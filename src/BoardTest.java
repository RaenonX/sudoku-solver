import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class BoardTest {
    @Test
    public void testPoorlyFormed() {
        try {
            BoardReader.read("puzzle3.sdk");
            Assert.fail("Should get an InsufficientRowLengthException - puzzle3.sdk");
        } catch (InsufficientRowLengthException e) {
        } catch (Exception e) {
            Assert.fail("Should get an " + e.getMessage() + " - puzzle3.sdk");
        }

        try {
            BoardReader.read("puzzle4.sdk");
            Assert.fail("Should get an InsufficientRowException - puzzle4.sdk");
        } catch (InsufficientRowException e) {
        } catch (Exception e) {
            Assert.fail("Should get an " + e.getMessage() + " - puzzle4.sdk");
        }

        try {
            BoardReader.read("puzzle5.sdk");
            Assert.fail("Should get an IOException - puzzle5.sdk");
        } catch (IOException e) {
        } catch (Exception e) {
            Assert.fail("Should get an " + e.getMessage() + " - puzzle5.sdk");
        }
    }

    @Test
    public void testPrintConsole() {
        try {
            Assert.assertEquals(
                    "2..1.5..3\n" +
                            ".54...71.\n" +
                            ".1.2.3.8.\n" +
                            "6.28.73.4\n" +
                            ".........\n" +
                            "1.53.98.6\n" +
                            ".2.7.1.6.\n" +
                            ".81...24.\n" +
                            "7..4.2..1", BoardReader.read("puzzle.sdk").toString());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testInsertButConflict() {
        Assert.assertEquals(InsertResult.Conflicted, BoardReader.readSafe("puzzle.sdk").insertSlot(3, 1, (byte) 1));
    }

    @Test
    public void testInsertButPreset() {
        Assert.assertEquals(InsertResult.Preset, BoardReader.readSafe("puzzle.sdk").insertSlot(3, 0, (byte) 1));
    }

    @Test
    public void testInsertSuccess() {
        Assert.assertEquals(InsertResult.Success, BoardReader.readSafe("puzzle.sdk").insertSlot(3, 4, (byte) 1));
    }

    @Test
    public void testRowValidate() {
        Board b = BoardReader.readSafe("puzzle.sdk");
        Assert.assertFalse(b.validInRow(3, (byte) 6));
        Assert.assertTrue(b.validInRow(3, (byte) 1));
    }

    @Test
    public void testColumnValidate() {
        Board b = BoardReader.readSafe("puzzle.sdk");
        Assert.assertFalse(b.validInColumn(3, (byte) 1));
        Assert.assertTrue(b.validInColumn(3, (byte) 5));
    }

    @Test
    public void testSquareValidate() {
        Board b = BoardReader.readSafe("puzzle.sdk");
        Assert.assertFalse(b.validInSquare(0, 1, (byte) 5));
        Assert.assertTrue(b.validInSquare(0, 1, (byte) 8));
    }
}
