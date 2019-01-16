package Main;

import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private List<List<Byte>> sudoku;

    Board(List<List<Byte>> sudoku) {
        this.sudoku = sudoku;
    }

    public String toString() {
        return sudoku
                .stream()
                .map(row -> row
                        .stream()
                        .map(x -> x == -1 ? BoardReader.EMPTY_SYMBOL : x.toString())
                        .map(Object::toString)
                        .collect(Collectors.joining()))
                .collect(Collectors.joining("\n"));
    }
}

