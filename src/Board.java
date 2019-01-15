import java.util.ArrayList;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
    private ArrayList<ArrayList<Byte>> sudoku;
    private Predicate<Byte> filterEmptySlots;

    Board(ArrayList<ArrayList<Byte>> sudoku) {
        this.sudoku = sudoku;
        this.filterEmptySlots = x -> x != -1;
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
        IntFunction<Integer> blockMin = x -> x * 2;
        IntFunction<Integer> blockMax = x -> blockMin.apply(x) + 2;

        int rowBlock = Math.floorDiv(row, 3);
        int colBlock = Math.floorDiv(column, 3);

        ArrayList<Byte> al = new ArrayList<Byte>() {{
            add(number);
        }};

        for (int r = blockMin.apply(rowBlock); r < blockMax.apply(rowBlock); r++) {
            for (int c = blockMin.apply(colBlock); c < blockMax.apply(colBlock); c++) {
                al.add(sudoku.get(r).get(c));
            }
        }

        return validArrayList(al);
    }

    boolean validInRow(int row, byte number) {
        return validArrayList(new ArrayList<Byte>(sudoku.get(row)) {{
            add(number);
        }});
    }

    boolean validInColumn(int column, byte number) {
        ArrayList<Byte> al = new ArrayList<Byte>() {{
            add(number);
        }};
        for (int row = 0; row < 9; row++) al.add(sudoku.get(row).get(column));

        return validArrayList(al);
    }

    boolean validArrayList(ArrayList<Byte> al) {
        Stream<Byte> s = al.stream().filter(this.filterEmptySlots);

        return s.distinct().count() == s.count();
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

