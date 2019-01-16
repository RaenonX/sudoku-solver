import main.BoardReader;
import main.Exceptions.InsufficientRowException;
import main.Exceptions.InsufficientRowLengthException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class BoardTestSs {
    @Test
    public void testPoorlyFormed() {
        try {
            BoardReader.read("puzzle-lessCol.ss");
            Assert.fail("Should get an main.InsufficientRowLengthException - puzzle-lessCol.ss");
        } catch (InsufficientRowLengthException e) {
        } catch (Exception e) {
            Assert.fail("Should get an " + e.getMessage() + " - puzzle-lessCol.ss");
        }

        try {
            BoardReader.read("puzzle-lessRow.ss");
            Assert.fail("Should get an main.InsufficientRowException - puzzle-lessRow.ss");
        } catch (InsufficientRowException e) {
        } catch (Exception e) {
            Assert.fail("Should get an " + e.getMessage() + " - puzzle-lessRow.ss");
        }

        try {
            BoardReader.read("puzzle5.ss");
            Assert.fail("Should get an IOException - puzzle5.ss");
        } catch (IOException e) {
        } catch (Exception e) {
            Assert.fail("Should get an " + e.getMessage() + " - puzzle5.ss");
        }
    }

    @Test
    public void testContent() {
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
                            "7..4.2..1", BoardReader.read("puzzle-normal.ss").toString());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testContentConcatenateColumn() {
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
                            "7..4.2..1", BoardReader.read("puzzle-extraCol.ss").toString());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
