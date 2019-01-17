package main;

public class Main {
    public static void main(String[] args) {
        Board b = BoardReader.readSafe("puzzle-normal.sdk");

        System.out.println(b.isSolvedDetail().toString().replace(",", "\n"));
    }
}
