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

    private static List<DataModel> dataModels;

    @Override
    public void onCreate() {
        super.onCreate();
        dataModels = new ArrayList<>();
    }

    public static List<DataModel> getDataModels() {
        return dataModels;
    }

    public static void setDataModels(List<DataModel> dataModels) {
        App.dataModels = dataModels;
    }
}
