package main.Exceptions;

public class FileFormatUnsupportedException extends Exception {
    public FileFormatUnsupportedException(String ext) {
        super(String.format("File format %s is not recognized.", ext));
    }
}