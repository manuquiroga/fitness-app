package Exceptions;

/**
 * Custom exception class for representing a food not found in the database.
 * This exception is thrown when attempting to access a food item that is not present in the database.
 */
public class FoodNotInMapException extends Exception {

    /**
     * Constructs a new FoodNotInMapException with a default error message.
     * The error message indicates that the requested food item is not found in the foods file.
     */
    public FoodNotInMapException() {
        super("The Food you were looking for is not on the database, please check the correct ID");
    }
}
