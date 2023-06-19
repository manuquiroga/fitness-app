package Exceptions;

/**
 * Custom exception class for representing an email already in use.
 * This exception is thrown when attempting to use an email that is already registered or in use.
 */
public class EmailInUseException extends Exception {

    private String email;

    /**
     * Constructs a new EmailInUseException with the specified email address.
     * @param email the email address that is already in use
     */
    public EmailInUseException(String email) {
        super("Email " + email + " already in use");
        this.email = email;
    }

    /**
     * Gets the email address that is already in use.
     * @return the email address
     */
    public String getEmail() {
        return email;
    }
}
