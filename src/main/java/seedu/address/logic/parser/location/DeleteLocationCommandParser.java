package seedu.address.logic.parser.location;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION_NAME;

import seedu.address.logic.commands.location.DeleteLocationCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.location.LocationName;

/**
 * Parses input arguments and creates a new {@link DeleteLocationCommand} object.
 */
public class DeleteLocationCommandParser implements Parser<DeleteLocationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@link DeleteLocationCommand}
     * and returns a {@code DeleteLocationCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteLocationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LOCATION_NAME);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_LOCATION_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_LOCATION_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteLocationCommand.MESSAGE_USAGE));
        }

        LocationName locationName = ParserUtil.parseLocationName(
                argMultimap.getValue(PREFIX_LOCATION_NAME).get());

        return new DeleteLocationCommand(locationName);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        for (Prefix prefix : prefixes) {
            if (argumentMultimap.getValue(prefix).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
