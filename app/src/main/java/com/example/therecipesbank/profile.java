package com.example.therecipesbank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profile.
     */
    // TODO: Rename and change types and number of parameters
    public static profile newInstance(String param1, String param2) {
        profile fragment = new profile();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Buttons
        Button profileButton         = view.findViewById(R.id.profile_profileButton);
        Button createButton          = view.findViewById(R.id.profile_plus);
        Button favButton             = view.findViewById(R.id.profile_favButton);
        Button myChefsButton         = view.findViewById(R.id.profile_myChefsButton);
        Button trendsButton          = view.findViewById(R.id.profile_trendsButton);
        Button confirmButton         = view.findViewById(R.id.update_button);
        Button signOutButton         = view.findViewById(R.id.sigOutButton);
        Button myDishesButton         = view.findViewById(R.id.myDishes_button);



        //Views
        EditText usernameEdtTxt      = view.findViewById(R.id.username_field);
        EditText emailEdtTxt         = view.findViewById(R.id.email_field);
        EditText passEdtTxt          = view.findViewById(R.id.password_field);

        TextView followersTxt        = view.findViewById(R.id.followers);

        usernameEdtTxt.setText(MainActivity.Username);
        emailEdtTxt.setText(MainActivity.Email);
        passEdtTxt.setText(MainActivity.Password);
        followersTxt.setText("Followers: "+String.valueOf(MainActivity.Followers));

        final NavController navController = Navigation.findNavController(getActivity(),
                R.id.nav_host_fragment);

//        view.findViewById(R.id.profileButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navController.navigate(R.id.);
//            }
//        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), cameraImg.class);
                startActivity(intent);            }
        });

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_profile_to_favList);
            }
        });

        myChefsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_profile_to_myChefs);
            }
        });

        trendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_profile_to_popular);
            }
        });


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.dbHandler.updateUserProfile(usernameEdtTxt.getText().toString(), emailEdtTxt.getText().toString(), passEdtTxt.getText().toString());
                Toast.makeText(getContext(),  "Your changes have been applied", Toast.LENGTH_LONG).show();
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set new profile info here from the database
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        myDishesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_profile_to_myPosts);
            }
        });
    }
}