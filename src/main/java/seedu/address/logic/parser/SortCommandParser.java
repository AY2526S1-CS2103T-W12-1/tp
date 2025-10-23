package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.COMPARATOR_SORT_BY_ADDRESS_ASCENDING;
import static seedu.address.model.Model.COMPARATOR_SORT_BY_NAME_ASCENDING;
import static seedu.address.model.Model.COMPARATOR_SORT_BY_CONTACT_ASCENDING;
import static seedu.address.model.Model.COMPARATOR_SORT_BY_PRIORITY_DESCENDING;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attraction.Attraction;

import java.util.Comparator;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRIORITY, PREFIX_CONTACT,
                        PREFIX_ADDRESS, PREFIX_ACTIVITIES, PREFIX_TAG);
        if (!hasOnlyOnePrefix(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        return new SortCommand(getComparatorFromPrefix(getSortFieldPrefix(argMultimap)));
    }

    /**
     * Returns true if only one of the prefixes is present in the given {@code ArgumentMultimap}.
     */
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

    /**
     * Returns the prefix that is present in the given {@code ArgumentMultimap}.
     * @throws ParseException if no valid prefix is found.
     */
    private static Prefix getSortFieldPrefix(ArgumentMultimap argumentMultimap) throws ParseException {
        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            return PREFIX_NAME;
        }
        if (argumentMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            return PREFIX_PRIORITY;
        }
        if (argumentMultimap.getValue(PREFIX_CONTACT).isPresent()) {
            return PREFIX_CONTACT;
        }
        if (argumentMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            return PREFIX_ADDRESS;
        }
        if (argumentMultimap.getValue(PREFIX_ACTIVITIES).isPresent()) {
            return PREFIX_ACTIVITIES;
        }
        if (argumentMultimap.getValue(PREFIX_TAG).isPresent()) {
            return PREFIX_TAG;
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    /**
     * Returns the comparator corresponding to the given prefix.
     * @throws ParseException if no valid comparator is found.
     */
    private static Comparator<Attraction> getComparatorFromPrefix(Prefix prefix) throws ParseException {
        if (prefix.equals(PREFIX_NAME)) {
            return COMPARATOR_SORT_BY_NAME_ASCENDING;
        } else if (prefix.equals(PREFIX_PRIORITY)) {
            return COMPARATOR_SORT_BY_PRIORITY_DESCENDING;
        } else if (prefix.equals(PREFIX_CONTACT)) {
            return COMPARATOR_SORT_BY_CONTACT_ASCENDING;
        } else if (prefix.equals(PREFIX_ADDRESS)) {
            return COMPARATOR_SORT_BY_ADDRESS_ASCENDING;
        } else if (prefix.equals(PREFIX_ACTIVITIES)) {
            // No comparator defined for activities yet
        } else if (prefix.equals(PREFIX_TAG)) {
            // No comparator defined for tags yet
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
