package com.pr.pr.pantryraid.RoomPersist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.pr.pr.pantryraid.recipe;

import java.util.List;

@Dao
public interface RecipesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipes(recipe r1);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipesList(List<recipe> r1);

    @Query("SELECT * FROM Recipes")
    List<recipe> allRecipes();

    @Query("SELECT * FROM Recipes WHERE recipeID LIKE :id")
    recipe getRecipeFromID(int id);

    @Query("SELECT * FROM Recipes WHERE favorites = 1")
    List<recipe> getFavorites();

    @Delete
    void deleteARecipe(recipe r);
}