package com.pr.pr.pantryraid.roomPersist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
@Dao
public interface DAO {
    @Insert
    void testPass(recipes r1);

    @Query("SELECT * FROM recipes")
    recipes getRecipes();

    @Delete
    void delete(recipes recipe);

}
