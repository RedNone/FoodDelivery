package com.example.rednone.fooddelivery;

import com.orm.SugarRecord;

/**
 * Created by RedNone on 23.03.2017.
 */

public class DbModel extends SugarRecord {
    private int id;

    private String name = "";

    private String cost = "";

    private int version;


    public  DbModel(int id, String name, String cost, int version) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.version = version;

    }

    public  DbModel() {
    }

    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "DbModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost='" + cost + '\'' +
                ", version=" + version +
                '}';
    }
}
