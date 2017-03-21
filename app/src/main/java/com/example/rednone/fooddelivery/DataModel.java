package com.example.rednone.fooddelivery;

import com.google.gson.annotations.Expose;


/**
 * Created by RedNone on 20.03.2017.
 */

public class DataModel {

    private int id;

    private String name = "";

    private String cost = "";

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCost() {
        return cost;
    }
}
