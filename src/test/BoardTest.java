import main.Board;
import main.BoardReader;
import main.Exceptions.EntryExistedException;
import main.Exceptions.EntryOutOfBoundException;
import main.ValidationResultEnum;
import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
    @Test
    public void testValidations() throws EntryOutOfBoundException, EntryExistedException {
        Board b = BoardReader.readSafe("puzzle-board.ss");

        b.insertEntry(1, 0, 1);

        Assert.assertEquals(ValidationResultEnum.FailedConflicted, b.validateSquare(1, 0).getValidationResult());
        Assert.assertEquals(ValidationResultEnum.FailedConflicted, b.validateRow(1).getValidationResult());
        Assert.assertEquals(ValidationResultEnum.FailedConflicted, b.validateColumn(0).getValidationResult());
        Assert.assertEquals(ValidationResultEnum.SuccessWithEmpty, b.validateSquare(3, 3).getValidationResult());
        Assert.assertEquals(ValidationResultEnum.SuccessWithEmpty, b.validateRow(3).getValidationResult());
        Assert.assertEquals(ValidationResultEnum.SuccessWithEmpty, b.validateColumn(3).getValidationResult());
    }

    @Test(expected = EntryOutOfBoundException.class)
    public void testInsert() throws EntryOutOfBoundException, EntryExistedException {
        Board b = BoardReader.readSafe("puzzle-board.ss");

        b.insertEntry(1, 0, -1);
    }

    @Test
    public void testValidEntryValidation() throws EntryOutOfBoundException {
        for (byte i = 1; i <= 9; i++) {
            Board.validateEntry(i);
        }
        Board.validateEntry((byte) -1);
    }

    @Test(expected = EntryOutOfBoundException.class)
    public void testInvalidEntryValidation() throws EntryOutOfBoundException {
        Board.validateEntry((byte) 0);
    }

    @Test
    public void testIsSolved() {
        Assert.assertFalse(BoardReader.readSafe("puzzle-board.ss").isSolved());
        Assert.assertTrue(BoardReader.readSafe("puzzle-filled.ss").isSolved());
    }
}
