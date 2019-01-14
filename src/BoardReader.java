import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.NoSuchElementException;

class InsufficientRowLengthException extends Exception {
    InsufficientRowLengthException(int rowNumber, String row) {
        super(String.format("Insufficient length at row %d: %d, loading terminated.", rowNumber, row.length()));
    }
}

class InsufficientRowException extends Exception {
    InsufficientRowException() {
        super("Insufficient rows to complete a sudoku board.");
    }
}

class BoardReader {
    static final char EMPTY_SYMBOL = '.';

    static Board read(String FilePath) throws InsufficientRowLengthException, InsufficientRowException, IOException {
        byte[][] sudoku = new byte[9][9];

        try {
            Iterator<String> sudokuIter = Files.readAllLines(Paths.get(FilePath)).iterator();

            for (int i = 0; i < 9; i++) {
                String row = sudokuIter.next();

                if (row.length() >= 9) {
                    for (int j = 0; j < 9; j++) {
                        char c = row.charAt(j);
                        sudoku[i][j] = c == EMPTY_SYMBOL ? -1 : (byte) (c - 48);
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

    static Board readSafe(String FilePath) {
        try {
            return BoardReader.read(FilePath);
        } catch (Exception e) {
            return null;
        }
    }
}
