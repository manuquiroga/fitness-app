package Exceptions;

/**
 * Custom exception that is thrown when an incorrect card number is provided.
 */
public class IncorrectCardNumberException extends Exception {

    /**
     * Constructs an IncorrectCardNumberException with the default error message.
     */
    public IncorrectCardNumberException() {
        super("Incorrect card number");
    }
}
