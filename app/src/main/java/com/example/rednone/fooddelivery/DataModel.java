package com.example.rednone.fooddelivery;




/**
 * Created by RedNone on 20.03.2017.
 */

public class DataModel  {


    private int id;

    private String name = "";

    private String type = "";

    private String cost = "";

    private int version;

    private Boolean basket = false;

    public DataModel() {
    }

    public DataModel(int id, String name,String type, String cost, int version) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public Boolean getBasket() {
        return basket;
    }

    public void setBasket(Boolean basket) {
        this.basket = basket;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", cost='" + cost + '\'' +
                ", version=" + version +
                ", basket=" + basket +
                '}';
    }
}
