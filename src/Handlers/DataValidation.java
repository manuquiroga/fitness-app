package Handlers;

import Exceptions.*;
import Users.User;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides data validation methods for various fields and user data.
 */
public final class DataValidation {
    private static final int MIN_PASSWORD_CHARACTERS = 8;
    private static final int MIN_NAME_CHARACTERS = 6;
    private static final int MIN_AGE = 13;
    private static final int MAX_AGE = 95;
    private static final double MIN_WEIGHT = 25;
    private static final double MAX_WEIGHT = 500;
    private static final int MIN_HEIGHT = 80;
    private static final int MAX_HEIGHT = 240;

    /**
     * Retrieves the minimum number of characters required for a password.
     * @return the minimum number of characters for a password
     */
    public static int getMinCharPass() {
        return MIN_PASSWORD_CHARACTERS;
    }
    /**
     * Retrieves the minimum number of characters required for a name.
     * @return the minimum number of characters for a name
     */
    public static int getMinCharName() {
        return MIN_NAME_CHARACTERS;
    }
    /**
     * Checks if a name meets the minimum character requirement.
     * @param name the name to validate
     * @return true if the name meets the minimum character requirement, false otherwise
     */
    public static boolean name(String name) {
        return name.length() >= MIN_NAME_CHARACTERS;
    }
    /**
     * Checks if an age is above the minimum age requirement.
     * @param age the age to validate
     * @return true if the age is above the minimum age requirement, false otherwise
     */
    public static boolean ageMin(int age) {
        return age > MIN_AGE;
    }
    /**
     * Checks if an age is below the maximum age limit.
     * @param age the age to validate
     * @return true if the age is below the maximum age limit, false otherwise
     */
    public static boolean ageMax(int age) {
        return age < MAX_AGE;
    }
    /**
     * Checks if a weight is above the minimum weight requirement.
     * @param weight the weight to validate
     * @return true if the weight is above the minimum weight requirement, false otherwise
     */
    public static boolean weightMin(double weight) {
        return weight > MIN_WEIGHT;
    }
    /**
     * Checks if a weight is below the maximum weight limit.
     * @param weight the weight to validate
     * @return true if the weight is below the maximum weight limit, false otherwise
     */
    public static boolean weightMax(double weight) {
        return weight < MAX_WEIGHT;
    }
    /**
     * Checks if a height is above the minimum height requirement.
     * @param height the height to validate
     * @return true if the height is above the minimum height requirement, false otherwise
     */
    public static boolean heightMin(int height) {
        return height > MIN_HEIGHT;
    }
    /**
     * Checks if a height is below the maximum height limit.
     * @param height the height to validate
     * @return true if the height is below the maximum height limit, false otherwise
     */
    public static boolean heightMax(int height) {
        return height < MAX_HEIGHT;
    }
    /**
     * Checks if an email address is in a valid format.
     * @param email the email address to validate
     * @return true if the email address is in a valid format, false otherwise
     */
    public static boolean email(String email) {
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPat.matcher(email);
        return matcher.find();
    }

    /**
     * Checks if an email address already exists in the user list.
     * @param email the email address to check
     * @return true if the email address already exists, false otherwise
     */
    private static boolean existsEmail(String email) {
        boolean val = false;
        ArrayList<User> userList = JSONHandler.readUserFile();
        for (User user: userList) {
            if (email.equalsIgnoreCase(user.getEmail())) {
                val = true;
                break;
            }
        }
        return val;
    }

    /**
     * Checks if a password meets the minimum requirements.
     * @param password the password to validate
     * @return true if the password meets the minimum requirements, false otherwise
     */
    public static boolean password(String password) {
        boolean val = false;
        if (password.length() >= MIN_PASSWORD_CHARACTERS) {
            if (checkPass(password)) {
                val = true;
            }
        }
        return val;
    }

    /**
     * Checks if a password meets the safety requirements.
     * @param password the password to check
     * @return true if the password meets the safety requirements, false otherwise
     */
    private static boolean checkPass(String password) {
        boolean hasNum = false;
        boolean hasCap = false;
        boolean isSafe = false;
        char c;

        for (int i = 0; i < password.length() && !isSafe; i++) {
            c = password.charAt(i);
            if (Character.isDigit(c)) {
                hasNum = true;
            } else if (Character.isUpperCase(c)) {
                hasCap = true;
            }
            if (hasNum && hasCap) {
                isSafe = true;
            }
        }
        return isSafe;
    }

