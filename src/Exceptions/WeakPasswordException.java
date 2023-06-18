package Exceptions;

public class WeakPasswordException extends Exception {
    public WeakPasswordException(String s) {
        super(s);
    }
    public String passwordExample(){
        return "Testo1234";
    }
}
