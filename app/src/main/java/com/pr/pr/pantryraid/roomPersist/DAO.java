package com.pr.pr.pantryraid.roomPersist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.OnConflictStrategy;
@Dao
public interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void testPass(recipes r1);

    @Query("SELECT * FROM recipes")
    recipes getRecipes();

    @Delete
    void delete(recipes recipe);

}
