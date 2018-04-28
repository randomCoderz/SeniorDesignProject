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
     * Searches for recipes (Everything but query is optional)
     * @param cuisine The cuisine(s) of the recipes (african, chinese, japanese, korean, vietnamese, thai, indian, british, irish, french, italian, mexican, spanish, middle eastern, jewish, american, cajun, southern, greek, german, nordic, eastern european, caribbean, or latin american)
     * @param diet The diet to which the recipes must be compliant. Possible values are: pescetarian, lacto vegetarian, ovo vegetarian, vegan, and vegetarian.
     * @param excludeIngredients ingredients or ingredient types that should not be contained in recipes
     * @param instructionsRequired whether recipes must have instructions
     * @param intolerances list of intolerances (dairy, egg, gluten, peanut, sesame, seafood, shellfish, soy, sulfite, tree nut, and wheat)
     * @param limitLicense Whether the recipes should have an open license that allows for displaying with proper attribution.
     * @param number The number of results to return (between 0 and 100)
     * @param offset The number of results to skip (between 0 and 900).
     * @param query The (natural language) recipe search query.
     * @param type the type of the recipes. (main course, side dish, dessert, appetizer, salad, bread, breakfast, soup, beverage, sauce, or drink)
     */

    public ArrayList<recipe> searchRecipes(String[] cuisine, String diet[], String[] excludeIngredients, boolean instructionsRequired, String[] intolerances, boolean limitLicense, int number, int offset, String query, String type) throws InterruptedException, JSONException {

        http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/search?";
        if(cuisine != null)
        {
            http += "cuisine=";
            for(int i = 0; i < cuisine.length; i++)
            {
                if(i != cuisine.length - 1)
                {
                    http += (cuisine[i] + "%2C");
                }
                else
                {
                    http += cuisine[i] + "&";
                }
            }
        }
        if(diet != null)
        {
            http += "diet=";
            for(int i = 0; i < diet.length; i++)
            {
                if(i != diet.length - 1)
                {
                    http += (diet[i] + "%2C");
                }
                else
                {
                    http += diet[i] + "&";
                }
            }
        }
        if(excludeIngredients != null)
        {
            http += "excludeIngredients=";
            for(int i = 0; i < excludeIngredients.length; i++)
            {
                if(i != excludeIngredients.length - 1)
                {
                    http += (excludeIngredients[i] + "%2C");
                }
                else
                {
                    http += excludeIngredients[i] + "&";
                }
            }
        }
        if(instructionsRequired)
            http += "instructionsRequired=" + instructionsRequired + "&";
        if(intolerances != null)
        {
            http += "intolerances=";
            for(int i = 0; i < intolerances.length; i++)
            {
                if(i != intolerances.length - 1)
                {
                    http = http + (intolerances[i] + "%2C");
                }
                else
                {
                    http += intolerances[i] + "&";
                }
            }
        }
        if(limitLicense)
            http += "limitLicense=" + limitLicense + "&";
        if(number > 0)
            http += "number=" + number;
        if(offset > 0)
        {
            http += "offset=" + offset;
        }
        http += "&query=" + query;
        if(type != null)
        {
            http +="&type=" + type;
        }

        ArrayList<recipe> recipeList = new ArrayList<>();

        start();
        join();
        HttpResponse<JsonNode> response = response_return;

        JSONObject object = response.getBody().getObject();
        JSONArray array = object.getJSONArray("results");
        for(int i = 0; i < array.length(); i++)
        {
            JSONObject recipe = array.getJSONObject(i);
            int id = recipe.getInt("id");
            String title = recipe.getString("title");

            String image = "https://spoonacular.com/recipeImages/";
            image += recipe.getString("image");


            int readyInMinutes = recipe.getInt("readyInMinutes");
            recipeList.add(new recipe(id, title, image, readyInMinutes, null, null, null, false, false, 0, 0, 0));
        }
        return recipeList;
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

            recipeList.add(new recipe(id, name, image, 0,null,null, null, false, false, 0, 0, 0));
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
