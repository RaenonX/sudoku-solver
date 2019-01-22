import main.BoardReader;
import main.Exceptions.InsufficientRowException;
import main.Exceptions.InsufficientRowLengthException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class BoardTestSdk {
    @Test
    public void testPoorlyFormed() {
        try {
            BoardReader.read("puzzle-lessCol.sdk");
            Assert.fail("Should get an main.InsufficientRowLengthException - puzzle-lessCol.sdk");
        } catch (InsufficientRowLengthException e) {
        } catch (Exception e) {
            Assert.fail("Should get an " + e.getMessage() + " - puzzle-lessCol.sdk");
        }

        try {
            BoardReader.read("puzzle-lessRow.sdk");
            Assert.fail("Should get an main.InsufficientRowException - puzzle-lessRow.sdk");
        } catch (InsufficientRowException e) {
        } catch (Exception e) {
            Assert.fail("Should get an " + e.getMessage() + " - puzzle-lessRow.sdk");
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
                            "7..4.2..1", BoardReader.read("puzzle-normal.sdk").toString());
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
                            "7..4.2..1", BoardReader.read("puzzle-extraCol.sdk").toString());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
