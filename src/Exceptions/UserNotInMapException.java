package Exceptions;

/**
 * Custom exception that is thrown when a user is not found in the user map.
 */
public class UserNotInMapException extends Exception {

    /**
     * Constructs a UserNotInMapException with the default error message.
     */
    public UserNotInMapException() {
        super("There isn't a user attached to that email in our database");
    }
}
