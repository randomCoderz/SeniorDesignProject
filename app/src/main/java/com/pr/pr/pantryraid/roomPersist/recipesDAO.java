package com.pr.pr.pantryraid.roomPersist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface  recipesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipes(recipesDB r1);

    @Query("SELECT * FROM Recipes")
    LiveData<List<recipesDB>> allRecipes();

    //livedata test
//    @Query("SELECT * FROM Recipes WHERE recipeId LIKE :id")
//    LiveData<recipesDB> getRecipeFromID(int id);

//    @Query("SELECT * FROM recipes WHERE recipeId LIKE :id")
//    List <recipesDB> getRecipeFromID(int id);

    @Delete
    void deleteARecipe(recipesDB recipe);
}

// LiveData is a data holder class that can be observed within a given lifecycle.
// Always holds/caches latest version of data. Notifies its active observers when the
// data has changed. Since we are getting all the contents of the database,
// we are notified whenever any of the database contents have changed.