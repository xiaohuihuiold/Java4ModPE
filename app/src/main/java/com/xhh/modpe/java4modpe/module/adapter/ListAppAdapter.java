package com.xhh.modpe.java4modpe.module.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xhh.modpe.java4modpe.R;
import com.xhh.modpe.java4modpe.module.model.AppData;

import java.util.ArrayList;

public class ListAppAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<AppData> appDatas = new ArrayList<>();

    public ListAppAdapter(Context context, ArrayList<AppData> appDatas) {
        this.context = context;
        this.appDatas = appDatas;
    }

    @Override
    public int getCount() {
        return appDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return appDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_app, null);
            viewHolder.name = convertView.findViewById(R.id.list_name);
            viewHolder.icon = convertView.findViewById(R.id.list_icon);
            convertView.setTag(viewHolder);
            viewHolder.name.setText(appDatas.get(position).getName()+"\n"+appDatas.get(position).getDescription());
            viewHolder.icon.setBackground(appDatas.get(position).getIcon());
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.name.setText(appDatas.get(position).getName()+"\n"+appDatas.get(position).getDescription());
            viewHolder.icon.setBackground(appDatas.get(position).getIcon());
        }
        return convertView;
    }

    class ViewHolder {
        public TextView name;
        public ImageView icon;
    }
}
