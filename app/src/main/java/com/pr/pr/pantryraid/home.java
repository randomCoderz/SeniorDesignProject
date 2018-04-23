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
 * Created by Kan on 2/25/18.
 */
class home extends Thread
{
    private HttpResponse<JsonNode> response_return;
    private String http;
    private String KEY;

    public home(String key) {
        http = "";
        KEY = key;
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
            recipeList.add(new recipe(id, title, image, readyInMinutes, null, null, null));
        }
        return recipeList;
    }

    /**
     * Searches for recipes (Everything but query is optional)
     * @param instructionsRequired whether recipes must have instructions
     * @param number The number of results to return (between 0 and 100)
     * @param query The (natural language) recipe search query.
     */
    public ArrayList<recipe> searchRecipes(boolean instructionsRequired, int number, String query) throws InterruptedException, JSONException {

        http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/search?";

        http += "instructionsRequired=" + instructionsRequired + "&";
        http += "number=" + number + "&";
        http += "query=" + query;

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
            recipeList.add(new recipe(id, title, image, readyInMinutes, null, null, null));
        }
        return recipeList;
    }


    /**
     * find random (popular) recipes
     * @param limitLicense Whether the recipes should have an open license that allows for displaying with proper attribution
     * @param number The number of random recipes to be returned. Must be in interval [1,100]. NOTE: Each random recipe returned counts as one request.
     * @param tags Tags that the random recipe(s) must adhere to
     */
    public ArrayList<recipe> randomRecipe(boolean limitLicense, int number, String[] tags) throws InterruptedException, JSONException {

        http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/random?";
        http += "limitLicense=" + limitLicense;
        http += "&number=" + number;
        if(tags != null)
        {
            http += "&tags=";
            for(int i = 0; i < tags.length; i++)
            {
                if(i != tags.length - 1)
                {
                    http += (tags[i] + "%2C");
                }
                else
                {
                    http += tags[i];
                }
            }
        }

        ArrayList<recipe> recipeList = new ArrayList<>();

        start();
        join();
        HttpResponse<JsonNode> response = response_return;

        JSONArray array = response.getBody().getObject().getJSONArray("recipes");
//        System.out.println(array.toString(2));
        for(int i = 0; i < array.length(); i++)
        {
            JSONObject object = array.getJSONObject(i);
            int id = object.getInt("id");
            String title = object.getString("title");
            String image = "";
            if(object.has("image"))
                image = object.getString("image");
            int readyInMinutes = object.getInt("readyInMinutes");
            String instructions = object.getString("instructions");

            ArrayList<ingredient> ingredients = new ArrayList<>();
            JSONArray ingredient_array = object.getJSONArray("extendedIngredients");

            for(int j = 0; j < ingredient_array.length(); j++)
            {
                JSONObject ingredient = ingredient_array.getJSONObject(j);
                int ingredient_id = 0;
                if(ingredient.has("id"))
                    ingredient_id = ingredient.getInt("id");
                String ingredient_name = ingredient.getString("name");
                String amount = ingredient.getString("amount");
                String unit = ingredient.getString("unit");

                String ingredient_image = "";
                if(ingredient.has("image"))
                    ingredient_image = ingredient.getString("image");
                ingredients.add(new ingredient(ingredient_id, ingredient_name, amount, unit, ingredient_image, 0, false));
            }
            ArrayList<step> analyzedInstructions = new ArrayList();
            JSONArray ai = object.getJSONArray("analyzedInstructions");
            if(ai.length() > 0)
            {
                JSONArray s = ai.getJSONObject(0).getJSONArray("steps");

                for(int k = 0; k < s.length(); k++)
                {
                    int num = s.getJSONObject(k).getInt("number");
                    String step_description = s.getJSONObject(k).getString("step");
                    JSONArray ing = s.getJSONObject(k).getJSONArray("ingredients");
                    ArrayList<ingredient> step_ingredients = new ArrayList();
                    for(int l = 0; l < ing.length(); l++)
                    {
                        int id_ = ing.getJSONObject(l).getInt("id");
                        String name = ing.getJSONObject(l).getString("name");
                        String url = ing.getJSONObject(l).getString("image");
                        step_ingredients.add(new ingredient(id_, name, url));
                    }
                    JSONArray equipment = s.getJSONObject(k).getJSONArray("equipment");
                    analyzedInstructions.add(new step(num, step_description, step_ingredients, equipment));
                }
            }


            recipeList.add(new recipe(id, title, image, readyInMinutes, ingredients, analyzedInstructions, instructions));

        }
        return recipeList;
    }

    /**
     * method to get analyzed recipes because some methods don't return enough information
     * @param id the id of the recipe
     * @param stepBreakdown Whether to break down the recipe steps even more.
     * @return return a arraylist of steps to complete a recipe
     * @throws InterruptedException
     * @throws JSONException
     */
    public ArrayList<step> getAnalyzedInstructions(int id, boolean stepBreakdown) throws InterruptedException, JSONException {
        http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/" + id + "/analyzedInstructions?";
        http += "stepBreakdown=" + stepBreakdown;

        start();
        join();

        HttpResponse<JsonNode> response = response_return;

        ArrayList<step> analyzedInstructions = new ArrayList<step>();
        JSONArray s = response.getBody().getArray().getJSONObject(0).getJSONArray("steps");
        for(int k = 0; k < s.length(); k++) {
            int num = s.getJSONObject(k).getInt("number");
            String step_description = s.getJSONObject(k).getString("step");
            JSONArray ing = s.getJSONObject(k).getJSONArray("ingredients");
            ArrayList<ingredient> step_ingredients = new ArrayList();
            for (int l = 0; l < ing.length(); l++) {
                int id_ = ing.getJSONObject(l).getInt("id");
                String name = ing.getJSONObject(l).getString("name");
                String url = ing.getJSONObject(l).getString("image");
                step_ingredients.add(new ingredient(id_, name, url));
            }
            JSONArray equipment = s.getJSONObject(k).getJSONArray("equipment");
            analyzedInstructions.add(new step(num, step_description, step_ingredients, equipment));
        }

        return analyzedInstructions;
    }

    public void run() {
        try
        {
                HttpResponse<JsonNode> response = Unirest.get(http)
                        .header("X-Mashape-Key", KEY)
                        .header("Accept", "application/json")
                        .asJson();
                setResponse(response);
        }
        catch (UnirestException e)
        {
            e.getStackTrace();
        }
    }

    public void setResponse(HttpResponse<JsonNode> response) {
        response_return = response;
    }


}
