package API;

import FoodModels.Food;
import FoodModels.FoodType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONHandler {

    public static ArrayList<Food> readFoodFile()
    {
        String jsonResponse = FileHandler.read("food");

        ArrayList<Food> foodList = new ArrayList<>();

        try{
            JSONObject jsonObjectFood = new JSONObject(jsonResponse);
            JSONArray jsonArrayFood = jsonObjectFood.getJSONArray("Foods");

            for (int i = 0; i < jsonArrayFood.length(); i++) {
                JSONObject joFromFoodsArray = jsonArrayFood.getJSONObject(i);
                Food food=new Food();
                food.setName(joFromFoodsArray.getString("name"));
                food.setFoodType(joFromFoodsArray.getString("foodType"));
                food.setId(joFromFoodsArray.getInt("id"));
                food.setCalories(joFromFoodsArray.getDouble("calories"));
                food.setProtein(joFromFoodsArray.getDouble("protein"));
                food.setCarbohydrates(joFromFoodsArray.getDouble("carbohydrates"));
                food.setFat(joFromFoodsArray.getDouble("fat"));
                food.setVegan(joFromFoodsArray.getBoolean("isVegan"));
                food.setCeliac(joFromFoodsArray.getBoolean("isCeliac"));
                food.setVegetarian(joFromFoodsArray.getBoolean("isVegetarian"));
                foodList.add(food);
            }

    }catch (JSONException e)
        {
            System.err.println("Wrong formulated JSON");
        }
}
