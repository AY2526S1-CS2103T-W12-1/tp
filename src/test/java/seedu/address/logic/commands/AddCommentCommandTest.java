package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.attraction.Comment;

public class AddCommentCommandTest {
    private static final String VALID_COMMENT = "Expensive";
    private static final String INVALID_COMMENT = " ";

    private static final int VALID_INDEX = 1;


    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        Comment comment = new Comment(VALID_COMMENT);
        assertThrows(NullPointerException.class, () -> new AddCommentCommand(null, comment));
    }

    @Test
    public void constructor_nullComment_throwsNullPointerException() {
        Index index = Index.fromZeroBased(VALID_INDEX);
        assertThrows(NullPointerException.class, () -> new AddCommentCommand(index, null));
    }

    @Test
    public void equals() {
        Comment comment1 = new Comment(VALID_COMMENT);
        Comment comment2 = new Comment(VALID_COMMENT + "Test");
        Index index = Index.fromZeroBased(VALID_INDEX);

        AddCommentCommand command1 = new AddCommentCommand(index, comment1);
        AddCommentCommand command2 = new AddCommentCommand(index, comment2);

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // same values -> returns true
        AddCommentCommand command1Copy = new AddCommentCommand(index, comment1);
        assertTrue(command1.equals(command1Copy));

        // different types -> returns false
        assertFalse(command1.equals(1));

        // null -> returns false
        assertFalse(command1.equals(null));

        // different attraction -> returns false
        assertFalse(command1.equals(command2));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromZeroBased(VALID_INDEX);
        Comment comment = new Comment(VALID_COMMENT);
        AddCommentCommand command1 = new AddCommentCommand(index, comment);

        String expected = AddCommentCommand.class.getCanonicalName() + "{comment=" + VALID_COMMENT + "}";
        assertEquals(expected, command1.toString());
    }
}
