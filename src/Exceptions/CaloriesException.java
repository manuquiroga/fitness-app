package Exceptions;

public class CaloriesException extends Exception{
    public CaloriesException() {
        super("You have exceeded the calorie limit");
    }
}
