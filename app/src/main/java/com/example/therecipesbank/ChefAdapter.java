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

class ChefAdapter extends ArrayAdapter<chefs> {
    ArrayList<chefs> usersList =null;
    MainActivity activity = new MainActivity();
    public ChefAdapter(Context context, ArrayList<chefs> userList) {
        super(context, R.layout.row_layout_mychef, userList);
        this.usersList = userList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") View theView = inflater.inflate(R.layout.row_layout_mychef, parent, false);

        TextView username = (TextView) theView.findViewById(R.id.chef_username_field);
        username.setText(usersList.get(position).getUsername());

        TextView followers = (TextView) theView.findViewById(R.id.followersView);
        followers.setText("UnFollowers\n"+String.valueOf(usersList.get(position).getFollowers()));


        return theView;
    }
}
