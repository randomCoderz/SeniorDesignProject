package com.pr.pr.pantryraid.RoomPersist;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.OnConflictStrategy;

public interface pantryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPantry(pantryDB r1);

    @Query("SELECT * FROM pantryDB")
    pantryDB getpantry();

    @Delete
    void deletePantry();
}
