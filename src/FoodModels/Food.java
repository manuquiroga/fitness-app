package FoodModels;

import Interfaces.IFromJSON;
import Interfaces.IToJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a food item with its nutritional information and properties.
 */
public class Food implements IToJSON, Comparable, IFromJSON {
    private int id;
    private String name;
    private double calories;
    private double proteins_g;
    private double fats_g;
    private double carbohydrates_g;
    private double servingSize_g;
    private FoodType foodType;
    private Set<String> ingredients;
    private boolean isVegan;
    private boolean isCeliac;
    private boolean isVegetarian;


    /**
     * Constructs a Food object with the specified parameters.
     * @param id the ID of the food
     * @param name the name of the food
     * @param calories the calorie content of the food
     * @param proteins_g the protein content of the food
     * @param fats_g the fat content of the food
     * @param carbohydrates_g the carbohydrate content of the food
     * @param servingSize_g the serving size of the food in grams
     * @param foodType the type of the food
     * @param isVegan indicates if the food is suitable for vegans
     * @param isCeliac indicates if the food is suitable for celiacs
     * @param isVegetarian indicates if the food is suitable for vegetarians
     */
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

    /**
     * Constructs an empty Food object with an empty ingredient set.
     */
    public Food() {
        this.ingredients = new HashSet<>();
    }

    /**
     * Adds an ingredient to the food's ingredient set.
     * @param ingredient the ingredient to be added
     */
    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    /**
     * Returns the protein content of the food.
     * @return the protein content in grams
     */
    public double getProteins_g() {
        return proteins_g;
    }

    /**
     * Returns the fat content of the food.
     * @return the fat content in grams
     */
    public double getFats_g() {
        return fats_g;
    }

    /**
     * Returns the carbohydrate content of the food.
     * @return the carbohydrate content in grams
     */
    public double getCarbohydrates_g() {
        return carbohydrates_g;
    }

    /**
     * Checks if the food is suitable for vegans.
     * @return true if the food is vegan, false otherwise
     */
    public boolean isVegan() {
        return isVegan;
    }

    /**
     * Checks if the food is suitable for celiacs.
     * @return true if the food is celiac-friendly, false otherwise
     */
    public boolean isCeliac() {
        return isCeliac;
    }

    /**
     * Checks if the food is suitable for vegetarians.
     * @return true if the food is vegetarian-friendly, false otherwise
     */
    public boolean isVegetarian() {
        return isVegetarian;
    }

    /**
     * Returns the ID of the food.
     * @return the food ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the food.
     * @return the food name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the calorie content of the food.
     * @return the calorie content
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Returns the serving size of the food in grams.
     * @return the serving size in grams
     */
    public double getServingSize_g() {
        return servingSize_g;
    }

    /**
     * Returns the type of the food.
     * @return the food type
     */
    public FoodType getFoodType() {
        return foodType;
    }

    /**
     * Returns the set of ingredients in the food.
     * @return the set of ingredients
     */
    public Set<String> getIngredients() {
        return ingredients;
    }

    /**
     * Sets the ID of the food.
     * @param id the food ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the name of the food.
     * @param name the food name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the calorie content of the food.
     * @param calories the calorie content
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * Sets the protein content of the food.
     * @param proteins_g the protein content in grams
     */
    public void setProteins_g(double proteins_g) {
        this.proteins_g = proteins_g;
    }

    /**
     * Sets the fat content of the food.
     * @param fats_g the fat content in grams
     */
    public void setFats_g(double fats_g) {
        this.fats_g = fats_g;
    }

    /**
     * Sets the carbohydrate content of the food.
     * @param carbohydrates_g the carbohydrate content in grams
     */
    public void setCarbohydrates_g(double carbohydrates_g) {
        this.carbohydrates_g = carbohydrates_g;
    }

    /**
     * Sets the serving size of the food in grams.
     * @param servingSize_g the serving size in grams
     */
    public void setServingSize_g(double servingSize_g) {
        this.servingSize_g = servingSize_g;
    }

