package main;

public class Main {
    public static void main(String[] args) {
        Board b = BoardReader.readSafe("puzzle-board.ss");

        System.out.println(b);
        System.out.println(b.autoSolve());
        System.out.println(b);
    }
}
