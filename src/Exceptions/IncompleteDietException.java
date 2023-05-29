package Exceptions;

public class IncompleteDietException extends Exception{
    public IncompleteDietException() {
        super("A food that was implemented in your diet was deleted from the system, we will provide a new one");
    }
}
