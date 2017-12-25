package cn.edu.gdmec.android.android_project02;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.FileOutputStream;

/**
 * Created by asus on 2017/12/25.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    public static final String CREATE_SENSE = "create table sense ("
            + "id integer primary key autoincrement, "
            + "temperature integer, "
            + "humidity integer, "
            + "lightIntensity integer, "
            + "CO2 integer, "
            + "pm integer, "
            + "status integer)";

    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_SENSE);
        Toast.makeText(mContext,"CREATE　SUCCEEDED",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists sense");
        onCreate(sqLiteDatabase);

    }
}
