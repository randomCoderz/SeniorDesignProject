package com.pr.pr.pantryraid.roomPersist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.Room;

@Database(entities = {recipes.class}, version = 1)
public abstract class testDatabase extends RoomDatabase {
    public abstract DAO test();
}
