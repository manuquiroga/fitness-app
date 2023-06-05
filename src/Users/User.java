package Users;

import Exceptions.TooFewMealsException;
import FoodModels.Food;
import FoodModels.FoodType;
import Handlers.FileHandler;
import Handlers.JSONHandler;
import Interfaces.IToJSON;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class User implements IToJSON, Comparable {

    private final int CALORIE_DEFICIT = 300;
    private final int CALORIE_SURPLUS = 500;
    private final int MAX_MEAL_QUANTITY = 9;
    private final int MIN_MEAL_QUANTITY = 4;

    private String name;
    private String password;
    private String email;
    private int id;
    private UserData userData;


    //constructors:
    public User(String name, String password, String email, int id, UserData userData) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = id;
        this.userData = userData;
    }

    public User() {
    }

    //Getters:


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public UserData getUserData() {
        return userData;
    }

    //Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }


    //equals, hashcode, compareTo, toString

    @Override
    public boolean equals(Object obj)
    {
        boolean rta=false;
        if(obj!=null)
        {
            if(obj instanceof User)
            {
                User aux=(User) obj;
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
        if(obj instanceof User)
        {
            User aux=(User) obj;
            if(getUserData().getAge()>aux.getUserData().getAge())
            {
                rta=1;
            }else{
                rta=-1;
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
        return "User{" + '\n' +
                "name: " + name + '\n' +
                "password: " + password + '\n' +
                "email: " + email + '\n' +
                "id: " + id + '\n' +
                "userData: " + userData.toString() +
                '}';
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("password", password);
        json.put("email", email);
        json.put("id", id);
        json.put("userData", userData.toJSON());
        return json;
    }

    public int getCaloriesObjective(){
        int bmr = userData.calculateBMR();
        int caloriesObjective = 0;

        switch (userData.getObjective()){
            case LOSE_WEIGHT -> caloriesObjective = bmr - CALORIE_DEFICIT;
            case MAINTAIN_WEIGHT -> caloriesObjective = bmr;
            case GAIN_WEIGHT -> caloriesObjective = bmr + CALORIE_SURPLUS;
        }
        return caloriesObjective;
    }

    //ask for a string depending on whether you are celiac, vegan or vegetarian. the words will be limited to the user (classic, vegan, vegetarian, celiac), we also limit the meal quantity number
    public void generateDiet(int mealsQuantity, String dietType) {

    }

    public void getClassicDiet(int mealsQuantity){
        ArrayList<Food> allFoods = JSONHandler.readFoodFile();
        ArrayList<Food> breakfasts = getBreakfastList(allFoods);
        ArrayList<Food> meals = getMealList(allFoods);
        ArrayList<Food> snacks = getSnackList(allFoods);


        int caloriesObjective = getCaloriesObjective();
        int actualCalories = 0;
        Set<Food> foodSet = new HashSet<>();

        int breakfastCount = 0, mealCount = 0, snackCount = 0;

        do{
            for (int i= 0; i<mealsQuantity; i++){
                Random random = new Random();
                ArrayList<Integer> randomIndex = generateRandomIndexArray(mealsQuantity, (allFoods.size()-1));
                int calculateSnackCount = mealsQuantity - 3; //3 is total of meals + breakfast

                random.nextInt(allFoods.size()-1);
                if(breakfastCount < 1){
                    foodSet.add(breakfasts.get(randomIndex.get(i)));
                    breakfastCount++;
                }else if(mealCount < 2){
                    foodSet.add(meals.get(randomIndex.get(i)));
                    mealCount++;
                }else if(snackCount < calculateSnackCount ){
                    foodSet.add(snacks.get(randomIndex.get(i)));
                    snackCount++;
                }
            }

        } while(caloriesObjective > actualCalories); //TODO: this

    }
    public void getVegetarianDiet(){
        ArrayList<Food> allFoods = JSONHandler.readFoodFile();
        int randomIndex = (int) (Math.random() % (allFoods.size()-1));

        ArrayList<Food> vegetarianFoods = new ArrayList<>();
        for (Food food:allFoods) {
            if(food.isVegetarian()){
                vegetarianFoods.add(food);
            }
        }

        int caloriesObjective = getCaloriesObjective();
    }
    public void getVeganDiet(){
        ArrayList<Food> allFoods = JSONHandler.readFoodFile();
        int randomIndex = (int) (Math.random() % (allFoods.size()-1));
        ArrayList<Food> veganFoods = new ArrayList<>();
        for (Food food:allFoods) {
            if(food.isVegan()){
                veganFoods.add(food);
            }
        }

        int caloriesObjective = getCaloriesObjective();
    }
    public void getCeliacDiet(){
        ArrayList<Food> allFoods = JSONHandler.readFoodFile();
        int randomIndex = (int) (Math.random() % (allFoods.size()-1));
        ArrayList<Food> celiacFoods = new ArrayList<>();
        for (Food food:allFoods) {
            if(food.isCeliac()){
                celiacFoods.add(food);
            }
        }

        int caloriesObjective = getCaloriesObjective();
    }

    private ArrayList<Food> getBreakfastList(ArrayList<Food> allFoods){
        ArrayList<Food> breakfasts = new ArrayList<>();
        for (Food food:allFoods) {
            if(food.getFoodType().equals(FoodType.BREAKFAST)){
                breakfasts.add(food);
            }
        }
        return breakfasts;
    }

    private ArrayList<Food> getSnackList(ArrayList<Food> allFoods){
        ArrayList<Food> snacks = new ArrayList<>();
        for (Food food:allFoods) {
            if(food.getFoodType().equals(FoodType.SNACK)){
                snacks.add(food);
            }
        }
        return snacks;
    }
    private ArrayList<Food> getMealList(ArrayList<Food> allFoods){
        ArrayList<Food> meals = new ArrayList<>();
        for (Food food:allFoods) {
            if(food.getFoodType().equals(FoodType.MEAL)){
                meals.add(food);
            }
        }
        return meals;
    }

    private ArrayList<Integer> generateRandomIndexArray(int mealQ, int bound){
        Random random = new Random();
        ArrayList<Integer> generatedNumbers = new ArrayList<>();


        int count = 0;
        while (count<mealQ){
            int randomNumber = random.nextInt(bound);

            if(!generatedNumbers.contains(randomNumber)){
                generatedNumbers.add(randomNumber);
                count++;
            }
        }
        return generatedNumbers;
    }
}
