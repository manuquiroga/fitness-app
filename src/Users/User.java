package Users;

import FoodModels.Food;
import FoodModels.FoodType;
import Handlers.JSONHandler;
import Interfaces.IFromJSON;
import Interfaces.IToJSON;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.*;

/**
 * This abstract class is the main class for users, where their important registration data and UserData are stored
 */
public abstract class User implements IToJSON, Comparable, IFromJSON {

    private final int CALORIE_DEFICIT = 350;
    private final int CALORIE_SURPLUS = 500;
    private String name;
    private String password;
    private String email;
    private UUID id;
    private UserData userData;

    /**
     * Constructs a User object with the specified name, password, email, and user data.
     * @param name the name of the user
     * @param password the password of the user
     * @param email the email of the user
     * @param userData the user data associated with the user
     */
    public User(String name, String password, String email, UserData userData) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = UUID.randomUUID();
        this.userData = userData;
    }
    /**
     * Constructs an empty User object.
     */
    public User() {
    }
    /**
     * Returns the name of the user.
     * @return the name of the user
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the password of the user.
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }
    /**
     * Returns the email of the user.
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }
    /**
     * Returns the ID of the user.
     * @return the ID of the user
     */
    public UUID getId() {
        return id;
    }
    /**
     * Returns the user data associated with the user.
     * @return the user data
     */
    public UserData getUserData() {
        return userData;
    }
    /**
     * Sets the name of the user.
     * @param name the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Sets the password of the user.
     * @param password the new password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Sets the email of the user.
     * @param email the new email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Sets the user data associated with the user.
     * @param userData the new user data
     */
    public void setUserData(UserData userData) {
        this.userData = userData;
    }
    /**
     * Sets the ID of the user from a string representation.
     * @param id the string representation of the UUID to set as the ID
     */
    public void setId(String id) {
        this.id = UUID.fromString(id);
    }

    //equals, hashcode, compareTo, toString

    /**
     * Checks if the current User object is equal to another object.
     * Two User objects are considered equal if they have the same ID.
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
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

    /**
     * Compares the current User object to another object.
     * The comparison is based on the age of the users.
     * @param obj the object to compare
     * @return a positive integer if the current object is greater, a negative integer if the current object is smaller,
     * or 0 if the objects are equal
     */
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

    /**
     * Returns the hash code value for the User object.
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        return 1;
    }

    /**
     * Returns a string representation of the User object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return
                "\n"+"User" + '\n' +
                "Name: " + name + '\n' +
                "Password: " + password + '\n' +
                "Email: " + email + '\n' +
                "ID: " + id.toString() + '\n' +
                "Data: "+userData.toString();
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

    @Override
    public void fromJSON(JSONObject jo) throws JSONException {
        setName(jo.getString("name"));
        setEmail(jo.getString("email"));
        setPassword(jo.getString("password"));
        setId(jo.getString("id"));
        UserData data=new UserData();
        data.fromJSON(jo.getJSONObject("userData"));
        setUserData(data);
    }

    /**
     * calculates the calories that the user should consume based on their BMR + objective.
     * The calories to consume according to the objective are constant values declared in this class
     * @return an integer representing the calories to consume
     */
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

    /**
     * This is the main method of the application, its function is to generate a diet for the user based on their goals and the required calories to achieve them.
     * To achieve this, all the meals are read from the food file. They are then separated based on their type (Breakfast, Meal, or Snack).
     * The 'type' parameter is analyzed, and based on this, the meals are separated into lists according to whether they are vegetarian, vegan, gluten-free, or classic (Classic includes all meals).
     * From this, an array with random indices is generated, with the limits ranging from 0 to the number of meals in the list.
     * Once this is done, the meals will be stored in the user's diet, with a minimum of one breakfast, two main meals (lunch and dinner), and the remainder as snacks.
     * The diet will be regenerated if the total calories in the diet exceed the user's calorie goal by 80.
     * @param mealsQuantity an integer representing the quantity of meals requested by the user.
     * @param type a string indicating the type of diet (classic, celiac, vegan, vegetarian).
     */
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

    /**
     * Retrieves a list of vegetarian foods from the database.
     * @return an ArrayList of Food objects that are vegetarian
     */
    public ArrayList<Food> getVegetarianFoods() {
        ArrayList<Food> allFoods = JSONHandler.readFoodFile();

        ArrayList<Food> vegetarianFoods = new ArrayList<>();
        for (Food food : allFoods) {
            if (food.isVegetarian()) {
                vegetarianFoods.add(food);
            }
        }

        return vegetarianFoods;
    }

    /**
     * Retrieves a list of vegan foods from the database.
     * @return an ArrayList of Food objects that are vegan
     */
    public ArrayList<Food> getVeganFoods() {
        ArrayList<Food> allFoods = JSONHandler.readFoodFile();

        ArrayList<Food> veganFoods = new ArrayList<>();
        for (Food food : allFoods) {
            if (food.isVegan()) {
                veganFoods.add(food);
            }
        }

        return veganFoods;
    }

    /**
     * Retrieves a list of celiac foods from the database.
     * @return an ArrayList of Food objects that are celiac-friendly
     */
    public ArrayList<Food> getCeliacFoods() {
        ArrayList<Food> allFoods = JSONHandler.readFoodFile();

        ArrayList<Food> celiacFoods = new ArrayList<>();
        for (Food food : allFoods) {
            if (food.isCeliac()) {
                celiacFoods.add(food);
            }
        }

        return celiacFoods;
    }

    /**
     * Resets the user's diet by clearing the list of foods.
     */
    public void resetDiet() {
        userData.setDiet(new ArrayList<>());
    }
    /**
     * Checks if the user has a diet.
     * @return true if the user has a diet, false otherwise
     */
    public boolean hasDiet() {
        return (userData.getDiet().size() > 0);
    }
    /**
     * Retrieves a list of breakfast foods from the given list of foods.
     * @param allFoods the list of all foods
     * @return an ArrayList of Food objects that are categorized as breakfast
     */
    private ArrayList<Food> getBreakfastList(ArrayList<Food> allFoods) {
        ArrayList<Food> breakfasts = new ArrayList<>();
        for (Food food : allFoods) {
            if (food.getFoodType().equals(FoodType.BREAKFAST)) {
                breakfasts.add(food);
            }
        }
        return breakfasts;
    }
    /**
     * Retrieves a list of snack foods from the given list of foods.
     * @param allFoods the list of all foods
     * @return an ArrayList of Food objects that are categorized as snacks
     */
    private ArrayList<Food> getSnackList(ArrayList<Food> allFoods) {
        ArrayList<Food> snacks = new ArrayList<>();
        for (Food food : allFoods) {
            if (food.getFoodType().equals(FoodType.SNACK)) {
                snacks.add(food);
            }
        }
        return snacks;
    }
    /**
     * Retrieves a list of meal foods from the given list of foods.
     * @param allFoods the list of all foods
     * @return an ArrayList of Food objects that are categorized as meals
     */
    private ArrayList<Food> getMealList(ArrayList<Food> allFoods) {
        ArrayList<Food> meals = new ArrayList<>();
        for (Food food : allFoods) {
            if (food.getFoodType().equals(FoodType.MEAL)) {
                meals.add(food);
            }
        }
        return meals;
    }
    /**
     * Generates an array of random indices within the specified bounds.
     * @param mealQ the quantity of random indices to generate
     * @param bound the upper bound (exclusive) for the random indices
     * @return an ArrayList of randomly generated indices
     */
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
