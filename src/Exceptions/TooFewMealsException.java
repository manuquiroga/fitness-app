package Exceptions;

public class TooFewMealsException extends Exception{
    public TooFewMealsException() {
        super("Too few meals for your objective. Select at least 4");
    }
}
