package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRIORITY, PREFIX_CONTACT,
                        PREFIX_ADDRESS, PREFIX_ACTIVITIES, PREFIX_TAG);
        if (!hasOnlyOnePrefix(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        return new SortCommand(getSortFieldPrefix(argMultimap));
    }

    private static boolean hasOnlyOnePrefix(ArgumentMultimap argumentMultimap) {
        int count = 0;
        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            count++;
        }
        if (argumentMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            count++;
        }
        if (argumentMultimap.getValue(PREFIX_CONTACT).isPresent()) {
            count++;
        }
        if (argumentMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            count++;
        }
        if (argumentMultimap.getValue(PREFIX_ACTIVITIES).isPresent()) {
            count++;
        }
        if (argumentMultimap.getValue(PREFIX_TAG).isPresent()) {
            count++;
        }
        return count == 1;
    }

    private static String getSortFieldPrefix(ArgumentMultimap argumentMultimap) {
        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            return PREFIX_NAME.getPrefix();
        }
        if (argumentMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            return PREFIX_PRIORITY.getPrefix();
        }
        if (argumentMultimap.getValue(PREFIX_CONTACT).isPresent()) {
            return PREFIX_CONTACT.getPrefix();
        }
        if (argumentMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            return PREFIX_ADDRESS.getPrefix();
        }
        if (argumentMultimap.getValue(PREFIX_ACTIVITIES).isPresent()) {
            return PREFIX_ACTIVITIES.getPrefix();
        }
        if (argumentMultimap.getValue(PREFIX_TAG).isPresent()) {
            return PREFIX_TAG.getPrefix();
        }
        // This line should never be reached because hasOnlyOnePrefix ensures at least one prefix is present.
        return "";
    }
}
