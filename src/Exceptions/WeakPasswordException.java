package Exceptions;

/**
 * Custom exception that is thrown when a password is considered weak.
 */
public class WeakPasswordException extends Exception {

    /**
     * Constructs a WeakPasswordException with the specified error message.
     * @param message the error message
     */
    public WeakPasswordException(String message) {
        super(message);
    }
    /**
     * Returns an example password.
     * @return an example password
     */
    public String passwordExample() {
        return "Testo1234";
    }
}
