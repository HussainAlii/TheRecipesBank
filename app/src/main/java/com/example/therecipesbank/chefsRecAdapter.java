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

class chefsRecAdapter extends ArrayAdapter<post> {
    ArrayList<post> postsList =null;
    MainActivity activity = new MainActivity();

    public chefsRecAdapter(Context context, ArrayList<post> postList) {
        super(context, R.layout.row_layout_chefs_rec_adapter, postList);
        this.postsList = postList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") View theView = inflater.inflate(R.layout.row_layout_chefs_rec_adapter, parent, false);

        TextView desc = (TextView) theView.findViewById(R.id.desc);
        desc.setText(postsList.get(position).getDescription());

        TextView title = (TextView) theView.findViewById(R.id.textView1);

        title.setText(postsList.get(position).getTitle());

        TextView likes = (TextView) theView.findViewById(R.id.likesViewChef);
        likes.setText(String.valueOf(postsList.get(position).getLikes()));


        // 11. Set the image resource to the celestial object image

        ImageView imageView = (ImageView) theView.findViewById(R.id.imageView1);
//        String imgLocation = postsList.get(position).getImg();
//        if (!imgLocation.equals("")){
//            int imgResource = activity.getImgLocation(postsList.get(position).getImg());
//            if (imgResource != 0)
//                imageView.setImageResource(imgResource);
//        }
//        else {
            imageView.setImageResource(R.drawable.pizza);
//        }
        return theView;
    }
}
