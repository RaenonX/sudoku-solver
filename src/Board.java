import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Board {
    public static final char EMPTY_SYMBOL = '.';

    private int[][] sudoku;
    private boolean valid;

    public Board(String FilePath) {
        sudoku = new int[9][9];
        try {
            Iterator<String> sudokuIter = Files.readAllLines(Paths.get(FilePath)).iterator();

            for (int i = 0; i < 9; i++) {
                String row = sudokuIter.next();

                if (row.length() >= 9) {
                    for (int j = 0; j < 9; j++) {
                        char c = row.charAt(j);
                        sudoku[i][j] = c == EMPTY_SYMBOL ? -1 : Character.getNumericValue(c);
                    }
                } else {
                    System.out.println(String.format("Insufficient length at row %d: %d, loading terminated.", i, row.length()));
                    valid = false;
                    return;
                }
            }

            valid = true;
        } catch (IOException e) {
            System.out.println(String.format("File not found. Path: %s", FilePath));
            valid = false;
        } catch (NoSuchElementException e) {
            System.out.println("Insufficient rows to complete a sudoku board.");
            valid = false;
        }
    }

    /// Return empty string if the board is invalid.
    public String getRowString(int index) {
        if (!valid) {
            return "";
        } else if (index < 0 || index > 8) {
            throw new IllegalArgumentException("Row index out of range.");
        } else {
            return String.join("", Arrays.stream(sudoku[index]).mapToObj(String::valueOf).map(x -> x.equals("-1") ? Character.toString(EMPTY_SYMBOL) : x).toArray(String[]::new));
        }
    }

    public String toString() {
        if (valid) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int item = sudoku[i][j];

                    sb.append(item == -1 ? Character.toString(EMPTY_SYMBOL) : Integer.toString(item));
                }

                if (i < 8) sb.append("\n");
            }

            return sb.toString();
        } else {
            return "This board is invalid.";
        }
    }
}

