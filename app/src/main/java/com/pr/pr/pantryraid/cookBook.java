package com.pr.pr.pantryraid;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


/**
 * Created by Kan on 2/26/18.
 */
class cookBook extends Thread
{
    HttpResponse<JsonNode> response_return;
    String http;
    String command;
    private String KEY = "";

    public cookBook(String key)
    {
        KEY = key;
        http = "";
        command = "";
    }

    /**
     * Searching for recipes by ingredients, ***doesnt work if ingredients are mispelled*** possible solution popup exception
     * @param fillIngredients Add information about the used and missing ingredients in each recipe.
     * @param ingredients list of ingredients that the recipes should contain.
     * @param limitLicense Whether to only show recipes with an attribution license.
     * @param number The maximal number of recipes to return
     * @param ranking Whether to maximize used ingredients (1) or minimize missing ingredients (2) first
     */
    public void getRecipesByIngredients(boolean fillIngredients, String[] ingredients, boolean limitLicense, int number, int ranking)
    {
        command = "getRecipe";

        http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?";
        http += "fillIngredients=" + fillIngredients;
        http += "&ingredients=";
        for(int i = 0; i < ingredients.length; i++)
        {
            if(i != ingredients.length - 1)
            {
                http += (ingredients[i] + "%2C");
            }
            else
            {
                http += ingredients[i];
            }
        }
        http += "&limitLicense=" + limitLicense;
        http += "&number=" + number;
        http += "&ranking=" + ranking;


    }

    public void getInstructions(int recipeID, boolean stepBreakdown)
    {

        http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/" +
                recipeID + "/analyzedInstructions?";
        http += "stepBreakdown=" + stepBreakdown;

    }

//        public String[] getIngredients

    public void run() {
        try {
            if(command.equals("getRecipe"))
            {
                HttpResponse<JsonNode> response = Unirest.get(http)
                        .header("X-Mashape-Key", KEY)
                        .header("Accept", "application/json")
                        .asJson();
                setResponse(response);
            }

        } catch (UnirestException e) {
            e.getStackTrace();
        }
    }

    public void setResponse(HttpResponse<JsonNode> response) {
        response_return = response;
    }

    public HttpResponse<JsonNode> getResponse() {
        return response_return;
    }

    public void setCommand(String command_)
    {
        command = command_;
    }

}
