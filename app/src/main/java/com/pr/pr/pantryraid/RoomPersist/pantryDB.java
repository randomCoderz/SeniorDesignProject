//package com.pr.pr.pantryraid.roomPersist;
//
//import android.arch.persistence.room.ColumnInfo;
//import android.arch.persistence.room.Entity;
//
//import com.mashape.unirest.http.HttpResponse;
//import com.mashape.unirest.http.JsonNode;
//import com.mashape.unirest.http.Unirest;
//import com.mashape.unirest.http.exceptions.UnirestException;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import com.pr.pr.pantryraid.ingredient;
//
//@Entity (tableName = "Pantry")
//public class pantryDB extends Thread{
//    @ColumnInfo(name = "ingredient")
//    ingredient i;
//
//    @ColumnInfo(name = "response")
//    private HttpResponse<JsonNode> response_return;
//
//    @ColumnInfo(name = "http")
//    private String http;
//
//    @ColumnInfo(name = "key")
//    private String KEY;
//
//    public pantryDB(String key) {
//        http = "";
//        KEY = key;
//    }
//
//    public ArrayList<ingredient> searchIngredient(String intolerances, boolean metaInformation, int number, String query) throws InterruptedException, JSONException {
//        http = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/food/ingredients/autocomplete?";
//        if(intolerances != null)
//        {
//            http += "intolerances=" + intolerances + "&";
//        }
//        http += "metaInformation=" + metaInformation + "&";
//        if(number > 0)
//        {
//            http += "number=" + number + "&";
//        }
//        http += "query=" + query;
//
//        ArrayList<ingredient> ingredientList = new ArrayList<>();
//
//        start();
//        join();
//        HttpResponse<JsonNode> response = response_return;
//
//        JSONArray array = response.getBody().getArray();
//        for(int i = 0; i < array.length(); i++)
//        {
//            JSONObject Ingredient = array.getJSONObject(i);
//            String name = Ingredient.getString("name");
//            String image = "https://spoonacular.com/cdn/ingredients_100x100/";
//            image += Ingredient.getString("image");
//            int id = Ingredient.getInt("id");
//            String aisle = Ingredient.getString("aisle");
//            ingredientList.add(new ingredient(id, name, null, null, image, aisle));
//        }
//        return ingredientList;
//    }
//
//    public void run() {
//        try
//        {
//
//            HttpResponse<JsonNode> response = Unirest.get(http)
//                    .header("X-Mashape-Key", KEY)
//                    .header("Accept", "application/json")
//                    .asJson();
//            setResponse(response);
//
//        } catch (UnirestException e) {
//            e.getStackTrace();
//        }
//    }
//
//    public void setResponse(HttpResponse<JsonNode> response) {
//        response_return = response;
//    }
//
//    public HttpResponse<JsonNode> getResponse() {
//        return response_return;
//    }
//
//    public ingredient getI() {
//        return i;
//    }
//
//    public void setI(ingredient i) {
//        this.i = i;
//    }
//
//    public HttpResponse<JsonNode> getResponse_return() {
//        return response_return;
//    }
//
//    public void setResponse_return(HttpResponse<JsonNode> response_return) {
//        this.response_return = response_return;
//    }
//
//    public String getHttp() {
//        return http;
//    }
//
//    public void setHttp(String http) {
//        this.http = http;
//    }
//
//    public String getKEY() {
//        return KEY;
//    }
//
//    public void setKEY(String KEY) {
//        this.KEY = KEY;
//    }
//}
