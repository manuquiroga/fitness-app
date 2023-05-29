package FoodModels;

import Interfaces.IToJSON;

import java.util.ArrayList;

public class Food implements IToJSON {
    private int id;
    private String name;
    private double calories;
    private double protein;
    private double fat;
    private double carbohydrates;
    private ArrayList<String> ingredients;
    private double servingSize;
    private foodType foodType;

    //constructors:

    public Food(int id, String name, double calories, double servingSize, foodType foodType) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.servingSize = servingSize;
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

    public foodType getFoodType() {
        return foodType;
    }

    //equals, hashCode, compareTo, toString

    //Methods:
    //None
}
