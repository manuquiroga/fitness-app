package Users;

import FoodModels.Food;
import Handlers.Intermediary;
import org.json.JSONException;

public class AdminUser extends User{

    public AdminUser() {
    }

    public AdminUser(String name, String password, String email, UserData userData) {
        super(name, password, email, userData);
    }

    //methods: ver todos los usuarios y comidas, eliminar un usuario/comida, buscar usuario/comida
    public String getAllUsers(Intermediary intermediary){
        return intermediary.showMapUsers();
    }

    public String getAllFoods(Intermediary intermediary){
        return intermediary.showFoodMap();
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
}
