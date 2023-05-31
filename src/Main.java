import API.ConnectionAPI;
import Collections.GenericTreeMap;
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
        GenericTreeMap<Integer, String> gt= new GenericTreeMap<>();
        gt.put(4, "Hola");
        gt.put(6, "s");
        gt.put(1, "d");
        gt.put(2, "f");

        System.out.println(gt.containsValue("s"));
        System.out.println(gt.containsValue("kj"));
        System.out.println(gt.removeByKey(2));
        System.out.println(gt.toString());
    }


}