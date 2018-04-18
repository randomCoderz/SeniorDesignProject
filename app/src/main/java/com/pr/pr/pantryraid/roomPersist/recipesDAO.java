package com.pr.pr.pantryraid.roomPersist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface  recipesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipes(recipesDB r1);

    @Query("SELECT * FROM Recipes")
    recipesDB allRecipes();

    @Query("SELECT * FROM Recipes WHERE recipeID LIKE :id")
    recipesDB getRecipeFromID(int id);

    @Delete
    void deleteARecipe(recipesDB recipe);
}