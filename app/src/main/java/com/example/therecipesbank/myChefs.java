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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link myChefs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class myChefs extends Fragment {
    ArrayList<chefs> userList=null;
    ListView ChefsList = null;
    ListAdapter chefAdapter =null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public myChefs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment myChefs.
     */
    // TODO: Rename and change types and number of parameters
    public static myChefs newInstance(String param1, String param2) {
        myChefs fragment = new myChefs();
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
        return inflater.inflate(R.layout.fragment_my_chefs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button profileButton = view.findViewById(R.id.myChefs_profileButton);
        Button createButton = view.findViewById(R.id.myChefs_createButton);
        Button favButton = view.findViewById(R.id.myChefs_favButton);
        Button myChefsButton = view.findViewById(R.id.myChefs_myChefsButton);
        Button trendsButton = view.findViewById(R.id.myChefs_trendsButton);
        ChefsList = view.findViewById(R.id.myChefsList);
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        userList = MainActivity.dbHandler.getLatestChefs(MainActivity.UserId);
        chefAdapter = new ChefAdapter(getContext(), userList);
        ChefsList.setAdapter(chefAdapter);
        ChefsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                MainActivity.selectedChef = (chefs) adapterView.getItemAtPosition(position);
                navController.navigate(R.id.action_myChefs_to_chefsRecList);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_myChefs_to_profile);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), cameraImg.class);
                startActivity(intent);            }
        });

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_myChefs_to_favList);
            }
        });

//        view.findViewById(R.id.myChefsButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navController.navigate(R.id.action_myChefs_to_myChefs);
//            }
//        });

        trendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_myChefs_to_popular);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        userList = MainActivity.dbHandler.getLatestChefs(MainActivity.UserId);
        chefAdapter = new ChefAdapter(getContext(), userList);
        ChefsList.setAdapter(chefAdapter);
    }
}