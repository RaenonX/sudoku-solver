package Main.Exceptions;

public class InsufficientRowLengthException extends Exception {
    public InsufficientRowLengthException(int rowNumber, String row) {
        super(String.format("Insufficient length at row %d: %d, loading terminated.", rowNumber, row.length()));
    }
}