package seedu.address.model.attraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommentTest {
    private static final String INVALID_COMMENT = " ";

    @Test
    public void constructor_invalidComment_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Comment(INVALID_COMMENT));
    }

    @Test
    public void isValidComment() {
        String commentWithWhiteSpace = "                 aaaaaa               ";
        String commentWithSpecialCharacters = " &&& 8A*  *A**)@!#$";
        String longComment = "AAAAAALDJKHF S ALJH   ASLKJDH D    ALSKDJHDLKAJHDLKJAH   ALSKJDALKDJHALD ";

        String whiteSpace = " ";
        String longWhiteSpace = "       ";

        assertTrue(Comment.isValidComment(commentWithWhiteSpace));
        assertTrue(Comment.isValidComment(commentWithSpecialCharacters));
        assertTrue(Comment.isValidComment(longComment));

        assertFalse(Comment.isValidComment(whiteSpace));
        assertFalse(Comment.isValidComment(longWhiteSpace));
    }

    @Test
    public void equals() {
        Comment comment1 = new Comment("test1");
        Comment comment1Duplicate = new Comment("test1");
        Comment comment2 = new Comment("test2");

        assertTrue(comment1.equals(comment1));
        assertFalse(comment1.equals(comment2));
        assertTrue(comment1.equals(comment1Duplicate));
    }

    @Test
    public void toString_returnsCorrectValue() {
        String commentString = "test";
        Comment testComment = new Comment(commentString);
        assertEquals(testComment.toString(), commentString);
    }
}
