package com.pr.pr.pantryraid.RoomPersist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.pr.pr.pantryraid.ingredient;
import com.pr.pr.pantryraid.recipe;

@Dao
interface IngredientDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertIngredient(ingredient r1);

    @Query("SELECT * FROM Pantry WHERE id LIKE :id")
    ingredient getIngredientFromID(int id);

    @Delete
    void deleteAnIngredient(ingredient r);
}