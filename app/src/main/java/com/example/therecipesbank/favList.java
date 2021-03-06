package com.example.therecipesbank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
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
 * Use the {@link favList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class favList extends Fragment {
    ArrayList<post> postList=null;
    ListView fav_list = null;
    ListAdapter theAdapter =null;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public favList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment favList.
     */
    // TODO: Rename and change types and number of parameters
    public static favList newInstance(String param1, String param2) {
        favList fragment = new favList();
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
        return inflater.inflate(R.layout.fragment_fav_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button profileButton = view.findViewById(R.id.favList_profileButton);
        Button createButton = view.findViewById(R.id.favList_createButton);
        Button favButton = view.findViewById(R.id.favList_favButton);
        Button myChefsButton = view.findViewById(R.id.favList_myChefsButton);
        Button trendsButton = view.findViewById(R.id.favList_trendsButton);
        fav_list = view.findViewById(R.id.favList_List);

        postList = MainActivity.dbHandler.getFavoriteList(MainActivity.UserId);
        theAdapter = new myAdapter(getContext(), postList);
        fav_list.setAdapter(theAdapter);
        fav_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                MainActivity.selectedPost = (post)adapterView.getItemAtPosition(position);
                Intent intent = new Intent(getContext(), recipeView.class);
                startActivity(intent);
            }
        });

        final NavController navController = Navigation.findNavController(getActivity(),
                R.id.nav_host_fragment);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_favList_to_profile);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), cameraImg.class);
                startActivity(intent);            }
        });

//        view.findViewById(R.id.favButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navController.navigate(R.id.action_favList_to_favList);
//            }
//        });

        myChefsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_favList_to_myChefs);
            }
        });

        trendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_favList_to_popular);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        postList = MainActivity.dbHandler.getFavoriteList(MainActivity.UserId);
        theAdapter = new myAdapter(getContext(), postList);
        fav_list.setAdapter(theAdapter);
    }
}