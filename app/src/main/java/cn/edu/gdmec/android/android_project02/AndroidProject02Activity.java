package cn.edu.gdmec.android.android_project02;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class AndroidProject02Activity extends Activity {
    private List<String> list;
    private List<String> list2;
    private HttpThreadd httpThread;
    private MyDataBaseHelper dataBaseHelper;
    private Handler handler;


    private TextView tv_title1;
    private TextView wendu;
    private TextView tv_title2;
    private TextView shidu;
    private TextView tv_title3;
    private TextView guangzhao;
    private TextView tv_title4;
    private TextView huatan;
    private TextView tv_title5;
    private TextView pm;
    private TextView tv_title6;
    private TextView daolu;
    private int count = 0;
    private SQLiteDatabase db;
    private int co2, co2sum, temsum, humsum, lightsum, statusum, pm2sum, tem, hum, light, statu, pm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_project02);
        try {
            initView();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void wendu(View view) {
        Toast.makeText(this, "温度", Toast.LENGTH_SHORT).show();
    }

    public void shidu(View view) {
        Toast.makeText(this, "湿度", Toast.LENGTH_SHORT).show();
    }

    public void guangzhao(View view) {
        Toast.makeText(this, "光照", Toast.LENGTH_SHORT).show();
    }

    public void huatan(View view) {
        Toast.makeText(this, "二氧化碳", Toast.LENGTH_SHORT).show();
    }

    public void pm(View view) {
        Toast.makeText(this, "PM2.5", Toast.LENGTH_SHORT).show();
    }

    public void daolu(View view) {

        Toast.makeText(this, "道路", Toast.LENGTH_SHORT).show();
    }

    private void initView() throws InterruptedException {
        tv_title1 = (TextView) findViewById(R.id.tv_title1);
        wendu = (TextView) findViewById(R.id.wendu);
        tv_title2 = (TextView) findViewById(R.id.tv_title2);
        shidu = (TextView) findViewById(R.id.shidu);
        tv_title3 = (TextView) findViewById(R.id.tv_title3);
        guangzhao = (TextView) findViewById(R.id.guangzhao);
        tv_title4 = (TextView) findViewById(R.id.tv_title4);
        huatan = (TextView) findViewById(R.id.huatan);
        tv_title5 = (TextView) findViewById(R.id.tv_title5);
        pm = (TextView) findViewById(R.id.pm);
        tv_title6 = (TextView) findViewById(R.id.tv_title6);
        daolu = (TextView) findViewById(R.id.daolu);
        dataBaseHelper = new MyDataBaseHelper(this, "Sense.db", null, 1);
        db = dataBaseHelper.getWritableDatabase();
        list = new ArrayList<String>();
        list2 = new ArrayList<String>();


        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                Bundle ll = message.getData();
                String ss = ll.getString("list1");
                String sss = ll.getString("list2");
                list = JsonTools.parseJson(ss);
                list2 = JsonTools.parseJson(sss);

                count++;

                tem = Integer.valueOf(list.get(4));
                hum = Integer.valueOf(list.get(5));
                light = Integer.valueOf(list.get(6));
                co2 = Integer.valueOf(list.get(3));
                pm2 = Integer.valueOf(list.get(2));
                statu = Integer.valueOf(list2.get(2));

                temsum += tem;
                humsum += hum;
                lightsum += light;
                co2sum += co2;
                pm2sum += pm2;
                statusum += statu;

                if (count % 12 == 0) {
                    tem = temsum / 12;
                    hum = humsum / 12;
                    light = lightsum / 12;
                    co2 = co2sum / 12;
                    pm2 = pm2sum / 12;
                    statu = statusum / 12;

                    selectAllDataNum();
                    setData(tem, hum, light, co2, pm2, statu);


                    temsum = 0;
                    humsum = 0;
                    lightsum = 0;
                    co2sum = 0;
                    pm2sum = 0;
                    statusum = 0;


                }
                Log.i("count", count + "");


                if (tem > 40) {

                    wendu.setBackgroundResource(R.drawable.red);

                } else {
                    wendu.setBackgroundResource(R.drawable.green);
                }
                if (hum > 40) {
                    shidu.setBackgroundResource(R.drawable.red);

                } else {
                    shidu.setBackgroundResource(R.drawable.green);
                }
                if (light > 2000) {
                    guangzhao.setBackgroundResource(R.drawable.red);

                } else {
                    guangzhao.setBackgroundResource(R.drawable.green);
                }
                if (co2 > 3000) {
                    huatan.setBackgroundResource(R.drawable.red);

                } else {
                    huatan.setBackgroundResource(R.drawable.green);
                }
                if (pm2 > 35) {
                    pm.setBackgroundResource(R.drawable.red);

                } else {
                    pm.setBackgroundResource(R.drawable.green);
                }
                if (statu >= 3) {
                    daolu.setBackgroundResource(R.drawable.red);

                } else {
                    daolu.setBackgroundResource(R.drawable.green);
                }
                wendu.setText(tem + "");
                shidu.setText(hum + "");
                guangzhao.setText(light + "");
                huatan.setText(co2 + "");
                pm.setText(pm2 + "");
                daolu.setText(statu + "");

                return false;
            }

        });
        sense();


    }

    public void setData(int tem, int hum, int lig, int CO, int ppm, int statu) {
        ContentValues updateValues = new ContentValues();
        updateValues.put("temperature", tem);
        updateValues.put("humidity", hum);
        updateValues.put("lightIntensity", lig);
        updateValues.put("CO2", CO);
        updateValues.put("pm", ppm);
        updateValues.put("status", statu);
        db.update("sense", updateValues, null, null);
        Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();

    }

    public void selectAllDataNum() {
        Cursor cursor = db.rawQuery("select count(*) from sense", null);
        cursor.moveToFirst();
        Long count = cursor.getLong(0);
        cursor.close();
        if (count > 3) {
            db.execSQL("delete from sense where id in (select top 1 id from sense)");
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        }

    }

    public void sense() {
        httpThread = new HttpThreadd(this, handler);
        httpThread.setUrl("http://192.168.1.231:8080/TrafficServer/action/GetAllSense.do");
        String strJson = "{\"UserName\":" + "\"user1\"" + "}";
        httpThread.setJsonstring(strJson);
        httpThread.start();
    }


}
