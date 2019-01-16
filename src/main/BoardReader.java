package main;

import main.Exceptions.FileFormatUnsupportedException;
import main.Exceptions.InsufficientRowException;
import main.Exceptions.InsufficientRowLengthException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class BoardReader {
    static final char EMPTY_SYMBOL = '.';

    private static String getFileExt(String FilePath) {
        int idx = FilePath.lastIndexOf('.');
        return idx > 0 ? FilePath.substring(idx + 1) : "";
    }

    public static Board read(String FilePath) throws InsufficientRowLengthException, InsufficientRowException, IOException, FileFormatUnsupportedException {
        String ext = getFileExt(FilePath);

        if (ext.equals("ss")) {
            return read(getSsList(FilePath));
        } else if (ext.equals("sdk")) {
            return read(getSdkList(FilePath));
        } else {
            throw new FileFormatUnsupportedException(ext);
        }
    }

    public static Board readSafe(String FilePath) {
        try {
            return read(FilePath);
        } catch (Exception e) {
            return null;
        }
    }

    private static List<String> getSdkList(String FilePath) throws IOException {
        return Files.readAllLines(Paths.get(FilePath));
    }

    private static List<String> getSsList(String FilePath) throws IOException {
        return Files.readAllLines(Paths.get(FilePath))
                .stream()
                .filter(x -> !(x.startsWith("-")))
                .map(x -> x.replace("|", ""))
                .collect(Collectors.toList());
    }

    private static Board read(List<String> LinesList) throws InsufficientRowLengthException, InsufficientRowException {
        List<List<Byte>> sudoku = new ArrayList<>();

        try {
            Iterator<String> sudokuIter = LinesList.iterator();

            for (int i = 0; i < 9; i++) {
                String row = sudokuIter.next();

                sudoku.add(new ArrayList<>());

                if (row.length() >= 9) {
                    for (int j = 0; j < 9; j++) {
                        char c = row.charAt(j);

                        sudoku.get(i).add(c == EMPTY_SYMBOL ? -1 : (byte) (c - 48));
                    }
                } else {
                    throw new InsufficientRowLengthException(i, row);
                }
            }
        } catch (NoSuchElementException e) {
            throw new InsufficientRowException();
        }

        return new Board(sudoku);
    }
}
