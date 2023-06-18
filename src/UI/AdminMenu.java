package UI;

import FoodModels.Food;
import Handlers.DataValidation;
import Handlers.Intermediary;
import Users.AdminUser;
import Users.User;
import org.json.JSONException;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminMenu {
    private static AdminUser admin;
    static Scanner scanner = new Scanner(System.in);
    public void runAdminMenu(Intermediary intermediary){

        int option = 0, id;
        String data = new String(),persist = new String();


            System.out.println("Welcome admin");
        do {
            System.out.println("Select option");

            System.out.println("1. DISPLAY ALL USERS");
            System.out.println("2. DISPLAY ALL FOODS");
            System.out.println("3. DELETE ONE USER");
            System.out.println("4. DELETE ONE FOOD");
            option = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (option) {
                    case 1:
                        System.out.println(admin.getAllUsers(intermediary));
                        break;
                    case 2:
                        System.out.println(admin.getAllFoods(intermediary));
                        break;
                    case 3:
                        System.out.println("Write the user's email: ");
                        data = scanner.nextLine();
                        admin.deleteUser(intermediary, data);
                        break;
                    case 4:
                        System.out.println("Write the food's id: ");
                        id = scanner.nextInt();
                        admin.deleteFood(intermediary, id);
                        admin.deleteUserDiet(intermediary, id);

                        scanner.nextLine();
                        break;
                    default:
                        System.err.println("Option does not exist");
                        break;
                }
            }catch (JSONException ex){
                System.err.println("Error"+ex.getMessage());
            }
            System.out.println("If you want to continue enter yes");
            persist = scanner.next();
        }while(persist.equalsIgnoreCase("yes"));
    }

    public AdminMenu(AdminUser admin){
        AdminMenu.admin = admin;
    }
}
