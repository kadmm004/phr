package com.phr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import com.phr.entity.News;
import com.phr.R;

public class NewsAdapter extends ArrayAdapter<News> {
    private int resourceId;
    //重写构造函数，把id和数据传进来
    public NewsAdapter(Context context, int textViewResourceId, List<News> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        News news = getItem(position);
        //对上一个语句进行优化
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);//false参数表示只让我们在父布局中声明的layout属性生效
        }else {
            view = convertView;
        }
        TextView newsTitle = view.findViewById(R.id.tv_news_title);
        newsTitle.setText(news.getNewsTitle());
        return view;
    }

}
