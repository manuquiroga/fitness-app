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
    private double desiredWeight;
    private int height;
    private String sex;
    private ArrayList<Food> diet;
    private PhysicalActivity physicalActivity;


    //constructors:

    public UserData(int age, double weight, double desiredWeight, int height, String sex, PhysicalActivity physicalActivity) {
        this.age = age;
        this.weight = weight;
        this.desiredWeight = desiredWeight;
        this.height = height;
        this.sex = sex;
        this.physicalActivity = physicalActivity;
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

    public double getDesiredWeight() {
        return desiredWeight;
    }

    public int getHeight() {
        return height;
    }

    public String getSex() {
        return sex;
    }

    public Enum getPhysicalActivity() {
        return physicalActivity;
    }

    //toString

    @Override
    public String toString() {
        return "UserData{" + '\n' +
                super.toString() + '\n' +
                "age: " + age + '\n' +
                "weight: " + weight + '\n' +
                "desiredWeight: " + desiredWeight + '\n' +
                "height: " + height + '\n' +
                "sex: " + sex + '\n' +
                "physicalActivity: " + physicalActivity +
                '}';
    }


    //Methods:

    public void updateData(){

    }

    @Override
    public JSONObject toJSON() throws JSONException {
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age", age);
        jsonObject.put("weight", weight);
        jsonObject.put("desiredWeight", desiredWeight);
        jsonObject.put("height", height);
        jsonObject.put("sex", sex);
        jsonObject.put("physicalActivity", physicalActivity);
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
