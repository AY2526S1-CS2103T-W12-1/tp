package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.model.Model.COMPARATOR_SORT_BY_NAME_ASCENDING;
import static seedu.address.model.Model.COMPARATOR_SORT_BY_PRICE_DESCENDING;
import static seedu.address.model.Model.COMPARATOR_SORT_BY_PRIORITY_DESCENDING;

import java.util.Comparator;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attraction.Attraction;



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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRIORITY, PREFIX_PRICE);
        if (!hasOneSortablePrefix(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        Prefix prefix = getSortFieldPrefix(argMultimap);
        Comparator<Attraction> comparator = getComparatorFromPrefix(prefix);
        return new SortCommand(comparator);
    }

    /**
     * Returns true if exactly one prefix which is sortable is present in the given {@code ArgumentMultimap}.
     */
    private static boolean hasOneSortablePrefix(ArgumentMultimap argumentMultimap) {
        int count = 0;
        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            count++;
        }
        if (argumentMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            count++;
        }
        if (argumentMultimap.getValue(PREFIX_PRICE).isPresent()) {
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
        if (argumentMultimap.getValue(PREFIX_PRICE).isPresent()) {
            return PREFIX_PRICE;
        }
        // Code coverage cannot reach this line, defensive programming
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
        } else if (prefix.equals(PREFIX_PRICE)) {
            return COMPARATOR_SORT_BY_PRICE_DESCENDING;
        }
        // Code coverage cannot reach this line, defensive programming
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
