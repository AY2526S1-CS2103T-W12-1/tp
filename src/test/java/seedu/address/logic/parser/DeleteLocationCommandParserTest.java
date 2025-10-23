package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_NAME_DESC_SENTOSA;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_NAME_DESC_SINGAPORE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.location.DeleteLocationCommand;
import seedu.address.logic.parser.location.DeleteLocationCommandParser;
import seedu.address.model.location.LocationName;

public class DeleteLocationCommandParserTest {

    private final DeleteLocationCommandParser parser = new DeleteLocationCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteLocationCommand() {
        DeleteLocationCommand expectedCommand = new DeleteLocationCommand(new LocationName("Singapore"));
        assertParseSuccess(parser, LOCATION_NAME_DESC_SINGAPORE, expectedCommand);
    }

    @Test
    public void parse_missingPrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLocationCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "Singapore", expectedMessage);
    }

    @Test
    public void parse_duplicatePrefix_failure() {
        assertParseFailure(parser,
                LOCATION_NAME_DESC_SINGAPORE + LOCATION_NAME_DESC_SENTOSA,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_LOCATION_NAME));
    }

    @Test
    public void parse_invalidLocationName_failure() {
        assertParseFailure(parser, INVALID_LOCATION_NAME_DESC, LocationName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_preamblePresent_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLocationCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "random" + LOCATION_NAME_DESC_SINGAPORE, expectedMessage);
    }
}
