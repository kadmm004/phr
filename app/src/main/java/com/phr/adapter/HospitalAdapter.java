package com.phr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;
import com.phr.entity.Hospital;
import com.phr.R;

public class HospitalAdapter extends ArrayAdapter {
    private int resourceId;

    class ViewHolder{
        ImageView hosImage;
        TextView hosName;
        TextView hosClass;
        TextView hosType;
        TextView hosDept;
        TextView hosComment;
    }

    public HospitalAdapter(Context context, int resource, List objects){
        super(context,resource,objects);
        resourceId=resource;
    }

    public View getView(int position, View convertView , ViewGroup parent){
        View view;
        ViewHolder holder;
        Hospital hos=(Hospital)getItem(position);
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            holder=new ViewHolder();
            holder.hosImage=(ImageView)view.findViewById(R.id.iv_hospic);
            holder.hosName=(TextView)view.findViewById(R.id.tv_hosname);
            holder.hosClass=(TextView)view.findViewById(R.id.tv_hosclass);
            holder.hosType=(TextView)view.findViewById(R.id.tv_hostype);
            holder.hosDept=(TextView)view.findViewById(R.id.tv_hosdpmt);
            holder.hosComment=(TextView)view.findViewById(R.id.tv_hoscomment);
            view.setTag(holder);
        }else{
            view=convertView;
            holder=(ViewHolder)view.getTag();
        }
        holder.hosImage.setImageResource(hos.getHosImage());
        holder.hosName.setText(hos.getHosName());
        holder.hosClass.setText(hos.getHosClass());
        holder.hosType.setText(hos.getHosType());
        holder.hosDept.setText(hos.getHosDept());
        holder.hosComment.setText(hos.getHosComment());
        return view;
    }
}
