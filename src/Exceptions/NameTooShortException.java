package Exceptions;

public class NameTooShortException extends Exception {
    public NameTooShortException(String s) {
        super(s);
    }

    public String nameExample(){
        return "Lionel Messi";
    }
}
