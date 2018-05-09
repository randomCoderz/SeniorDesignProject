package com.pr.pr.pantryraid.RoomPersist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

public class RecipeView extends AndroidViewModel {

    private AppDatabase db;
    public RecipeView (Application app){
        super(app);
        db = AppDatabase.getInMemoryDatabase(this.getApplication());
    }

}
