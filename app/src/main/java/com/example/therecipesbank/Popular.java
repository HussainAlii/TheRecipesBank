package com.example.therecipesbank;

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
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Popular#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Popular extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Popular() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Popular.
     */
    // TODO: Rename and change types and number of parameters
    public static Popular newInstance(String param1, String param2) {
        Popular fragment = new Popular();
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
        return inflater.inflate(R.layout.fragment_popular, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button profileButton = view.findViewById(R.id.popular_profileButton);
        Button createButton = view.findViewById(R.id.popular_createButton);
        Button favButton = view.findViewById(R.id.popular_favButton);
        Button myChefsButton = view.findViewById(R.id.popular_myChefsButton);
        Button trendsButton = view.findViewById(R.id.popular_trendsButton);

        String foodList []= {"Apple","Chocolate","Chocolate","Chocolate","Chocolate","Chocolate","Chocolate","Chocolate","Chocolate","Chocolate","Chocolate","Chocolate","Chocolate"};

        ListAdapter theAdapter = new myAdapter(getContext(), foodList);
        ListView trendList = view.findViewById(R.id.trendsList);
        trendList.setAdapter(theAdapter);

        trendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //adapterView.getItemAtPosition(position)
            }
        });

        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_popular_to_profile);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_popular_to_createRes);
            }
        });

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_popular_to_favList);
            }
        });

       myChefsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_popular_to_myChefs);
            }
        });

    }
}