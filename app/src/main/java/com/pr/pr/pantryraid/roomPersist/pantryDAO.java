package com.pr.pr.pantryraid.roomPersist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

public interface pantryDAO {
    @Insert
    void insertPantry(pantryDB r1);

    @Query("SELECT * FROM pantryDB")
    pantryDB getpantry();

    @Delete
    void deletePantry();


}
