package main.Exceptions;

public class EntryOutOfBoundException extends Exception {
    public EntryOutOfBoundException(int entry) {
        super(String.format("Attempted to insert a invalid entry %d into the board.", entry));
    }
}
