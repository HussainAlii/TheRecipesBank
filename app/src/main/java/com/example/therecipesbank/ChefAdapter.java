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

class ChefAdapter extends ArrayAdapter<post> {
    ArrayList<post> usersList =null;
    MainActivity activity = new MainActivity();
    public ChefAdapter(Context context, ArrayList<post> userList) {
        super(context, R.layout.row_layout_2, userList);
        this.usersList = userList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") View theView = inflater.inflate(R.layout.row_layout_2, parent, false);

        TextView desc = (TextView) theView.findViewById(R.id.desc);
        desc.setText(usersList.get(position).getDescription());

        TextView title = (TextView) theView.findViewById(R.id.textView1);

        title.setText(usersList.get(position).getTitle());

        TextView likes = (TextView) theView.findViewById(R.id.likesView);
        likes.setText(String.valueOf(usersList.get(position).getLikes()));

        TextView userView = (TextView) theView.findViewById(R.id.userPostView);
        userView.setText(usersList.get(position).getUserName());


        // 11. Set the image resource to the celestial object image

        ImageView imageView = (ImageView) theView.findViewById(R.id.imageView1);
        int imgResource = activity.getImgLocation(usersList.get(position).getImg());
        if (imgResource != 0)
            imageView.setImageResource(imgResource);
        else
            imageView.setImageResource(R.drawable.pizza);

        return theView;
    }
}
