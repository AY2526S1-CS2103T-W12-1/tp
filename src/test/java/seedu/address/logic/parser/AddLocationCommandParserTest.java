package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_ATTRACTION_INDEX_DESC_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_ATTRACTION_INDEX_DESC_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_NAME_DESC_SENTOSA;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_NAME_DESC_SINGAPORE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.location.AddLocationCommand;
import seedu.address.logic.parser.location.AddLocationCommandParser;
import seedu.address.model.location.LocationName;

public class AddLocationCommandParserTest {

    private final AddLocationCommandParser parser = new AddLocationCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        AddLocationCommand expectedCommand = new AddLocationCommand(new LocationName("Singapore"),
                Arrays.asList(Index.fromOneBased(1), Index.fromOneBased(2)));
        assertParseSuccess(parser,
                LOCATION_NAME_DESC_SINGAPORE + LOCATION_ATTRACTION_INDEX_DESC_FIRST
                        + LOCATION_ATTRACTION_INDEX_DESC_SECOND,
                expectedCommand);
    }

    @Test
    public void parse_duplicateLocationNamePrefix_failure() {
        assertParseFailure(parser,
                LOCATION_NAME_DESC_SINGAPORE + LOCATION_NAME_DESC_SENTOSA + LOCATION_ATTRACTION_INDEX_DESC_FIRST,
                Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_LOCATION_NAME));
    }

    @Test
    public void parse_missingLocationName_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLocationCommand.MESSAGE_USAGE);
        assertParseFailure(parser, LOCATION_ATTRACTION_INDEX_DESC_FIRST, expectedMessage);
    }

    @Test
    public void parse_missingIndexes_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLocationCommand.MESSAGE_USAGE);
        assertParseFailure(parser, LOCATION_NAME_DESC_SINGAPORE, expectedMessage);
    }

    @Test
    public void parse_invalidLocationName_failure() {
        assertParseFailure(parser,
                INVALID_LOCATION_NAME_DESC + LOCATION_ATTRACTION_INDEX_DESC_FIRST,
                LocationName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser,
                LOCATION_NAME_DESC_SINGAPORE + " " + CliSyntax.PREFIX_LOCATION_ATTRACTION_INDEX + "a",
                ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_preamblePresent_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLocationCommand.MESSAGE_USAGE);
        assertParseFailure(parser,
                "random" + LOCATION_NAME_DESC_SINGAPORE + LOCATION_ATTRACTION_INDEX_DESC_FIRST,
                expectedMessage);
    }
}
