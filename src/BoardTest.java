import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Partial Solution of JUnit test on system console print comes from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println

public class BoardTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testStoreCorrect() {
        Board b = new Board("C:\\Users\\maple\\OneDrive\\SCC\\201901 WN\\CS 143\\Projects\\sudoku-solver\\puzzle.sdk");
        assertEquals("2..1.5..3", b.getRowString(0));
        assertEquals(".54...71.", b.getRowString(1));
        assertEquals(".1.2.3.8.", b.getRowString(2));
        assertEquals("6.28.73.4", b.getRowString(3));
        assertEquals(".........", b.getRowString(4));
        assertEquals("1.53.98.6", b.getRowString(5));
        assertEquals(".2.7.1.6.", b.getRowString(6));
        assertEquals(".81...24.", b.getRowString(7));
        assertEquals("7..4.2..1", b.getRowString(8));
    }

    @Test
    public void testPoorlyFormed() {
        Board b = new Board("C:\\Users\\maple\\OneDrive\\SCC\\201901 WN\\CS 143\\Projects\\sudoku-solver\\puzzle3.sdk");
        assertEquals("This board is invalid.", b.toString());
        b = new Board("C:\\Users\\maple\\OneDrive\\SCC\\201901 WN\\CS 143\\Projects\\sudoku-solver\\puzzle4.sdk");
        assertEquals("This board is invalid.", b.toString());
    }

    @Test
    public void testPrintConsole() {
        Board b = new Board("C:\\Users\\maple\\OneDrive\\SCC\\201901 WN\\CS 143\\Projects\\sudoku-solver\\puzzle.sdk");
        System.out.print(b);
        assertEquals("2..1.5..3\n.54...71.\n.1.2.3.8.\n6.28.73.4\n.........\n1.53.98.6\n.2.7.1.6.\n.81...24.\n7..4.2..1", outContent.toString());
    }

}
