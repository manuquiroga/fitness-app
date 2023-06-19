package Exceptions;

/**
 * Custom exception that is thrown when an email is in an incorrect format.
 */
public class IncorrectEmailFormatException extends Exception {

    /**
     * Constructs an IncorrectEmailFormatException with the specified error message.
     * @param message the error message describing the incorrect email format
     */
    public IncorrectEmailFormatException(String message) {
        super(message);
    }
}
