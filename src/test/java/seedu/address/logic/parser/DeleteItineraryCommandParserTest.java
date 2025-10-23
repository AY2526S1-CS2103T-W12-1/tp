package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteItineraryCommand;

class DeleteItineraryCommandParserTest {

    private final DeleteItineraryCommandParser parser = new DeleteItineraryCommandParser();

    @Test
    void parse_validArgs_returnsDeleteItineraryCommand() {
        assertParseSuccess(parser, "1", new DeleteItineraryCommand(Index.fromOneBased(1)));
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteItineraryCommand.MESSAGE_USAGE));
    }
}
