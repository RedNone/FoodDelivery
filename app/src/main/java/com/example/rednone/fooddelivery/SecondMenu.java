package com.example.rednone.fooddelivery;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondMenu extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    public static final String TAG = "SecondMenu";
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<DataModel> dataModelsList;
    private DrinksAdapter drinksAdapter;

    public SecondMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_second_menu, container, false);

        dataModelsList = new ArrayList<>();

        drinksAdapter = new DrinksAdapter(dataModelsList);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.drinksSwipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.drinksMenuRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(drinksAdapter);

        return rootView;
    }

    @Override
    public void onRefresh() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.downloadData();
    }
    void callBack()
    {

        if(swipeRefreshLayout.isShown()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

     void getData()
    {
        dataModelsList.clear();
        List<DataModel> list = App.getDataModels();

        for(DataModel obj : list)
        {
            if(obj.getType().equals("drinks"))
            {
                dataModelsList.add(obj);
            }
        }

        drinksAdapter.notifyDataSetChanged();
    }
}
