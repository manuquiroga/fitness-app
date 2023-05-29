package FoodModels;

import Interfaces.IToJSON;
import Interfaces.JSONException;
import Interfaces.JSONObject;

import java.util.ArrayList;

public class Food implements IToJSON {
    private int id;
    private String name;
    private double calories;
    private double protein;
    private double fat;
    private double carbohydrates;
    private double servingSize;
    private foodType foodType;

    //constructors:

    public Food(int id, String name, double calories, double servingSize, foodType foodType) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.servingSize = servingSize;
        this.foodType = (FoodModels.foodType) foodType;
    }

    public Food() {
    }

    //Getters:


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getServingSize() {
        return servingSize;
    }

    public foodType getFoodType() {
        return foodType;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", name);
        json.put("calories", calories);
        json.put("servingSize", servingSize);
        json.put("foodType", foodType);
        json.put("protein", protein);
        json.put("fat", fat);
        json.put("carbohydrates", carbohydrates);
        return json;
    }

    //equals, hashCode, compareTo, toString

    //Methods:
    //None
}
