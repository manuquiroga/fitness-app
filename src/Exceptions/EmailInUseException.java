package Exceptions;

public class EmailInUseException extends Exception{


    public EmailInUseException() {
        super("This email is already in use, please use another one");
    }
}
