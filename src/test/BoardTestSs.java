import main.BoardReader;
import org.junit.Assert;
import org.junit.Test;

public class BoardTestSs {
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
                            "7..4.2..1", BoardReader.read("puzzle-board.ss").toString());
        } catch (Exception e) {
            Assert.fail(e.getClass().getName() + " " + e.getMessage());
        }
    }
}
