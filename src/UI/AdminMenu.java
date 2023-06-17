package UI;

import Handlers.Intermediary;
import Users.AdminUser;

import java.util.Scanner;

public class AdminMenu {
    private static AdminUser admin;
    static Scanner scanner = new Scanner(System.in);
    public void runAdminMenu(Intermediary intermediary){

        int option = 0, id;
        String data;

        System.out.println("Welcome admin");
        System.out.println("Select option");

        System.out.println("1. DISPLAY ALL USERS");
        System.out.println("2. DISPLAY ALL FOODS");
        System.out.println("3. DELETE ONE USER");
        System.out.println("4. DELETE ONE FOOD");
        option = scanner.nextInt();

        switch (option){
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
                break;
            default:
                System.err.println("Option does not exist. Closing admin menu");
                break;
        }
    }

    public AdminMenu(AdminUser admin){
        AdminMenu.admin = admin;
    }
}
