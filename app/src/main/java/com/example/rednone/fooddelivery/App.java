package com.example.rednone.fooddelivery;

import android.app.Application;
import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RedNone on 20.03.2017.
 */

public class App extends Application {

    private static getJS intfObj;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("AppTAG", "Start");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        intfObj = retrofit.create(getJS.class);
    }

    public static getJS getApi (){return intfObj;}
}
