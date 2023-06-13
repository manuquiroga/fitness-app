package Users;

import FoodModels.Food;
import Interfaces.IToJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserData implements IToJSON {
    private int age;
    private double weight;
    private Objective objective;
    private int height;
    private String sex;
    private ArrayList<Food> diet;
    private PhysicalActivity physicalActivity;


    //constructors:

    public UserData(int age, double weight, String objective, int height, String sex, String physicalActivity) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
        setObjective(objective);
        setPhysicalActivity(physicalActivity);
        diet=new ArrayList<Food>();
    }

    public UserData() {
    }

    //Getters:

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public Objective getObjective() {
        return objective;
    }

    public int getHeight() {
        return height;
    }

    public String getSex() {
        return sex;
    }

    public PhysicalActivity getPhysicalActivity() {
        return physicalActivity;
    }

    //Setters

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }




    public void setHeight(int height) {
        this.height = height;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDiet(ArrayList<Food> diet) {
        this.diet = diet;
    }

    public void setPhysicalActivity(String physicalActivity) {
        if (physicalActivity.equals("NONE")){
            this.physicalActivity = PhysicalActivity.NONE;
        }else if (physicalActivity.equals("MODERATE")){
            this.physicalActivity = PhysicalActivity.MODERATE;
        }else {
            this.physicalActivity = PhysicalActivity.ACTIVE;
        }
    }

    public void setObjective(String objective) {
        if (objective.equals("Lose weight") || objective.equals("LOSE_WEIGHT")){
            this.objective = Objective.LOSE_WEIGHT;
        }else if (objective.equals("Maintain weight") || objective.equals("MAINTAIN_WEIGHT")){
            this.objective = Objective.MAINTAIN_WEIGHT;
        }else if (objective.equals("Gain weight") || objective.equals("GAIN_WEIGHT")){
            this.objective = Objective.GAIN_WEIGHT;
        }
    }


    //toString

    @Override
    public String toString() {
        return "UserData{" + '\n' +
                "age: " + age + '\n' +
                "weight: " + weight + '\n' +
                "objective: " + objective + '\n' +
                "height: " + height + '\n' +
                "sex: " + sex + '\n' +
                "physicalActivity: " + physicalActivity + '\n' +
                "diet: " + diet.toString() + '\n' +
                '}';
    }


    //Methods:

    public int calculateBMR(){
        //Harris-Bennedict Equation
        int bmr = 0;
        if(sex.equalsIgnoreCase("MALE")){
            bmr = 88 + (int) (13.4 * weight) + (int) (4.8 * height) + (int) (5.7 * age);
        }
        else if (sex.equalsIgnoreCase("FEMALE")) {
            bmr = 448 + (int) (9.24 * weight) + (3 * height) + (int) (4.3 * age); //
        }

        switch (physicalActivity){
            case NONE -> bmr *= 1.1;
            case MODERATE -> bmr *= 1.3;
            case ACTIVE -> bmr *= 1.5;
        }
        return bmr;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age", age);
        jsonObject.put("weight", weight);
        jsonObject.put("objective", objective.name());
        jsonObject.put("height", height);
        jsonObject.put("sex", sex);
        jsonObject.put("physicalActivity", physicalActivity.name());
        JSONArray jsonArray = new JSONArray();
        for (Food food : diet) {
            jsonArray.put(food.toJSON());
        }
        jsonObject.put("diet", jsonArray);
        return jsonObject;
    }

    public void addFood(Food food) {
        diet.add(food);
    }
}
