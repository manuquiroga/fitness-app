package Handlers;

import Exceptions.*;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
    private static final int MIN_PASSWORD_CHARACTERS = 8;
    private static final int MIN_NAME_CHARACTERS = 6;
    private static final int MIN_AGE = 13;
    private static final int MAX_AGE = 95;
    private static  final double MIN_WEIGHT = 25;
    private static final double MAX_WEIGHT = 500;
    private static final int MIN_HEIGHT = 80;

    private static final int MAX_HEIGHT = 240;
    public static int getMinCharPass(){
        return MIN_PASSWORD_CHARACTERS;
    }

    public static int getMinCharName(){
        return MIN_NAME_CHARACTERS;
    }

    public static boolean name(String name){
        return name.length() >= MIN_NAME_CHARACTERS;
    }
    public static boolean ageMin (int age){
        return age < MIN_AGE;
    }

    public static boolean ageMax (int age){
        return age > MAX_AGE;
    }
    public static boolean weightMin(double weight){
        return weight < MIN_WEIGHT;
    }

    public static boolean weightMax(double weight){
        return weight > MAX_WEIGHT;
    }

    public static boolean heightMin (int height){
        return height < MIN_HEIGHT;
    }

    public static boolean heightMax (int height){
        return height < MAX_HEIGHT;
    }

    public static boolean email(String email){
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(email);
        return matcher.find();
    }

    public static boolean password(String password){
        boolean val = false;
        if(password.length() >= MIN_PASSWORD_CHARACTERS){
            if(checkPass(password)){
                val = true;
            }
        }
        return val;
    }

    private static boolean checkPass(String password){
        boolean hasNum = false;
        boolean hasCap = false;
        boolean isSafe = false;
        char c;

        for (int i = 0; i<password.length() && !isSafe; i++){
            c = password.charAt(i);
            if(Character.isDigit(c)){
                hasNum = true;
            }
            else if (Character.isUpperCase(c)){
                hasCap = true;
            }
            if(hasNum && hasCap){
                isSafe = true;
            }
        }
        return isSafe;
    }

    public static boolean checkData(String name, String email, String password) throws NameTooShortException, IncorrectEmailFormatException, WeakPasswordException {
        boolean val = false;
        if(!DataValidation.name(name)){
            throw new NameTooShortException("The name has to be at least "+ DataValidation.getMinCharName() + " characters long");
        }
        else if(!DataValidation.email(email)){
            throw new IncorrectEmailFormatException("The email format is wrong");
        }
        else if(!DataValidation.password(password)){
            throw new WeakPasswordException("Password needs to be at least "+
                    DataValidation.getMinCharPass() +
                    " and contain at least 1 number and 1 uppercase"); //redactar mejor
        }
        else{
            val = true;
        }
        return val;
    }

    public static boolean checkUserDataBounds(int age,double weight, int height) throws DataOutOfBoundsException{
        boolean val = false;
        if(!DataValidation.ageMin(age)){
            throw new DataOutOfBoundsException("Under age");
        } else if (!DataValidation.ageMax(age)) {
            throw new DataOutOfBoundsException("Pass the age limit");
        } else if (!DataValidation.weightMin(weight)) {
            throw new DataOutOfBoundsException("Under weight");
        } else if (!DataValidation.weightMax(weight)) {
            throw new DataOutOfBoundsException("Pass the weight limit");
        } else if (!DataValidation.heightMin(height)) {
            throw new DataOutOfBoundsException("Under height");
        } else if (!DataValidation.heightMax(height)) {
            throw new DataOutOfBoundsException("Pass the height limit");
        }
        return val;
    }
}
