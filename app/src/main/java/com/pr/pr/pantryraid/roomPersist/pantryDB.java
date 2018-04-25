package com.pr.pr.pantryraid.RoomPersist;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.support.annotation.NonNull;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.pr.pr.pantryraid.ingredient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity
public class pantryDB extends Thread {

    //@ColumnInfo(name = "ingredient")
    @Ignore
    private ingredient i;

    @Ignore
    private HttpResponse<JsonNode> response_return;

    @ColumnInfo(name = "http")
    private String http;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "key")
    private String KEY;

    public pantryDB(){

    }

    public pantryDB(String key) {
       http = "";
        KEY = key;
    }

    public ArrayList<ingredient> searchIngredient(String intolerances, boolean metaInformation, int number, String query) throws InterruptedException, JSONException {
        http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/ingredients/autocomplete?";
        if(intolerances != null)
        {
            http += "intolerances=" + intolerances + "&";
        }
        http += "metaInformation=" + metaInformation + "&";
        if(number > 0)
        {
            http += "number=" + number + "&";
        }
        http += "query=" + query;

        ArrayList<ingredient> ingredientList = new ArrayList<>();

        start();
        join();
        HttpResponse<JsonNode> response = response_return;

        JSONArray array = response.getBody().getArray();
        for(int i = 0; i < array.length(); i++)
        {
            JSONObject ingredient = array.getJSONObject(i);
            String name = ingredient.getString("name");
            String image = "https://spoonacular.com/cdn/ingredients_100x100/";
            image += ingredient.getString("image");
            int id = ingredient.getInt("id");
            String aisle = ingredient.getString("aisle");
            //ingredientList.add(new ingredient(id, name, null, null, image, aisle));
        }
        return ingredientList;
    }

    public void run() {
        try  
        {

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

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public String getKEY() {
        return KEY;
    }

    public void setKEY(String KEY) {
        this.KEY = KEY;
    }
}
