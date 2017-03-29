package com.example.rednone.fooddelivery;

import com.orm.SugarRecord;

/**
 * Created by RedNone on 28.03.2017.
 */

public class DbModelUser extends SugarRecord {


    private String Email;



    public DbModelUser() {
    }

    public DbModelUser(String email) {
        Email = email;

    }




    public String getEmail() {
        return Email;
    }



    public void setEmail(String email) {
        Email = email;
    }


}
