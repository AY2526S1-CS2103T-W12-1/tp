package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddItineraryCommand;
import seedu.address.model.itinerary.ItineraryName;

class AddItineraryCommandParserTest {

    private final AddItineraryCommandParser parser = new AddItineraryCommandParser();

    @Test
    void parse_validArgs_success() {
        AddItineraryCommand expectedCommand = new AddItineraryCommand(
                new ItineraryName("Weekend Trip"),
                Arrays.asList(Index.fromOneBased(1), Index.fromOneBased(3)));

        assertParseSuccess(parser, " n/Weekend Trip ai/1 ai/3", expectedCommand);
    }

    @Test
    void parse_validArgsWithoutIndexes_success() {
        AddItineraryCommand expectedCommand = new AddItineraryCommand(
                new ItineraryName("Empty Trip"), Arrays.asList());

        assertParseSuccess(parser, " n/Empty Trip", expectedCommand);
    }

    @Test
    void parse_missingName_failure() {
        assertParseFailure(parser, " ai/1 ai/2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItineraryCommand.MESSAGE_USAGE));
    }

    @Test
    void parse_invalidIndex_failure() {
        assertParseFailure(parser, " n/Weekend Trip ai/a", MESSAGE_INVALID_INDEX);
    }
}
