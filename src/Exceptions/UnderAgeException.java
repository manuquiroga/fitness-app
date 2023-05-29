package Exceptions;

public class UnderAgeException extends Exception{


    public UnderAgeException(int emailEntered) {
        super("Users under the age of thirteen are not allowed to use this app");
    }


}
