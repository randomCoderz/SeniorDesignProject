package com.pr.pr.pantryraid;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Kan on 3/8/18.
 */

public class step {
    int number;
    String step_description;
    ArrayList<ingredient> ingredients;
    JSONArray equipment;

    public step(int number, String step_description, ArrayList<ingredient> ingredients, JSONArray equipment)
    {
        this.number = number;
        this.step_description = step_description;
        this.ingredients = ingredients;
        this.equipment = equipment;
    }

}
