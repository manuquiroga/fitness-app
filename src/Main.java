import API.ConnectionAPI;
import FoodModels.Food;
import FoodModels.foodType;
import Users.PhysicalActivity;
import Users.User;
import Users.UserData;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.NotSerializableException;

public class Main {
    public static void main(String[] args) throws IOException {
        Food food = new Food(1, "asd", 3, 4, 5, 6, 8, foodType.BREAKFAST, false, false, false);
        UserData userData=new UserData(32, 75, 80, 175, "male", PhysicalActivity.MODERATE);
        userData.addFood(food);
        User user = new User("name", "password", "email", 1, userData);



}