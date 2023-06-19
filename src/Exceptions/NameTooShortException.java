package Exceptions;

/**
 * Custom exception that is thrown when a name is too short.
 */
public class NameTooShortException extends Exception {

    /**
     * Constructs a NameTooShortException with the specified error message.
     * @param message the error message
     */
    public NameTooShortException(String message) {
        super(message);
    }
    /**
     * Returns an example name.
     * @return an example name
     */
    public String nameExample() {
        return "Lionel Messi";
    }
}
