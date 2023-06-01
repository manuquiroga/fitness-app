package Exceptions;

public class NameTooShortException extends Throwable {
    public NameTooShortException(String s) {
        super(s);
    }
}
