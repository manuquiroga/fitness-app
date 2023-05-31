package FoodModels;

import java.io.*;

import Interfaces.IToFile;
import Interfaces.IToJSON;
import org.json.JSONException;
import org.json.JSONObject;


public class Food implements IToJSON, IToFile {
    private int id;
    private String name;
    private double calories;
    private double protein;
    private double fat;
    private double carbohydrates;
    private double servingSize;
    private foodType foodType;

    private boolean isVegan;
    private boolean isCeliac;
    private boolean isVegetarian;

    //constructors:

    public Food(int id, String name, double calories, double protein, double fat, double carbohydrates, double servingSize, FoodModels.foodType foodType, boolean isVegan, boolean isCeliac, boolean isVegetarian) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.servingSize = servingSize;
        this.foodType = foodType;
        this.isVegan = isVegan;
        this.isCeliac = isCeliac;
        this.isVegetarian = isVegetarian;
    }

    public Food() {
    }

    //Getters:
    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public boolean isCeliac() {
        return isCeliac;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }
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

    @Override
    public void toFile() throws JSONException, IOException {

    }

    //equals, hashCode, compareTo, toString

    //Methods:
    //None
}
