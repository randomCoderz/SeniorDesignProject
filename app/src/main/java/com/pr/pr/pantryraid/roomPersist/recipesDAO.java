package com.pr.pr.pantryraid.roomPersist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
@Dao
public interface recipesDAO {
    @Insert
    void testPass(recipesDB r1);

    @Query("SELECT * FROM recipesDB")
    recipesDB getRecipes();

    @Query("SELECT * from recipesDB where recipeId LIKE :id")
    recipesDB getRecipeFromID(int id);

    @Delete
    void delete(recipesDB recipe);

}
