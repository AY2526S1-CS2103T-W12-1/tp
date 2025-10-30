package seedu.address.logic.parser.location;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION_ACTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION_ATTRACTION_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.location.EditLocationCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.location.LocationName;

/**
 * Parses input arguments and creates a new {@link EditLocationCommand} object.
 */
public class EditLocationCommandParser implements Parser<EditLocationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@link EditLocationCommand}
     * and returns an {@code EditLocationCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditLocationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_LOCATION_NAME, PREFIX_LOCATION_ACTION, PREFIX_LOCATION_ATTRACTION_INDEX);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_LOCATION_NAME, PREFIX_LOCATION_ACTION,
                PREFIX_LOCATION_ATTRACTION_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_LOCATION_NAME, PREFIX_LOCATION_ACTION,
                PREFIX_LOCATION_ATTRACTION_INDEX) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLocationCommand.MESSAGE_USAGE));
        }

        LocationName locationName = ParserUtil.parseLocationName(argMultimap.getValue(PREFIX_LOCATION_NAME).get());

        EditLocationCommand.Action action;
        try {
            action = EditLocationCommand.Action.fromString(argMultimap.getValue(PREFIX_LOCATION_ACTION).get());
        } catch (IllegalArgumentException e) {
            throw new ParseException(EditLocationCommand.MESSAGE_INVALID_ACTION, e);
        }

        Index attractionIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_LOCATION_ATTRACTION_INDEX).get());

        return new EditLocationCommand(locationName, action, attractionIndex);
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
