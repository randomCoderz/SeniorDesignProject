package com.pr.pr.pantryraid.roomPersist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface  recipesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRecipes(recipesDB r1);

    @Query("SELECT * FROM recipes")
    List<recipesDB> allRecipes();

//    @Query("SELECT * FROM recipes WHERE recipeId LIKE :id")
//    void getRecipeFromID(int id);
    @Delete
    void deleteAllRecipes(recipesDB recipe);
}


// LiveData is a data holder class that can be observed within a given lifecycle.
// Always holds/caches latest version of data. Notifies its active observers when the
// data has changed. Since we are getting all the contents of the database,
// we are notified whenever any of the database contents have changed.