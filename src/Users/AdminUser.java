package Users;

import FoodModels.Food;
import Handlers.Intermediary;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class that belongs to the administrator users. It includes the administrator methods.
 */
public class AdminUser extends User {

    private String positionAtCompany;
    public AdminUser() {
    }

    public AdminUser(String name, String password, String email, UserData userData, String positionAtCompany ) {
        super(name, password, email, userData);
        this.positionAtCompany=positionAtCompany;
    }

    public String getAllUsers(Intermediary intermediary){
        return intermediary.showMapUsers();
    }

    public String getAllFoods(Intermediary intermediary){
        return intermediary.showFoodMap();
    }

    public String getPositionAtCompany() {
        return positionAtCompany;
    }

    public void setPositionAtCompany(String positionAtCompany) {
        this.positionAtCompany = positionAtCompany;
    }

    public void deleteUser(Intermediary intermediary, User user) throws JSONException{
        intermediary.deleteUser(user);
    }
    public void deleteUser(Intermediary intermediary, String email) throws JSONException{
        intermediary.deleteUser(email);
    }


    public void deleteFood(Intermediary intermediary, Food food) throws JSONException{
        intermediary.deleteFood(food);
    }
    public void deleteFood(Intermediary intermediary, int id) throws JSONException{
        intermediary.deleteFood(id);
    }
    public void deleteUserDiet(Intermediary intermediary, int id) throws JSONException{
        List<User> users= intermediary.usersToList();

        for (User user:users) {
            ArrayList<Food> userDiet = user.getUserData().getDiet();

            for(Food food:userDiet){
                if(food.getId() == id){
                    user.resetDiet();
                    intermediary.updateUser(user.getEmail(), user);
                }
            }
        }
    }

    public void addFood(Intermediary intermediary,Food food) throws JSONException{
        intermediary.addFoodToFile(food);
    }

    @Override
    public void IFromJSON(JSONObject jo) throws JSONException
    {
        super.IFromJSON(jo);
        setPositionAtCompany(jo.getString("position"));
    }
    @Override
    public JSONObject toJSON() throws JSONException
    {
        JSONObject aux=new JSONObject();
        aux=super.toJSON();
        aux.put("position", getPositionAtCompany());
        return aux;
    }

}
