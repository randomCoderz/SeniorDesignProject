package com.pr.pr.pantryraid;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Kan on 2/26/18.
 */
class cookBook extends Thread
{
    HttpResponse<JsonNode> response_return;
    String http;
    private String KEY = "";

    public cookBook(String key)
    {
        KEY = key;
        http = "";
    }

    /**
     * Searching for recipes by ingredients, ***doesnt work if ingredients are mispelled*** possible solution popup exception
     * @param fillIngredients Add information about the used and missing ingredients in each recipe.
     * @param ingredients list of ingredients that the recipes should contain.
     * @param limitLicense Whether to only show recipes with an attribution license.
     * @param number The maximal number of recipes to return
     * @param ranking Whether to maximize used ingredients (1) or minimize missing ingredients (2) first
     */
    public ArrayList<recipe> getRecipesByIngredients(boolean fillIngredients, String[] ingredients, boolean limitLicense, int number, int ranking) throws InterruptedException, JSONException {

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

        ArrayList<recipe> recipeList = new ArrayList<>();

        start();
        join();
        HttpResponse<JsonNode> response = response_return;

        JSONArray array = response.getBody().getArray();
        for(int i = 0; i < array.length(); i++)
        {
            JSONObject recipe = array.getJSONObject(i);
            int id = recipe.getInt("id");
            String name = recipe.getString("title");
            String image = recipe.getString("image");

            recipeList.add(new recipe(id, name, image, 0,null,null));
        }

        return recipeList;
    }

    public ArrayList<step> getInstructions(int recipeID, boolean stepBreakdown) throws InterruptedException, JSONException {

        http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/" +
                recipeID + "/analyzedInstructions?";
        http += "stepBreakdown=" + stepBreakdown;

        start();
        join();

        HttpResponse<JsonNode> response = response_return;
        JSONArray array = response.getBody().getArray();
        JSONObject obj = array.getJSONObject(0);
        JSONArray steps_array = obj.getJSONArray("steps");

        ArrayList<step> steps_list = new ArrayList<>();
        for(int i = 0; i < steps_array.length(); i++)
        {
            JSONObject step = steps_array.getJSONObject(i);
            int number = step.getInt("number");
            String step_description = step.getString("step");
            JSONArray ing_array = step.getJSONArray("ingredients");
            ArrayList<ingredient> ingredients = new ArrayList<>();
            for(int j = 0; j < ing_array.length(); i++)
            {
                JSONObject ingredient = ing_array.getJSONObject(j);
                int id = ingredient.getInt("id");
                String name = ingredient.getString("name");
                String url = ingredient.getString("image");
                ingredients.add(new ingredient(id, name, url));
            }
            JSONArray equipment = step.getJSONArray("equipment");
            steps_list.add(new step(number, step_description, ingredients, equipment));
        }
        return steps_list;
    }


    public void run() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(http)
                    .header("X-Mashape-Key", KEY)
                    .header("Accept", "application/json")
                    .asJson();
            setResponse(response);

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


}
