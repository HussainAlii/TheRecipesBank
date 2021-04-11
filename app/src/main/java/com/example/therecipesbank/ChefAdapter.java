package com.example.therecipesbank;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        Button followersButton = theView.findViewById(R.id.unFollowButton);
        ImageView chefIcon = theView.findViewById(R.id.chefIcon);
        followersButton.setText("UnFollow\n"+String.valueOf(usersList.get(position).getFollowers()));
        followersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.dbHandler.unsubscribe(MainActivity.UserId,usersList.get(position).getUser_id());
                followersButton.setVisibility(View.INVISIBLE);
                chefIcon.setVisibility(View.INVISIBLE);
                theView.setBackgroundColor(Color.rgb(210,69,100));
            }
        });


        return theView;
    }
}
