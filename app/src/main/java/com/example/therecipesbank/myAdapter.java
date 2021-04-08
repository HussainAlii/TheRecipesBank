package com.example.therecipesbank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class myAdapter extends ArrayAdapter<String> {
    ArrayList<String> Info =null;
    public myAdapter(Context context, String[] values,ArrayList<String> info) {
        super(context, R.layout.row_layout_2, values);
        Info = info;
        System.out.println(info+"-------");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        System.out.println("----------"+position);
        System.out.println("-----"+Info.get(position));
        @SuppressLint("ViewHolder") View theView = inflater.inflate(R.layout.row_layout_2, parent, false);

        TextView desc = (TextView) theView.findViewById(R.id.desc);
        desc.setText(Info.get(position));

        TextView title = (TextView) theView.findViewById(R.id.textView1);

        title.setText("tt");

        ImageView imageView = (ImageView) theView.findViewById(R.id.imageView1);

        imageView.setImageResource(R.drawable.pizza);

        return theView;
    }
}
