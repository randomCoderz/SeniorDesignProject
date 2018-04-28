package com.pr.pr.pantryraid;

public class ingredient
{
    int id;
    String name;
    String amount;
    String unit;
    String photoURL;
    int photoID;
    double quantity;
    boolean selected;


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



    ingredient(int id, String name, String amount, String unit, String photoURL, double quantity, boolean selected)
    {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.photoURL = photoURL;
        this.selected = selected;
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

    public boolean getSelected() {
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
}