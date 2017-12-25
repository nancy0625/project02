package cn.edu.gdmec.android.android_project02;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by asus on 2017/12/25.
 */

public class JsonTools {
    private static List<String> list;
    public static List<String> parseJson(String ss){
        list = new ArrayList<String>();
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(ss);
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            Iterator<String> iterator = jsonObject.keys();
            while(iterator.hasNext()){
                String key = iterator.next();
                String value = jsonObject.getString(key);
                list.add(value);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
       return list;
    };
}
