package Exceptions;

public class UnderAgeException extends Exception{


    public UnderAgeException(int emailEntered) {
        super("Users under the age of thirteen are not allowed to use this app");
    }

    //if the user is under 13 years old, the app will close
    public void closeApp(){
        System.exit(0);
    }
}
