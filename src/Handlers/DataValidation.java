package Handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
    private static final int MIN_PASSWORD_CHARACTERS = 8;
    private static final int MIN_NAME_CHARACTERS = 6;

    public static int getMinCharPass(){
        return MIN_PASSWORD_CHARACTERS;
    }

    public static int getMinCharName(){
        return MIN_NAME_CHARACTERS;
    }

    public static boolean name(String name){
        return name.length() >= MIN_NAME_CHARACTERS;
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
}
