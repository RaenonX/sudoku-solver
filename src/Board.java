public class Board {
    private byte[][] sudoku;

    Board(byte[][] sudoku) {
        this.sudoku = sudoku;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int item = sudoku[i][j];

                sb.append(item == -1 ? Character.toString(BoardReader.EMPTY_SYMBOL) : Integer.toString(item));
            }

            if (i < 8) sb.append("\n");
        }

        return sb.toString();
    }
}

