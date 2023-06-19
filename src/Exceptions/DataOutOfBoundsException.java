package Exceptions;

/**
 * Custom exception class for representing data out of bounds.
 * This exception is thrown when a data value exceeds the acceptable bounds or range.
 */
public class DataOutOfBoundsException extends Exception{
    /**
     * Constructs a new DataOutOfBoundsException with the specified detail message.
     * @param message the detail message explaining the reason for the exception
     */
    public DataOutOfBoundsException(String message) {
        super(message);
    }
}
