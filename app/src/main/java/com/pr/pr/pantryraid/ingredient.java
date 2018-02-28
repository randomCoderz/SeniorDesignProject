package com.pr.pr.pantryraid;

import java.util.ArrayList;
import java.util.List;

public class ingredient
{
    int id;
    String name;
    String amount;
    String photoURL;
    int photoID;

    ingredient(int id, String name, String amount)
    {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    ingredient(int id, String name, String amount, int photoID)
    {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.photoID = photoID;
    }

    ingredient(int id, String name, String amount, String photoURL)
    {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.photoURL = photoURL;
    }
}