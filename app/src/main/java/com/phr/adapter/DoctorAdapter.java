package com.phr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;
import com.phr.entity.Doctor;
import com.phr.R;

public class DoctorAdapter extends ArrayAdapter {
    private int resourceId;

    class ViewHolder{
        ImageView docImage;
        TextView docName;
        TextView docTitle;
        TextView docScore;
        TextView docNum;
        TextView docMajor;
    }

    public DoctorAdapter(Context context, int resource, List objects){
        super(context,resource,objects);
        resourceId=resource;
    }

    public View getView(int position, View convertView , ViewGroup parent){
        View view;
        ViewHolder holder;
        Doctor doc=(Doctor)getItem(position);
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            holder=new ViewHolder();
            holder.docImage =(ImageView)view.findViewById(R.id.iv_docImage);
            holder.docName =(TextView)view.findViewById(R.id.tv_docName);
            holder.docTitle =(TextView)view.findViewById(R.id.tv_docTitle);
            holder.docScore =(TextView)view.findViewById(R.id.tv_docScore);
            holder.docNum =(TextView)view.findViewById(R.id.tv_docNum);
            holder.docMajor =(TextView)view.findViewById(R.id.tv_docMajor);
            view.setTag(holder);
        }else{
            view=convertView;
            holder=(ViewHolder)view.getTag();
        }
        holder.docImage.setImageResource(doc.getDocImage());
        holder.docName.setText(doc.getDocName());
        holder.docTitle.setText(doc.getDocTitle());
        holder.docScore.setText(doc.getDocScore());
        holder.docNum.setText(doc.getDocNum());
        holder.docMajor.setText(doc.getDocMajor());
        return view;
    }
}
