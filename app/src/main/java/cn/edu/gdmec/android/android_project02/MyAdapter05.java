package cn.edu.gdmec.android.android_project02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus on 2017/12/26.
 */

public class MyAdapter05 extends BaseAdapter {
    public String type ="";
    private List<Sense> list;
    private LayoutInflater mInflater;



    public MyAdapter05(List<Sense> list, Context context) {
        this.list = list;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.project05_item,null);
            viewHolder.textView1 = (TextView) view.findViewById(R.id.text01);
            viewHolder.textView2 = (TextView) view.findViewById(R.id.text02);
            viewHolder.textView3 = (TextView) view.findViewById(R.id.text03);
            viewHolder.textView4 = (TextView) view.findViewById(R.id.text04);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Sense sense = list.get(i);
        if (type.equals("空气温度")){
            viewHolder.textView1.setText(type);

            viewHolder.textView2.setText(sense.getTemperature()+"");
            if (Integer.valueOf(sense.getTemperature())>40){
                viewHolder.textView3.setText("异常");
            }else {
                viewHolder.textView3.setText("正常");
            }

            viewHolder.textView4.setText(sense.getTimer()+"");

        }else if (type.equals("空气湿度")){
            viewHolder.textView1.setText(type);
            viewHolder.textView2.setText(sense.getHumidity()+"");
            if (Integer.valueOf(sense.getHumidity())>60){
                viewHolder.textView3.setText("异常");
            }else {
                viewHolder.textView3.setText("正常");
            }
            viewHolder.textView4.setText(sense.getTimer()+"");
        }else if (type.equals("光照")){
            viewHolder.textView1.setText(type);
            viewHolder.textView2.setText(sense.getLightIntensity()+"");
            if (Integer.valueOf(sense.getLightIntensity())>2000){
                viewHolder.textView3.setText("异常");
            }else {
                viewHolder.textView3.setText("正常");
            }
            viewHolder.textView4.setText(sense.getTimer()+"");
        }else if (type.equals("CO2")){
            viewHolder.textView1.setText(type);
            viewHolder.textView2.setText(sense.getCO2()+"");
            if (Integer.valueOf(sense.getCO2())>3000){
                viewHolder.textView3.setText("异常");
            }else {
                viewHolder.textView3.setText("正常");
            }
            viewHolder.textView4.setText(sense.getTimer()+"");
        }else if (type.equals("PM2.5")){
            viewHolder.textView1.setText(type);
            viewHolder.textView2.setText(sense.getPm()+"");
            if (Integer.valueOf(sense.getPm())>35){
                viewHolder.textView3.setText("异常");
            }else {
                viewHolder.textView3.setText("正常");
            }
            viewHolder.textView4.setText(sense.getTimer()+"");
        }else if (type.equals("道路状态")){
            viewHolder.textView1.setText(type);
            viewHolder.textView2.setText(sense.getStatus()+"");
            if (Integer.valueOf(sense.getStatus())>=3){
                viewHolder.textView3.setText("异常");
            }else {
                viewHolder.textView3.setText("正常");
            }
            viewHolder.textView4.setText(sense.getTimer()+"");
        }


        return view;
    }
    class ViewHolder{
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;

    }
}
