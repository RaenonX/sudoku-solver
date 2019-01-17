package main;

import main.Exceptions.EntryOutOfBoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// Partial code of validateColumn comes from https://stackoverflow.com/a/18552071

public class Board {
    private List<List<Byte>> sudoku;
    private Predicate<Byte> filterEmpty;

    public Board(List<List<Byte>> sudoku) {
        this.sudoku = sudoku;
        this.filterEmpty = x -> x != (byte) -1;
    }

    public static byte validateEntry(byte num) throws EntryOutOfBoundException {
        if ((num >= 1 && num <= 9) || num == -1) {
            return num;
        } else {
            throw new EntryOutOfBoundException(num);
        }
    }

    public void insertEntry(int rowNum, int colNum, int number) throws EntryOutOfBoundException {
        insertEntry(rowNum, colNum, (byte) number);
    }

    public void insertEntry(int rowNum, int colNum, byte number) throws EntryOutOfBoundException {
        if (number < 1 || number > 9) {
            throw new EntryOutOfBoundException(number);
        }

        sudoku.get(rowNum).set(colNum, number);
    }

    public List<ValidationResult> isSolvedDetail() {
        return IntStream.range(0, 9).mapToObj(x -> new ValidationResult[]{validateRow(x), validateColumn(x), validateSquare(x)}).flatMap(Stream::of).collect(Collectors.toList());
    }

    public boolean isSolved() {
        return isSolvedDetail().stream().allMatch(x -> x.getValidationResult() == ValidationResultEnum.SuccessFilled);
    }

    public List<ValidationResult> insertEntryValidate(int rowNum, int colNum, byte number) throws EntryOutOfBoundException {
        insertEntry(rowNum, colNum, number);

        return new ArrayList<ValidationResult>() {{
            add(validateRow(rowNum));
            add(validateColumn(colNum));
            add(validateSquare(rowNum, colNum));
        }};
    }

    public ValidationResult validateSquare(int squareIndex) {
        int rowStart = Math.floorDiv(squareIndex, 3) * 3;
        int colStart = squareIndex % 3 * 3;

        List<Byte> l = new ArrayList<>();

        IntStream
                .range(rowStart, rowStart + 3)
                .forEach(
                        r -> l.addAll(
                                IntStream
                                        .range(colStart, colStart + 3)
                                        .mapToObj(c -> sudoku.get(r).get(c))
                                        .filter(filterEmpty)
                                        .collect(Collectors.toList())
                        )
                );

        return validate(l, ValidationTargetEnum.Square, squareIndex);
    }

    public ValidationResult validateSquare(int rowNum, int colNum) {
        return validateSquare(Math.floorDiv(rowNum, 3) * 3 + Math.floorDiv(colNum, 3));
    }

    public ValidationResult validateColumn(int colNum) {
        List<Byte> l = sudoku.stream().map(x -> x.get(colNum)).collect(Collectors.toList());

        return validate(l, ValidationTargetEnum.Column, colNum);
    }

    public ValidationResult validateRow(int rowNum) {
        List<Byte> l = sudoku.get(rowNum);

        return validate(l, ValidationTargetEnum.Row, rowNum);
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

    ValidationResult validate(List<Byte> l, ValidationTargetEnum target, int position) {
        Stream<Byte> s = l.stream().filter(filterEmpty), s2 = l.stream().filter(filterEmpty);

        int setCount = ((int) s.distinct().count());
        int totalCount = ((int) s2.count());

        ValidationResultEnum result;

        if (setCount == totalCount) {
            result = setCount == 9 ? ValidationResultEnum.SuccessFilled : ValidationResultEnum.SuccessWithEmpty;
        } else {
            result = ValidationResultEnum.Failed;
        }

        return new ValidationResult(result, target, position);
    }
}

