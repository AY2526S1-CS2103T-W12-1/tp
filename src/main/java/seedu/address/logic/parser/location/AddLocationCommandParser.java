package seedu.address.logic.parser.location;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION_ATTRACTION_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION_NAME;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.location.AddLocationCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.location.LocationName;

/**
 * Parses input arguments and creates a new {@link AddLocationCommand} object.
 */
public class AddLocationCommandParser implements Parser<AddLocationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@link AddLocationCommand}
     * and returns an {@code AddLocationCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddLocationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LOCATION_NAME, PREFIX_LOCATION_ATTRACTION_INDEX);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_LOCATION_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_LOCATION_NAME)
                || argMultimap.getAllValues(PREFIX_LOCATION_ATTRACTION_INDEX).isEmpty()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddLocationCommand.MESSAGE_USAGE));
        }

        LocationName locationName = ParserUtil.parseLocationName(
                argMultimap.getValue(PREFIX_LOCATION_NAME).get());

        List<Index> attractionIndexes = new ArrayList<>();
        for (String indexString : argMultimap.getAllValues(PREFIX_LOCATION_ATTRACTION_INDEX)) {
            attractionIndexes.add(ParserUtil.parseIndex(indexString));
        }

        return new AddLocationCommand(locationName, attractionIndexes);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        for (Prefix prefix : prefixes) {
            if (argumentMultimap.getValue(prefix).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
