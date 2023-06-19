package UI;

import Exceptions.FoodNotInMapException;
import Exceptions.UserNotInMapException;
import FoodModels.Food;
import FoodModels.FoodType;
import Handlers.DataValidation;
import Handlers.Intermediary;
import Handlers.JSONHandler;
import Users.AdminUser;
import Users.User;
import org.json.JSONException;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
            System.out.println("5. SEARCH ONE FOOD");
            System.out.println("6. SEARCH ONE USER");
            System.out.println("7. ADD ONE FOOD");
            String input = scanner.nextLine();

            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                runAdminMenu(intermediary);
                break;
            }

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
                        data = scanner.next();
                        if(!DataValidation.email(data)){
                            System.err.println("The email entered is wrongly formulated");
                        }else {
                            admin.deleteUser(intermediary, data);
                        }
                        break;
                    case 4:
                        System.out.println("Write the food's id: ");
                        id = scanner.nextInt();
                        admin.deleteFood(intermediary, id);
                        admin.deleteUserDiet(intermediary, id);
                        break;
                    case 5:
                        System.out.println("Search food by id");
                        id = scanner.nextInt();
                        Food food = intermediary.searchFood(id);
                        System.out.println(food.toString());
                        break;
                    case 6:
                        System.out.println("Search user by email");
                        data = scanner.next();
                        User user = intermediary.searchUser(data);
                        System.out.println(user.toString());
                        break;
                    case 7:
                        Food aux = new Food();
                        addFoodMenu(aux);
                        intermediary.addFoodToFile(aux);
                        break;
                    default:
                        System.err.println("Option does not exist");
                        break;
                }
            }catch (JSONException ex){
                System.err.println("Error "+ex.getMessage());
            }catch (FoodNotInMapException ex){
                System.err.println("Error "+ex.getMessage());
            }catch (UserNotInMapException ex){
                System.err.println("Error: "+ex.getMessage());
            }
            System.out.println("If you want to continue enter yes");
            persist = scanner.next();
        }while(persist.equalsIgnoreCase("yes"));
    }

    public AdminMenu(AdminUser admin){
        AdminMenu.admin = admin;
    }

    private void addFoodMenu(Food aux){
        System.out.print("Name: ");
        aux.setName(scanner.nextLine());

        System.out.print("Calories: ");
        aux.setCalories(scanner.nextInt());

        System.out.print("Proteins (in grams): ");
        aux.setProteins_g(scanner.nextDouble());

        System.out.print("Fats (in grams): ");
        aux.setFats_g(scanner.nextDouble());

        System.out.print("Carbohydrates (in grams): ");
        aux.setCarbohydrates_g(scanner.nextDouble());

        System.out.print("Serving Size (in grams): ");
        aux.setServingSize_g(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("Food type: (BREAKFAST, MEAL, SNACK): ");
        aux.setFoodType(scanner.next());

        System.out.println("Enter the ingredients (type 'done' to finish):");

        String ingredient = scanner.nextLine();
        while (!ingredient.equalsIgnoreCase("done")) {
            aux.addIngredient(ingredient);
            ingredient = scanner.nextLine();
        }

        System.out.print("Is it vegan? (true/false): ");
        aux.setVegan(scanner.nextBoolean());

        System.out.print("Is it celiac friendly? (true/false): ");
        aux.setCeliac(scanner.nextBoolean());

        System.out.print("Is it vegetarian? (true/false): ");
        aux.setVegetarian(scanner.nextBoolean());


    }
}
