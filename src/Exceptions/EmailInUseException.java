package Exceptions;

public class EmailInUseException extends Exception{
    private String email;

    public EmailInUseException(String email) {
        super("Email "+email+" already in use");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
