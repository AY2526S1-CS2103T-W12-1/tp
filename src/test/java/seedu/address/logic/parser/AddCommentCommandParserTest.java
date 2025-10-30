package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attraction.Comment;


public class AddCommentCommandParserTest {
    private static final String VALID_INDEX = " 1";
    private static final String INVALID_INDEX = " -1";
    private static final String VALID_COMMENT = " com/test";
    private static final String INVALID_COMMENT = " com/ ";

    private AddCommentCommandParser parser = new AddCommentCommandParser();
    @Test
    public void parse_allFieldsPresent_success() {
        Index index = Index.fromZeroBased(0);
        AddCommentCommand expectedCommand = new AddCommentCommand(index,
                new Comment("test"));
        assertParseSuccess(parser, VALID_INDEX + VALID_COMMENT, expectedCommand);
    }

    @Test
    public void parse_invalidIndex_parseException() throws ParseException {
        assertThrows(ParseException.class, () ->
                new AddCommentCommandParser().parse("comment" + INVALID_INDEX + VALID_COMMENT));
    }

    @Test
    public void parse_invalidComment_parseException() throws ParseException {
        assertThrows(ParseException.class, () ->
                new AddCommentCommandParser().parse("comment" + VALID_INDEX + INVALID_COMMENT));
    }

}
