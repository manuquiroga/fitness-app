package Exceptions;

public class FoodNotInMapException extends Exception{
    public FoodNotInMapException() {
        super("The Food you were looking for is not on the database, please check the correct ID");
    }
}
