package Exceptions;

public class EmailInUseException extends Exception{
    public EmailInUseException() {
        super("This email is already in use, please use another one");
    }

    //While this exception happens, we will recursively ask the user to try another email.
}
