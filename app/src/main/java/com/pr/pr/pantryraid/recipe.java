package com.pr.pr.pantryraid;
import java.util.ArrayList;
/**
 * Created by Kan on 2/22/18.
 */

public class recipe {
    int id;
    String name;
    String url;
    int readyInMinutes;
    ArrayList<ingredient> ingredients = new ArrayList<>();
    String instructions;
    ArrayList<String> analyzedInstructions;

    public recipe(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public recipe(int id, String name, String url)
    {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public recipe(int id, String name, String url, int readyInMinutes)
    {
        this.id = id;
        this.name = name;
        this.url = url;
        this.readyInMinutes = readyInMinutes;
    }


    public recipe(int id, String name, String url, int readyInMinutes, ArrayList<ingredient> ingredients, String instructions)
    {
        this.id = id;
        this.name = name;
        this.url = url;
        this.readyInMinutes = readyInMinutes;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
}
