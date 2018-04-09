package com.pr.pr.pantryraid;

import android.arch.persistence.room.Dao;

import java.util.ArrayList;
import java.util.List;

public class ingredient
{
    int id;
    String name;
    String amount;
    String unit;
    String photoURL;
    String aisle;
    int photoID;

    public ingredient(int id, String name, String photoURL)
    {
        this.id = id;
        this.name = name;
        this.photoURL = photoURL;
    }

    public ingredient(int id, String name, String amount, int photoID)
    {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.photoID = photoID;
    }

    public ingredient(int id, String name, String amount, String photoURL)
    {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.photoURL = photoURL;
    }


    public ingredient(int id, String name, String amount, String unit, String photoURL, String aisle)
    {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.photoURL = photoURL;
        this.aisle = aisle;
    }
}