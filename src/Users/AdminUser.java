package Users;

import FoodModels.Food;
import Handlers.Intermediary;
import Interfaces.IToJSON;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminUser extends User {

    private String positionAtCompany;
    public AdminUser() {
    }

    public AdminUser(String name, String password, String email, UserData userData, String positionAtCompany ) {
        super(name, password, email, userData);
        this.positionAtCompany=positionAtCompany;
    }

    //methods: ver todos los usuarios y comidas, eliminar un usuario/comida, buscar usuario/comida
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

    public void deleteUser(Intermediary intermediary, User user){
        try {
            intermediary.deleteUser(user);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }
    public void deleteUser(Intermediary intermediary, String email){
        try {
            intermediary.deleteUser(email);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }


    public void deleteFood(Intermediary intermediary, Food food){
        try {
            intermediary.deleteFood(food);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }
    public void deleteFood(Intermediary intermediary, int id){
        try {
            intermediary.deleteFood(id);
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }
    public void deleteUserDiet(Intermediary intermediary, int id){
        ArrayList<User> users= (ArrayList<User>) intermediary.usersToList();

        for (User user:users) {
            ArrayList<Food> userDiet = user.getUserData().getDiet();

            for(Food food:userDiet){
                if(food.getId() == id){
                    user.resetDiet();

                    try {
                        intermediary.updateUser(user.getEmail(), user);
                    } catch (JSONException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
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
