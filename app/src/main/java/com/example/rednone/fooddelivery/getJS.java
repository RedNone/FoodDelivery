package com.example.rednone.fooddelivery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by RedNone on 20.03.2017.
 */

public interface getJS {

    @GET("/RedNone/GetJson/master/dishes.json")
    Call<List<DataModel>> getData();
}
