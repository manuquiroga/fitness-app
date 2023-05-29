package Exceptions;

public class IncorrectPasswordException extends Exception{


    public IncorrectPasswordException(int tries) {
        super("Your password was incorrect, try again. You have "+ tries + " remaining");
    }

    public void closeApp(){
        System.exit(0);
    }
}
