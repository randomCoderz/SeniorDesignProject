package com.pr.pr.pantryraid.roomPersist;

public class dbInitialize{

    public static void test(AppDatabase db){
        recipes r = new recipes("TestName", 10, 20);
        db.recipeDao().testPass(r);
        }

    public static void fetch(AppDatabase db){
        for(int i = 0; i < 100; i++) {
            System.out.println("IM IN THE FETCH");
        }
        System.out.println(db.recipeDao().getRecipes());
    }
}
