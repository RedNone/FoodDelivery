package com.example.rednone.fooddelivery;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasketFragment extends Fragment {

    private RecyclerView recyclerView;
    private BasketAdapter basketAdapter;
    private FloatingActionButton floatingActionButton;

    public static final String TAG = "BasketFragment";


    public BasketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.basket_main, container, false);


        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.floatingActionButton);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.basketRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        basketAdapter = new BasketAdapter(App.getBasketModelsList());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(basketAdapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!App.getBasketModelsList().isEmpty()) {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    Log.d(TAG, gson.toJson(App.getBasketModelsList()));
                }
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        basketAdapter.notifyDataSetChanged();
    }
}
