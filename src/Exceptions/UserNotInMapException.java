package Exceptions;

public class UserNotInMapException extends Exception{
    public UserNotInMapException() {
        super("There isn't an user attached to that email in our database");
    }
}
