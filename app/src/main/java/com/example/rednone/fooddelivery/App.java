package com.example.rednone.fooddelivery;

import com.orm.SugarApp;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RedNone on 23.03.2017.
 */

public class App extends SugarApp {
    private static getJS intfObj;
    private Retrofit retrofit;
    private static MenuAdapter menuAdapter;
    private static List<BasketModel> basketModelsList;


    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        intfObj = retrofit.create(getJS.class);
        basketModelsList = new ArrayList<>();
    }

    public static List<BasketModel> getBasketModelsList() {
        return basketModelsList;
    }

    public static void setBasketModelsList(List<BasketModel> basketModelsLists) {
        basketModelsList = basketModelsLists;
    }
    public static void addList(int id, String name, String cost)
    {
        basketModelsList.add(new BasketModel(id,name,cost));
    }


    public static void setMenuAdapter(MenuAdapter menuAdapter) {
        App.menuAdapter = menuAdapter;
    }

    public static MenuAdapter getMenuAdapter() {
        return menuAdapter;
    }
    public static getJS getIntfObj() {
        return intfObj;
    }



}
