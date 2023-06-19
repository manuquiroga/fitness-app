package Exceptions;

/**
 * Custom exception that is thrown when a password is incorrect on Login.
 */
public class IncorrectPasswordException extends Exception {

    /**
     * Constructs an IncorrectPasswordException with the specified error message.
     * @param message the error message describing the incorrect password
     */
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
