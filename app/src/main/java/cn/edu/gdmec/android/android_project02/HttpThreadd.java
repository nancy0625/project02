package cn.edu.gdmec.android.android_project02;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.List;

public class HttpThreadd extends Thread {

    private Context context;
    private String url;
    private String jsonstring;
    private List<String> list2;
    private Handler handler;

    public HttpThreadd(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            HttpPostRequest post2 = new HttpPostRequest();
            String json = "{\"RoadId\":1,\"UserName\":\"user1\"}";
            int re = post2.requestHttp("http://192.168.1.231:8080/TrafficServer/action/GetRoadStatus.do",json);
            String info2 = post2.getWebContext();
            Log.i("TAAAA",info2);



            HttpPostRequest post = new HttpPostRequest();

            int res = post.requestHttp(url, jsonstring);

            String webContent = post.getWebContext();
            System.out.println("***res:" + res);
            if (res == 1 && re == 1) {
                Message msg = new Message();
                msg.what = res;
                Bundle bundle = new Bundle();
                bundle.putString("list1",webContent);
                bundle.putString("list2",info2);
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
            /******/
            else if (res == 901) {
                Message msg = new Message();
                msg.what = res;
                msg.obj = "timeout!";
                handler.sendMessage(msg);
            }






        }


        /******/

    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJsonstring() {
        return jsonstring;
    }

    public void setJsonstring(String jsonstring) {
        this.jsonstring = jsonstring;
    }
}

