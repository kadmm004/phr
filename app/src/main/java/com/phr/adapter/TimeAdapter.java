package com.phr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import com.phr.entity.Time;
import com.phr.R;

public class TimeAdapter extends ArrayAdapter {
    private int resourceId;

    class ViewHolder{
        TextView registTime;
        TextView registPrice;
    }

    public TimeAdapter(Context context, int resource, List objects){
        super(context,resource,objects);
        resourceId=resource;
    }

    public View getView(int position, View convertView , ViewGroup parent){
        View view;
        TimeAdapter.ViewHolder holder;
        Time doc=(Time)getItem(position);
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            holder=new TimeAdapter.ViewHolder();
            holder.registTime =(TextView)view.findViewById(R.id.tv_regist_time);
            holder.registPrice =(TextView)view.findViewById(R.id.tv_regist_price);

            view.setTag(holder);
        }else{
            view=convertView;
            holder=(TimeAdapter.ViewHolder)view.getTag();
        }
        holder.registTime.setText(doc.getRegistTime());
        holder.registPrice.setText(doc.getRegistPrice());

        return view;
    }
}
