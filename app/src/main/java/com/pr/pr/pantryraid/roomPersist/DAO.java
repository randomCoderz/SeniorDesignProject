package com.pr.pr.pantryraid.roomPersist;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

public interface DAO {
    @Insert
    public void testPass(recipes r1);

    @Query("SELECT * FROM recipes")
    public recipes getRecipes();

}
