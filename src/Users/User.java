package Users;

import Exceptions.TooFewMealsException;
import FoodModels.Food;
import FoodModels.FoodType;
import Handlers.FileHandler;
import Handlers.JSONHandler;
import Interfaces.IToJSON;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.*;

public class User implements IToJSON, Comparable {

    private final int CALORIE_DEFICIT = 350;
    private final int CALORIE_SURPLUS = 500;
    private String name;
    private String password;
    private String email;
    private UUID id;
    private UserData userData;


    //constructors:
    public User(String name, String password, String email, UserData userData) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = UUID.randomUUID();
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

    public UUID getId() {
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

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public void setId(String id){
        this.id = UUID.fromString(id);
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
                if(getId().equals(aux.getId()))
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
                "id: " + id.toString() + '\n' +
                "userData: " + userData.toString() +
                '}';
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        String idNew = id.toString();
        json.put("name", name);
        json.put("password", password);
        json.put("email", email);
        json.put("id", idNew);
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
    
    public void generateDiet(int mealsQuantity, String type){
        ArrayList<Food> allFoods = new ArrayList<>();
        ArrayList<Food> foodArrayList;

        int breakfastCount, mealCount, snackCount;
        int calculateSnackCount = mealsQuantity - 3;
        int caloriesObjective = getCaloriesObjective();
        int actualCalories;
        boolean dif;
        
        switch (type.toLowerCase()){
            case "classic" -> allFoods = JSONHandler.readFoodFile();
            case "vegan" -> allFoods = getVeganFoods();
            case "vegetarian" -> allFoods = getVegetarianFoods();
            case "celiac" -> allFoods = getCeliacFoods();
            //TODO default -> throw new WrongFoodTypeException();
            //TODO: celiac-vegetarian and celiac-vegan diets
        }

        ArrayList<Food> breakfasts = getBreakfastList(allFoods);
        ArrayList<Food> meals = getMealList(allFoods);
        ArrayList<Food> snacks = getSnackList(allFoods);

        do{
            actualCalories = 0; breakfastCount= 0; mealCount= 0; snackCount = 0;
            foodArrayList = new ArrayList<>();

            ArrayList<Integer> randomBreakfastIndex = generateRandomIndexArray(mealsQuantity, (breakfasts.size()-1));
            ArrayList<Integer> randomMealIndex = generateRandomIndexArray(mealsQuantity, (meals.size()-1));
            ArrayList<Integer> randomSnackIndex = generateRandomIndexArray(mealsQuantity, (snacks.size()-1));


            for (int i= 0; i<mealsQuantity; i++){

                 //3 is total of meals + breakfast

                if(breakfastCount < 1){
                    Food aux = breakfasts.get(randomBreakfastIndex.get(i));
                    actualCalories += aux.getCalories();

                    foodArrayList.add(aux);
                    breakfastCount++;

                }else if(mealCount < 2){
                    Food aux = meals.get(randomMealIndex.get(i));
                    actualCalories += aux.getCalories();

                    foodArrayList.add(aux);
                    mealCount++;

                }else if(snackCount < calculateSnackCount ){
                    Food aux = snacks.get(randomSnackIndex.get(i));
                    actualCalories += aux.getCalories();

                    foodArrayList.add(aux);
                    snackCount++;
                }
            }

            dif = ((actualCalories-caloriesObjective) <= 80 && (actualCalories-caloriesObjective) >= 0);
        } while(!dif);

        userData.setDiet(foodArrayList);
    }
    public ArrayList<Food> getVegetarianFoods(){
        ArrayList<Food> allFoods = JSONHandler.readFoodFile();

        ArrayList<Food> vegetarianFoods = new ArrayList<>();
        for (Food food:allFoods) {
            if(food.isVegetarian()){
                vegetarianFoods.add(food);
            }
        }

        return vegetarianFoods;
    }
    public ArrayList<Food> getVeganFoods(){
        ArrayList<Food> allFoods = JSONHandler.readFoodFile();

        ArrayList<Food> veganFoods = new ArrayList<>();
        for (Food food:allFoods) {
            if(food.isVegan()){
                veganFoods.add(food);
            }
        }

        return veganFoods;
    }
    public ArrayList<Food> getCeliacFoods(){
        ArrayList<Food> allFoods = JSONHandler.readFoodFile();

        ArrayList<Food> celiacFoods = new ArrayList<>();
        for (Food food:allFoods) {
            if(food.isCeliac()){
                celiacFoods.add(food);
            }
        }

        return celiacFoods;
    }

    public void resetDiet(){
        userData.setDiet(new ArrayList<>());
    }

    public boolean hasDiet(){
        return (userData.getDiet().size() > 0);
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
