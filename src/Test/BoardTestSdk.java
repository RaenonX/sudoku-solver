import Main.BoardReader;
import Main.Exceptions.InsufficientRowException;
import Main.Exceptions.InsufficientRowLengthException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class BoardTestSdk {
    @Test
    public void testPoorlyFormed() {
        try {
            BoardReader.read("puzzle3.sdk");
            Assert.fail("Should get an Main.InsufficientRowLengthException - puzzle3.sdk");
        } catch (InsufficientRowLengthException e) {
        } catch (Exception e) {
            Assert.fail("Should get an " + e.getMessage() + " - puzzle3.sdk");
        }

        try {
            BoardReader.read("puzzle4.sdk");
            Assert.fail("Should get an Main.InsufficientRowException - puzzle4.sdk");
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
                            "7..4.2..1", BoardReader.read("puzzle.sdk").toString());
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
                            "7..4.2..1", BoardReader.read("puzzle2.sdk").toString());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
