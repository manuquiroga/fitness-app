package FoodModels;

import Interfaces.IToFile;
import Interfaces.IToJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Food implements IToJSON, Comparable {
    private int id;
    private String name;
    private double calories;
    private double proteins_g;
    private double fats_g;
    private double carbohydrates_g;
    private double servingSize_g;
    private FoodType foodType;
    private Set<String> ingredients; //TODO: make set
    private boolean isVegan;
    private boolean isCeliac;
    private boolean isVegetarian;

    //constructors:

    public Food(int id, String name, double calories, double proteins_g, double fats_g, double carbohydrates_g, double servingSize_g, FoodType foodType, boolean isVegan, boolean isCeliac, boolean isVegetarian) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.proteins_g = proteins_g;
        this.fats_g = fats_g;
        this.carbohydrates_g = carbohydrates_g;
        this.servingSize_g = servingSize_g;
        this.foodType = foodType;
        this.isVegan = isVegan;
        this.isCeliac = isCeliac;
        this.isVegetarian = isVegetarian;
        this.ingredients = new HashSet<>();
    }

    public Food() {
        this.ingredients = new HashSet<>();
    }

    //Getters:

    public void addIngredient(String ingredient){
        ingredients.add(ingredient);
    }
    public double getproteins_g() {
        return proteins_g;
    }

    public double getfats_g() {
        return fats_g;
    }

    public double getCarbohydrates_g() {
        return carbohydrates_g;
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

    public double getServingSize_g() {
        return servingSize_g;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public Set<String> getIngredients(){
        return ingredients;
    }

    //Setters:


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setProteins_g(double proteins_g) {
        this.proteins_g = proteins_g;
    }

    public void setFats_g(double fats_g) {
        this.fats_g = fats_g;
    }

    public void setCarbohydrates_g(double carbohydrates_g) {
        this.carbohydrates_g = carbohydrates_g;
    }

    public void setServingSize_g(double servingSize_g) {
        this.servingSize_g = servingSize_g;
    }

    public void setFoodType(String foodType) {
        if(foodType.equals("MEAL")){
            this.foodType=FoodType.MEAL;
        } else if (foodType.equals("BREAKFAST")) {
            this.foodType=FoodType.BREAKFAST;
        } else if (foodType.equals("SNACK")) {
            this.foodType=FoodType.SNACK;
        }
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public void setCeliac(boolean celiac) {
        isCeliac = celiac;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        
        JSONObject json = new JSONObject();
        JSONArray ja_ingredients = new JSONArray();
        json.put("id", id);
        json.put("name", name);
        json.put("calories", calories);
        json.put("servingSize_g", servingSize_g);
        json.put("type", foodType.name());
        json.put("proteins_g", proteins_g);
        json.put("fats_g", fats_g);
        json.put("carbohydrates_g", carbohydrates_g);
        json.put("isVegan", isVegan);
        json.put("isCeliac", isCeliac);
        json.put("isVegetarian", isVegetarian);
        for (String s:ingredients) {
            ja_ingredients.put(s);
        }
        json.put("ingredients", ja_ingredients);
        return json;
    }


    //equals, hashCode, compareTo, toString
    @Override
    public boolean equals (Object obj){
        boolean rta=false;
        if(obj!=null)
        {
            if(obj instanceof Food)
            {
                Food aux=(Food) obj;
                if(getId()==aux.getId())
                {
                    rta=true;
                }
            }
        }
        return rta;
    }

    @Override
    public int compareTo(Object obj)
    {
        int rta=0;
        if(obj!=null)
        {
            if(obj instanceof Food)
            {
                Food aux=(Food)obj;
                if(getCalories()>aux.getCalories())
                {
                    rta=1;
                }else{
                    rta=-1;
                }
            }
        }
        return rta;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "\n-----\n"+
                "Food:" +
                "ID=" + id +'\n'+
                "Name='" + name + '\n' +
                "Calories=" + calories + '\n'+
                "Proteins_g=" + proteins_g + '\n'+
                "Fats_g=" + fats_g + '\n'+
                "Carbohydrates_g=" + carbohydrates_g + '\n'+
                "ServingSize_g=" + servingSize_g + '\n'+
                "FoodType=" + foodType + '\n'+
                "Ingredients=" + ingredients + '\n'+
                "IsVegan=" + isVegan + '\n'+
                "IsCeliac=" + isCeliac + '\n'+
                "IsVegetarian=" + isVegetarian;
    }

    //Methods:


    //None
}