    /**
     * Sets the type of the food.
     * @param foodType the food type
     */
    public void setFoodType(String foodType) {
        if (foodType.equals("MEAL")) {
            this.foodType = FoodType.MEAL;
        } else if (foodType.equals("BREAKFAST")) {
            this.foodType = FoodType.BREAKFAST;
        } else if (foodType.equals("SNACK")) {
            this.foodType = FoodType.SNACK;
        }
    }

    /**
     * Sets whether the food is suitable for vegans.
     * @param isVegan true if the food is vegan, false otherwise
     */
    public void setVegan(boolean isVegan) {
        this.isVegan = isVegan;
    }

    /**
     * Sets whether the food is suitable for celiacs.
     * @param isCeliac true if the food is celiac-friendly, false otherwise
     */
    public void setCeliac(boolean isCeliac) {
        this.isCeliac = isCeliac;
    }

    /**
     * Sets whether the food is suitable for vegetarians.
     * @param isVegetarian true if the food is vegetarian-friendly, false otherwise
     */
    public void setVegetarian(boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
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

    @Override
    public void fromJSON(JSONObject jo) throws JSONException
    {
        setName(jo.getString("name"));
        setFoodType(jo.getString("type"));
        setId(jo.getInt("id"));
        setServingSize_g(jo.getInt("servingSize_g"));
        setCalories(jo.getDouble("calories"));
        setProteins_g(jo.getDouble("proteins_g"));
        setCarbohydrates_g(jo.getDouble("carbohydrates_g"));
        setFats_g(jo.getDouble("fats_g"));
        setVegan(jo.getBoolean("isVegan"));
        setCeliac(jo.getBoolean("isCeliac"));
        setVegetarian(jo.getBoolean("isVegetarian"));

        JSONArray jaIngredients = jo.getJSONArray("ingredients");
        for (int j = 0; j < jaIngredients.length(); j++) {
            addIngredient(jaIngredients.getString(j));
        }
    }


    //equals, hashCode, compareTo, toString
    /**
     * Overrides the equals method to compare two Food objects for equality.
     * The comparison is based on the ID of the Food.
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        boolean rta = false;
        if (obj != null) {
            if (obj instanceof Food) {
                Food aux = (Food) obj;
                if (getId() == aux.getId()) {
                    rta = true;
                }
            }
        }
        return rta;
    }
    /**
     * Overrides the compareTo method to compare two Food objects based on their calorie values.
     * Returns a positive integer if this Food has higher calories, a negative integer if it has lower calories,
     * and zero if the calories are equal.
     * @param obj the object to compare with
     * @return an integer indicating the comparison result
     * @throws IllegalArgumentException if the object is not an instance of Food
     */
    @Override
    public int compareTo(Object obj) {
        int rta = 0;
        if (obj != null) {
            if (obj instanceof Food) {
                Food aux = (Food) obj;
                if (getCalories() > aux.getCalories()) {
                    rta = 1;
                } else {
                    rta = -1;
                }
            }
        }
        return rta;
    }
    /**
     * Returns the hash code value for the Food object.
     * This implementation always returns the same hash code (1) for all Food objects.
     * @return the constant hash code value (1)
     */
    @Override
    public int hashCode() {
        return 1;
    }
    /**
     * Returns a string representation of the Food object.
     * @return a string representation of the Food object
     */
    @Override
    public String toString() {
        return "\n-----\n" +
                "Food:" +
                "ID=" + id + '\n' +
                "Name='" + name + '\n' +
                "Calories=" + calories + '\n' +
                "Proteins_g=" + proteins_g + '\n' +
                "Fats_g=" + fats_g + '\n' +
                "Carbohydrates_g=" + carbohydrates_g + '\n' +
                "ServingSize_g=" + servingSize_g + '\n' +
                "FoodType=" + foodType + '\n' +
                "Ingredients=" + ingredients + '\n' +
                "IsVegan=" + isVegan + '\n' +
                "IsCeliac=" + isCeliac + '\n' +
                "IsVegetarian=" + isVegetarian;
    }
}
