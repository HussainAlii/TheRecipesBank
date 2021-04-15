package com.example.therecipesbank;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link createRes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class createRes extends Fragment {
    private ImageView img = null;
    static Bitmap bitmapImg = null;
    private byte byteImg []=null;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public createRes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment createRes.
     */
    // TODO: Rename and change types and number of parameters
    public static createRes newInstance(String param1, String param2) {
        createRes fragment = new createRes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_res, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button profileButton = view.findViewById(R.id.createRes_profileButton);
        Button createButton = view.findViewById(R.id.createRes_addButton);
        Button favButton = view.findViewById(R.id.createRes_favoriteButton);
        Button myChefsButton = view.findViewById(R.id.createRes_dishButton);
        Button trendsButton = view.findViewById(R.id.createRes_popularButton);
        Button postButton = view.findViewById(R.id.RConfirm);
        EditText recTitle = view.findViewById(R.id.RTitile);
        EditText recDesc = view.findViewById(R.id.RDesc);

        img = (ImageView) view.findViewById(R.id.RImg);
        img.setImageBitmap(bitmapImg);

        //convert from bitmaps to byte arr
        ByteArrayOutputStream blob = new ByteArrayOutputStream();
        bitmapImg.compress(Bitmap.CompressFormat.PNG, 0, blob);
        byteImg = blob.toByteArray();

        final NavController navController = Navigation.findNavController(getActivity(),
                R.id.nav_host_fragment);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_createRes_to_profile);
            }
        });

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_createRes_to_favList);
            }
        });

        myChefsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_createRes_to_myChefs);
            }
        });

        trendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_createRes_to_popular);
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!recTitle.getText().toString().equals("")) {
                    if (!recDesc.getText().toString().equals("")) {
                        MainActivity.dbHandler.insertIntoPosts(recTitle.getText().toString(), recDesc.getText().toString(), MainActivity.UserId, byteImg);
                        Toast.makeText(getContext(), "Your recipe has been posted ;)", Toast.LENGTH_LONG).show();
                        navController.navigate(R.id.action_createRes_to_popular);
                    } else {
                        Toast.makeText(getContext(), "Please Write a Title", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Please Write a Description", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}