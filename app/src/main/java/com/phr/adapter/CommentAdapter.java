package com.phr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.phr.R;
import com.phr.entity.Comment;

import java.util.List;

public class CommentAdapter extends ArrayAdapter {
    private int resourceId;

    class ViewHolder{
        TextView patientName;
        TextView date;
        TextView disease;
        TextView method;
        TextView cost;
        TextView result;
        TextView comment;
    }

    public CommentAdapter(Context context, int resource, List objects){
        super(context,resource,objects);
        resourceId=resource;
    }

    public View getView(int position, View convertView , ViewGroup parent){
        View view;
        CommentAdapter.ViewHolder holder;
        Comment com=(Comment)getItem(position);
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            holder=new CommentAdapter.ViewHolder();
            holder.patientName =(TextView)view.findViewById(R.id.tv_comment_patientName);
            holder.date =(TextView)view.findViewById(R.id.tv_comment_date);
            holder.disease =(TextView)view.findViewById(R.id.tv_comment_disease);
            holder.method =(TextView)view.findViewById(R.id.tv_comment_method);
            holder.cost =(TextView)view.findViewById(R.id.tv_comment_cost);
            holder.result =(TextView)view.findViewById(R.id.tv_comment_result);
            holder.comment =(TextView)view.findViewById(R.id.tv_comment);
            view.setTag(holder);
        }else{
            view=convertView;
            holder=(CommentAdapter.ViewHolder)view.getTag();
        }
        holder.patientName.setText(com.getPatientName());
        holder.date.setText(com.getDate());
        holder.disease.setText(com.getDisease());
        holder.method.setText(com.getMethod());
        holder.cost.setText(com.getCost());
        holder.result.setText(com.getResult());
        holder.comment.setText(com.getComment());
        return view;
    }
}
