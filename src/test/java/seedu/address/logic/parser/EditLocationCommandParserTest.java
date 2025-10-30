package seedu.address.logic.parser;

import static seedu.address.logic.commands.location.EditLocationCommand.Action.ADD;
import static seedu.address.logic.commands.location.EditLocationCommand.Action.REMOVE;
import static seedu.address.logic.commands.location.EditLocationCommand.MESSAGE_INVALID_ACTION;
import static seedu.address.logic.commands.location.EditLocationCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.location.EditLocationCommand;
import seedu.address.logic.parser.location.EditLocationCommandParser;
import seedu.address.model.location.LocationName;

public class EditLocationCommandParserTest {

    private static final String LOCATION_NAME = "ln/Singapore";
    private static final String ACTION_ADD = " action/ADD";
    private static final String ACTION_REMOVE = " action/REMOVE";
    private static final String INDEX_FIRST = " i/1";

    private final EditLocationCommandParser parser = new EditLocationCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        EditLocationCommand expectedCommand = new EditLocationCommand(new LocationName("Singapore"), ADD,
                Index.fromOneBased(1));
        assertParseSuccess(parser, " " + LOCATION_NAME + ACTION_ADD + INDEX_FIRST, expectedCommand);
    }

    @Test
    public void parse_removeAction_success() {
        EditLocationCommand expectedCommand = new EditLocationCommand(new LocationName("Singapore"), REMOVE,
                Index.fromOneBased(1));
        assertParseSuccess(parser, " " + LOCATION_NAME + ACTION_REMOVE + INDEX_FIRST, expectedCommand);
    }

    @Test
    public void parse_missingFields_failure() {
        assertParseFailure(parser, " act/ADD i/1",
                String.format("Invalid command format! \n%1$s", MESSAGE_USAGE));
        assertParseFailure(parser, " ln/Singapore i/1",
                String.format("Invalid command format! \n%1$s", MESSAGE_USAGE));
        assertParseFailure(parser, " ln/Singapore act/ADD",
                String.format("Invalid command format! \n%1$s", MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidAction_failure() {
        assertParseFailure(parser, " " + LOCATION_NAME + " action/UPDATE" + INDEX_FIRST, MESSAGE_INVALID_ACTION);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, " " + LOCATION_NAME + ACTION_ADD + " i/0",
                "Index is not a non-zero unsigned integer.");
    }
}
