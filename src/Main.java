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
        Food food = new Food(1, "asd", 3, 4, 5, 6, 8, foodType.BREAKFAST);
        UserData userData=new UserData(32, 75, 80, 175, "male", PhysicalActivity.MODERATE);
        userData.addFood(food);
        User user = new User("name", "password", "email", 1, userData);


        //write to file
        try{
            user.toFile();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }catch (JSONException e){
            System.err.println(e.getMessage());
        }

        //read string from file, convert into json
        BufferedReader br = new BufferedReader(new FileReader("users.dat"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            JSONObject jo = new JSONObject(everything);
            System.out.println(jo.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } finally {
            br.close();
        }
    }
}