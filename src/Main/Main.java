package Main;

public class Main {
    public static void main(String[] args) {
        Board b = BoardReader.readSafe("puzzle.sdk");
        System.out.println(b);
        b = BoardReader.readSafe("puzzle.ss");
        System.out.println(b);
    }
}
