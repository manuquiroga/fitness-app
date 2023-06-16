package Exceptions;

public class IncorrectCardNumberException extends Exception{
    public IncorrectCardNumberException() {
        super("Incorrect card number");
    }
}