    /**
     * Checks the login data for a user.
     * @param email the email address entered by the user
     * @param password the password entered by the user
     * @return true if the login data is valid, false otherwise
     * @throws IncorrectPasswordException if the email or password is incorrect
     */
    public static boolean checkLoginData(String email, String password) throws IncorrectPasswordException {
        boolean val = false;
        ArrayList<User> userList;
        try {
            if (FileHandler.existsFile("user")) {
                userList = JSONHandler.readUserFile();
                for (User user : userList) {
                    if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                        val = true;
                        break;
                    }
                }
                if (!val) {
                    throw new IncorrectPasswordException("Incorrect email or password");
                }
            }
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
        return val;
    }
    /**
     * Checks the user data for validity.
     * @param name the name entered by the user
     * @param email the email entered by the user
     * @param password the password entered by the user
     * @return true if the user data is valid, false otherwise
     * @throws NameTooShortException if the name is too short
     * @throws IncorrectEmailFormatException if the email format is incorrect
     * @throws WeakPasswordException if the password is too weak
     * @throws EmailInUseException if the email is already in use
     */
    public static boolean checkData(String name, String email, String password) throws NameTooShortException, IncorrectEmailFormatException, WeakPasswordException, EmailInUseException {
        boolean val = false;
        if (!DataValidation.name(name)) {
            throw new NameTooShortException("The name has to be at least " + DataValidation.getMinCharName() + " characters long");
        } else if (!DataValidation.email(email)) {
            throw new IncorrectEmailFormatException("The email format is wrong");
        } else if (!DataValidation.password(password)) {
            throw new WeakPasswordException("Password needs to have minimum " +
                    DataValidation.getMinCharPass() +
                    " characters and contain at least 1 number and 1 uppercase");
        } else if (DataValidation.existsEmail(email)) {
            throw new EmailInUseException(email);
        } else {
            val = true;
        }
        return val;
    }
    /**
     * Checks if the text fields contain only digits.
     * @param fields the text fields to check
     * @throws NumberFormatException if any of the fields does not contain only digits
     */
    public static void checkDataDigit(JTextField... fields) throws NumberFormatException {
        String regex = "\\d+";
        for (JTextField field : fields) {
            String value = field.getText();
            if (!value.matches(regex)) {
                throw new NumberFormatException("Age, weight, height and desired weight fields can only accept digits");
            }
        }
    }
    /**
     * Checks the user data for valid bounds.
     * @param age the age of the user
     * @param weight the weight of the user
     * @param height the height of the user
     * @return true if the user data is within the valid bounds, false otherwise
     * @throws DataOutOfBoundsException if any of the user data is outside the valid bounds
     */
    public static boolean checkUserDataBounds(int age, double weight, int height) throws DataOutOfBoundsException {
        boolean val = false;
        if (!DataValidation.ageMin(age)) {
            throw new DataOutOfBoundsException("Under age");
        } else if (!DataValidation.ageMax(age)) {
            throw new DataOutOfBoundsException("Over the age limit");
        } else if (!DataValidation.weightMin(weight)) {
            throw new DataOutOfBoundsException("Under weight");
        } else if (!DataValidation.weightMax(weight)) {
            throw new DataOutOfBoundsException("Over the weight limit");
        } else if (!DataValidation.heightMin(height)) {
            throw new DataOutOfBoundsException("Under height");
        } else if (!DataValidation.heightMax(height)) {
            throw new DataOutOfBoundsException("Over the height limit");
        }
        return val;
    }
    /**
     * Retrieves the user object from the login data.
     * @param email the email address entered by the user
     * @param password the password entered by the user
     * @return the User object if the login data is valid, null otherwise
     */
    public static User getUserFromLogin(String email, String password) {
        ArrayList<User> userList;
        try {
            if (FileHandler.existsFile("user")) {
                userList = JSONHandler.readUserFile();
                for (User user : userList) {
                    if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                        return user;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("File error: " + e.getMessage());
        }
        return null;
    }
    /**
     * Checks the card data for validity.
     * @param cardNumber the card number entered by the user
     * @param cvv the CVV entered by the user
     * @param name the name entered by the user
     * @throws IncorrectCardNumberException if the card number, CVV, or name format is incorrect
     */
    public static void checkCardData(String cardNumber, String cvv, String name) throws IncorrectCardNumberException {

        if (!checkCardNumber(cardNumber) || !cvv.matches("\\d{3,4}") || (!name.matches("^[a-zA-Z]+\\s[a-zA-Z]+(\\s[a-zA-Z]+)?$") && name.replaceAll("\\s", "").length() > 24)){
            throw new IncorrectCardNumberException();
        }

    }

    /**
     * Checks the validity of a credit card number using the Luhn algorithm.
     * @param cardNumber The credit card number to be checked.
     * @return {@code true} if the card number is valid, {@code false} otherwise.
     */
    private static boolean checkCardNumber(String cardNumber) {
        cardNumber = cardNumber.replaceAll("\\s+", "");

        if (!cardNumber.matches("\\d+")) {
            return false;
        }

        int[] digits = new int[cardNumber.length()];
        for (int i = 0; i < cardNumber.length(); i++) {
            digits[i] = Character.getNumericValue(cardNumber.charAt(i));
        }

        // Luhn algorithm
        for (int i = digits.length - 2; i >= 0; i -= 2) {
            int digitDouble = digits[i] * 2;
            if (digitDouble > 9) {
                digitDouble = digitDouble % 10 + 1;
            }
            digits[i] = digitDouble;
        }

        int plus = 0;
        for (int aux : digits) {
            plus += aux;
        }


        return plus % 10 == 0;
    }
}
