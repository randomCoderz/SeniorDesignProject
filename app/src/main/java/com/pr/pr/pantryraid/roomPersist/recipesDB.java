package com.pr.pr.pantryraid.roomPersist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class recipesDB {
    @PrimaryKey
    private int recipeId;

    @ColumnInfo
    private double price;

    //Foreign Key to be used from Ingredients
    @ColumnInfo(name = "Name")
    private String name;

    //Constructor for roomPersist
    public recipesDB(String name, int recipeId, double price){
        this.name = name;
        this.recipeId = recipeId;
        this.price = price;
    }
    //Getters and Setters
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

