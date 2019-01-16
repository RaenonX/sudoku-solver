package Main.Exceptions;

public class InsufficientRowException extends Exception {
    public InsufficientRowException() {
        super("Insufficient rows to complete a sudoku board.");
    }
}