package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ATTRACTION;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.location.AddLocationCommand;
import seedu.address.logic.commands.location.DeleteLocationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.NameContainsKeywordsPredicate;
import seedu.address.model.location.LocationName;
import seedu.address.testutil.AttractionBuilder;
import seedu.address.testutil.AttractionUtil;
import seedu.address.testutil.EditAttractionDescriptorBuilder;

public class MapletParserTest {

    private final MapletParser parser = new MapletParser();

    @Test
    public void parseCommand_add() throws Exception {
        Attraction attraction = new AttractionBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(AttractionUtil.getAddCommand(attraction));
        assertEquals(new AddCommand(attraction), command);
    }

    @Test
    public void parseCommand_addLocation() throws Exception {
        AddLocationCommand command = (AddLocationCommand) parser.parseCommand(
                AddLocationCommand.COMMAND_WORD + " "
                        + "ln/Singapore "
                        + "i/1 i/2");
        assertEquals(new AddLocationCommand(new LocationName("Singapore"),
                Arrays.asList(Index.fromOneBased(1), Index.fromOneBased(2))), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_ATTRACTION.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_ATTRACTION), command);
    }

    @Test
    public void parseCommand_deleteLocation() throws Exception {
        DeleteLocationCommand command = (DeleteLocationCommand) parser.parseCommand(
                DeleteLocationCommand.COMMAND_WORD + " " + "ln/Singapore");
        assertEquals(new DeleteLocationCommand(new LocationName("Singapore")), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Attraction attraction = new AttractionBuilder().build();
        EditCommand.EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder(attraction).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ATTRACTION.getOneBased() + " "
                + AttractionUtil.getEditAttractionDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_ATTRACTION, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " n/") instanceof SortCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
