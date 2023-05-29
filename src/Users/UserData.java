package Users;

import FoodModels.Food;
import Interfaces.IToJSON;

import java.util.ArrayList;

public class UserData implements IToJSON {
    private int age;
    private double weight;
    private double desiredWeight;
    private int height;
    private char gender;
    private ArrayList<Food> diet;
    private PhysicalActivity physicalActivity;


    //constructors:

    public UserData(int age, double weight, double desiredWeight, int height, char gender, PhysicalActivity physicalActivity) {
        this.age = age;
        this.weight = weight;
        this.desiredWeight = desiredWeight;
        this.height = height;
        this.gender = gender;
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

    public char getGender() {
        return gender;
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
                "gender: " + gender + '\n' +
                "physicalActivity: " + physicalActivity +
                '}';
    }


    //Methods:

    public void updateData(){

    }
}
