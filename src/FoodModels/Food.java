package FoodModels;

import java.util.ArrayList;

public class Food {
    private int id;
    private String name;
    private double calories;
    private ArrayList<String> ingredients;
    private double servingSize;
    private NutritionalValue nutritionalValue;
    private foodType foodType;

    //constructors:

    public Food(int id, String name, double calories, double servingSize, NutritionalValue nutritionalValue, Enum foodType) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.servingSize = servingSize;
        this.nutritionalValue = nutritionalValue;
        this.foodType = (FoodModels.foodType) foodType;
        ingredients=new ArrayList<>();
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

    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

    public foodType getFoodType() {
        return foodType;
    }

    //equals, hashCode, compareTo, toString

    //Methods:
    //None
}
