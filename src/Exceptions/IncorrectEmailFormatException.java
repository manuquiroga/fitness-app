package Exceptions;

public class IncorrectEmailFormatException extends Throwable {
    public IncorrectEmailFormatException(String s) {
        super(s);
    }
}
