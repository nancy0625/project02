package cn.edu.gdmec.android.android_project02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/12/25.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    private SQLiteDatabase db;


    public static final String CREATE_SENSE = "create table sense ("
            + "id integer primary key autoincrement, "
            + "temperature integer, "
            + "humidity integer, "
            + "lightIntensity integer, "
            + "CO2 integer, "
            + "pm integer, "
            + "status integer,"
            + "timer integer)";
    public static final String CREATE_CAR = "create table car ("
            + "id integer primary key autoincrement, "
            + "Carid integer, "
            + "intime varchar(25), "
            + "outtime varchar(25), "
            + "money integer)";
    public static final String CREATE_RECORD = "create table record ("
            + "id integer primary key autoincrement, "
            + "Carid integer, "
            + "intime varchar(25), "
            + "outtime varchar(25), "
            + "money integer)";

    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_SENSE);
        sqLiteDatabase.execSQL(CREATE_CAR);
        sqLiteDatabase.execSQL(CREATE_RECORD);
        Toast.makeText(mContext, "CREATE　SUCCEEDED", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
    public void deleteTable(String num){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(num,null,null);
    }


    public List<Sense> selectAvg(int num) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Sense> list = new ArrayList<Sense>();
        Cursor cursor = db.rawQuery(" select strftime('%Y-%m-%d %H:%M:%S',datetime(round(timer/1000),'unixepoch')),round(avg(temperature)),round(avg(humidity)),round(avg(lightIntensity)),round(avg(CO2)),round(avg(pm)),round(avg(status))" +
                " from sense" +
                " group by strftime('%Y-%m-%d %H:%M',datetime(round(timer/1000),'unixepoch'))" +
                " order by 1 desc" +
                " limit "+ num, null);
        cursor.moveToFirst();

        do {
            Sense sense = new Sense();
            sense.setTemperature(cursor.getInt(1));
            sense.setHumidity(cursor.getInt(2));
            sense.setLightIntensity(cursor.getInt(3));
            sense.setCO2(cursor.getInt(4));
            sense.setPm(cursor.getInt(5));
            sense.setStatus(cursor.getInt(6));
            sense.setTimer( cursor.getString(0));
            list.add(sense);
        }while (cursor.moveToNext());

        cursor.close();

        return list;
    }
    public List<Car> selectCarJ(String num) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Car> list = new ArrayList<Car>();
        Cursor cursor = db.rawQuery("select Carid,intime,outtime,money from car order by "+num+" desc", null);
        Log.i("cusor",cursor.getCount()+"");
        cursor.moveToFirst();
       do {
            Car car = new Car();
            car.setID(cursor.getInt(0));
            car.setIntime(cursor.getString(1));
            car.setOuttime(cursor.getString(2));
            car.setMoney(cursor.getInt(3));
           list.add(car);

        } while (cursor.moveToNext());
        cursor.close();

        return list;
    }
    public List<Car> selectCarS(String num) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Car> list = new ArrayList<Car>();
        Cursor cursor = db.rawQuery("select Carid,intime,outtime,money from car order by "+num+" asc", null);
        cursor.moveToFirst();


        do {
            Car car = new Car();
            car.setID(cursor.getInt(0));
            car.setIntime(cursor.getString(1));
            car.setOuttime(cursor.getString(2));
            car.setMoney(cursor.getInt(3));
            list.add(car);

        } while (cursor.moveToNext());
        cursor.close();

        return list;
    }



    public Sense select() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select temperature,humidity,lightIntensity,CO2,pm,status,timer from sense order by timer desc", null);
        cursor.moveToFirst();

        Sense sense = new Sense();
        sense.setTemperature(cursor.getInt(0));
        sense.setHumidity(cursor.getInt(1));
        sense.setLightIntensity(cursor.getInt(2));
        sense.setCO2(cursor.getInt(3));
        sense.setPm(cursor.getInt(4));
        sense.setStatus(cursor.getInt(5));
        sense.setTimer( cursor.getString(6));
        cursor.close();

        return sense;
    }
    public List<Record> select20() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Record> list = new ArrayList<Record>();
        Cursor cursor = db.rawQuery("select Carid,intime,outtime,money from record", null);
        cursor.moveToFirst();


        do {
            Record record = new Record();
            record.setID(cursor.getInt(0));
            record.setIntime(cursor.getString(1));
            record.setOuttime(cursor.getString(2));
            record.setMoney(cursor.getInt(3));
            list.add(record);

        } while (cursor.moveToNext());
        cursor.close();

        return list;
    }
    public void setData20(int i, String humm, String ligt, int COO) {
            db = this.getWritableDatabase();
        ContentValues updateValues = new ContentValues();
        updateValues.put("Carid", i);
        updateValues.put("intime", humm);
        updateValues.put("outtime", ligt);
        updateValues.put("money", COO);

        db.insert("record", null, updateValues);
        Log.i("record,record","CCCCCCCCC");


    }


}
