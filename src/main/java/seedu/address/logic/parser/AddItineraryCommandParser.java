package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITINERARY_ATTRACTION_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddItineraryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.itinerary.ItineraryName;

/**
 * Parses input arguments and creates a new AddItineraryCommand object.
 */
public class AddItineraryCommandParser implements Parser<AddItineraryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddItineraryCommand
     * and returns an AddItineraryCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public AddItineraryCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ITINERARY_ATTRACTION_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddItineraryCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);

        ItineraryName itineraryName = ParserUtil.parseItineraryName(argMultimap.getValue(PREFIX_NAME).get());
        List<Index> attractionIndexes = new ArrayList<>();
        for (String indexString : argMultimap.getAllValues(PREFIX_ITINERARY_ATTRACTION_INDEX)) {
            attractionIndexes.add(ParserUtil.parseIndex(indexString));
        }

        return new AddItineraryCommand(itineraryName, attractionIndexes);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
