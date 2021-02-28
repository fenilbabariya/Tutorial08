package com.rku_18fotca11002.tutorial08;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Item> list;

    public MyAdapter(Context context, ArrayList<Item> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//      1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//      2.Get row from inflater
        View rowView = inflater.inflate(R.layout.listitem, parent, false);
        TextView username = rowView.findViewById(R.id.txtUsername);
        username.setText(list.get(position).getUsername());
        return rowView;
    }
}
