import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Board {
    private ArrayList<ArrayList<Byte>> sudoku;

    Board(ArrayList<ArrayList<Byte>> sudoku) {
        this.sudoku = sudoku;
    }

    InsertResult insertSlot(int row, int column, byte number) {
        if (this.sudoku.get(row).get(column) == -1) {
            if (validInSquare(row, column, number) && validInRow(row, number) && validInColumn(column, number)) {
                return InsertResult.Success;
            } else {
                return InsertResult.Conflicted;
            }
        } else {
            return InsertResult.Preset;
        }
    }

    boolean validInSquare(int row, int column, byte number) {
        throw new NotImplementedException();
    }

    boolean validInRow(int row, byte number) {
        throw new NotImplementedException();
    }

    boolean validInColumn(int column, byte number) {
        throw new NotImplementedException();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (ArrayList<Byte> row : sudoku) {
            sb.append(row.stream().map(x -> x == -1 ? BoardReader.EMPTY_SYMBOL : x.toString()).map(Object::toString).collect(Collectors.joining()));
        }

        return sb.toString();
    }
}

