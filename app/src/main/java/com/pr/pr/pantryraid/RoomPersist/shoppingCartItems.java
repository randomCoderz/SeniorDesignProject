package com.pr.pr.pantryraid.RoomPersist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ShoppingCart")
public class shoppingCartItems {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    int id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "amount")
    String amount;

    public shoppingCartItems(int id, String name, String amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
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
}
