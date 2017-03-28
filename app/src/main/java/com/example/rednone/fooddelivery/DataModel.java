package com.example.rednone.fooddelivery;




/**
 * Created by RedNone on 20.03.2017.
 */

public class DataModel  {


    private int id;

    private String name = "";

    private String cost = "";

    private int version;

    public DataModel() {
    }

    public DataModel(int id, String name, String cost, int version) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

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
