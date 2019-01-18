package main.Exceptions;

public class EntryExistedException extends Exception {
    public EntryExistedException(int row, int col) {
        super(String.format("An entry is already existed at row %d column %d.", row, col));
    }
}
