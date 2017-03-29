package com.example.rednone.fooddelivery;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    //List<DataModel> dataModel;

    public static final String TAG = "MenuFragment";

    public MenuFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.menu_main, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.menu_main);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.blue_swipe);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.menuRecucler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(App.getMenuAdapter());

        return rootView;
    }


    @Override
    public void onRefresh() {
       MainActivity mainActivity = (MainActivity) getActivity();
       mainActivity.downloadData();
    }

    protected void callBack()
    {
        if(swipeRefreshLayout.isShown()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
