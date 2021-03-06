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

    @ColumnInfo(name = "photoURL")
    String photoURL;

    @ColumnInfo(name = "photoID")
    int photoID;

    @ColumnInfo(name = "quantity")
    double quantity;

    @ColumnInfo(name = "selected")
    boolean selected;

    @ColumnInfo(name = "missing")
    boolean missing;

    @ColumnInfo(name = "pantry")
    boolean pantry;

    @ColumnInfo(name = "shoppingCart")
    boolean shoppingCart;


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

    public ingredient(int id, String name, String amount, String unit, String photoURL, double quantity, boolean missing, boolean selected, boolean pantry, boolean shoppingCart)
    {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.photoURL = photoURL;
        this.selected = selected;
        this.quantity = quantity;
        this.missing = missing;
        this.pantry = pantry;
        this.shoppingCart = shoppingCart;

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
}