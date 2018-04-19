package com.pr.pr.pantryraid;

import org.json.JSONArray;

import java.util.ArrayList;

public class step {
    int number;
    String step_description;
    ArrayList<ingredient> ingredients = new ArrayList<>();
    JSONArray equipment;

    public step(int number, String step_description, ArrayList<ingredient> ingredients, JSONArray equipment)
    {
        this.number = number;
        this.step_description = step_description;
        this.ingredients = ingredients;
        this.equipment = equipment;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStep_description() {
        return step_description;
    }

    public void setStep_description(String step_description) {
        this.step_description = step_description;
    }

    public ArrayList<ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public JSONArray getEquipment() {
        return equipment;
    }

    public void setEquipment(JSONArray equipment) {
        this.equipment = equipment;
    }
}
