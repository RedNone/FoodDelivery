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

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;


    public static final String TAG = "MenuFragment";
    private  MenuAdapter menuAdapter;
    private List<DataModel> dataModelList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.menu_main, container, false);

        dataModelList = new ArrayList<>();

        menuAdapter = new MenuAdapter(dataModelList);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.menu_main);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.menuRecucler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());


        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(menuAdapter);

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
        dataModelList.clear();
        List<DataModel> list = App.getDataModels();

        for(DataModel obj : list)
        {
            if(obj.getType().equals("pizza"))
            {
                dataModelList.add(obj);
            }
        }

        menuAdapter.notifyDataSetChanged();
    }
}
