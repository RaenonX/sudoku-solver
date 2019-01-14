import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

// Partial Solution of JUnit test on system console print comes from https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println

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
            Assert.assertEquals("2..1.5..5\n.54...71.\n.1.2.3.8.\n6.28.73.4\n.........\n1.53.98.6\n.2.7.1.6.\n.81...24.\n7..4.2..1", BoardReader.read("puzzle5.sdk").toString());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

}
