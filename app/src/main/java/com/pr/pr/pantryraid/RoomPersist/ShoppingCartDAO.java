package com.pr.pr.pantryraid.RoomPersist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.pr.pr.pantryraid.recipe;

@Dao
interface ShoppingCartDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShoppingCartItem(shoppingCartItems r1);

    @Query("SELECT * FROM ShoppingCart WHERE id LIKE :id")
    shoppingCartItems getCartItemsFromID(int id);

    @Delete
    void deleteACartItem(shoppingCartItems r);
}