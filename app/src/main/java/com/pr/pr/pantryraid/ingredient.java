package com.pr.pr.pantryraid;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Pantry")
public class ingredient {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    int id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "amount")
    String amount;

    @ColumnInfo(name = "unit")
    String unit;

    String photoURL;
    int photoID;
    double quantity;
    boolean selected;
    boolean missing;
<<<<<<< HEAD
=======
    boolean pantry;
    boolean shoppingCart;
>>>>>>> parent of 01a5877... Revert "Merge branch 'gui' of https://github.com/randomCoderz/SeniorDesignProject into gui"



    @Ignore
    public ingredient(int id, String name, String photoURL)
    {
        this.id = id;
        this.name = name;
        this.photoURL = photoURL;
    }

    @Ignore
    public ingredient(int id, String name, String amount, int photoID)
    {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.photoID = photoID;
    }

    @Ignore
    public ingredient(int id, String name, String amount, String photoURL)
    {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.photoURL = photoURL;
    }

<<<<<<< HEAD
    public ingredient(int id, String name, String amount, String unit, String photoURL, double quantity, boolean missing, boolean selected)
=======

    ingredient(int id, String name, String amount, String unit, String photoURL, double quantity, boolean missing, boolean selected, boolean pantry, boolean shoppingCart)
>>>>>>> parent of 01a5877... Revert "Merge branch 'gui' of https://github.com/randomCoderz/SeniorDesignProject into gui"
    {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.photoURL = photoURL;
        this.selected = selected;
        this.quantity = quantity;
        this.missing = missing;
<<<<<<< HEAD

=======
        this.pantry = pantry;
        this.shoppingCart = shoppingCart;
>>>>>>> parent of 01a5877... Revert "Merge branch 'gui' of https://github.com/randomCoderz/SeniorDesignProject into gui"
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean isMissing() {
        return missing;
    }

    public void setMissing(boolean missing) {
        this.missing = missing;
    }

<<<<<<< HEAD
=======
    public boolean isPantry() {
        return pantry;
    }

    public void setPantry(boolean pantry) {
        this.pantry = pantry;
    }

    public boolean isShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(boolean shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

>>>>>>> parent of 01a5877... Revert "Merge branch 'gui' of https://github.com/randomCoderz/SeniorDesignProject into gui"
}