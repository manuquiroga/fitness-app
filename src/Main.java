import API.ConnectionAPI;
import Users.PhysicalActivity;
import Users.User;
import Users.UserData;
import org.json.JSONException;

import java.io.IOException;
import java.io.NotSerializableException;

public class Main {
    public static void main(String[] args) {
        UserData userData=new UserData(32, 75, 80, 175, "male", PhysicalActivity.MODERATE);
        User user = new User("name", "password", "email", 1, userData);
        try{
            user.toFile();
        }catch (JSONException e){
            System.err.println(e.getMessage());
        } catch (NotSerializableException e)
        {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}