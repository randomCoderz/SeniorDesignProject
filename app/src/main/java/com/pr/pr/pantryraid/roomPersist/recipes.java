package com.pr.pr.pantryraid.roomPersist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

public class recipes {
        //Constructor for roomPersist
        public recipes(String name, int recipeId, double price){
            this.name = name;
            this.recipeId = recipeId;
            this.price = price;
        }

        @PrimaryKey
        private int recipeId;

        @ColumnInfo
        private double price;

        //Foreign Key to be used from Ingredients
        @ColumnInfo(name = "Name")
        private String name;
}

