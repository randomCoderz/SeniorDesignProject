package com.pr.pr.pantryraid.RoomPersist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.pr.pr.pantryraid.ingredient;

import java.util.List;


@Dao
interface IngredientDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredient(ingredient r1);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredientList(List<ingredient> r1);

    @Query("SELECT * FROM Pantry WHERE id LIKE :id")
    ingredient getIngredientFromID(int id);

    @Query("SELECT * FROM Pantry")
    List<ingredient> getAllIngredients();

    @Delete
    void deleteAnIngredient(ingredient r);
}

