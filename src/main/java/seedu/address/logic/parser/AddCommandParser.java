package seedu.address.logic.parser;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import seedu.address.logic.commands.AddCommand;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attraction.Activities;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Contact;
import seedu.address.model.attraction.Name;
import seedu.address.model.attraction.Priority;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddCommand and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected
     * format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap
                = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PRIORITY,
                        PREFIX_CONTACT, PREFIX_ADDRESS, PREFIX_ACTIVITIES, PREFIX_TAG);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS,
                PREFIX_PRIORITY, PREFIX_CONTACT, PREFIX_ACTIVITIES)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getAllValues(PREFIX_TAG).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
                PREFIX_NAME, PREFIX_PRIORITY, PREFIX_CONTACT, PREFIX_ADDRESS, PREFIX_ACTIVITIES);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Priority priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
        Contact contact = ParserUtil.parseContact(argMultimap.getValue(PREFIX_CONTACT).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Activities activities = ParserUtil.parseActivities(argMultimap.getValue(PREFIX_ACTIVITIES).get());
        Set<Tag> tagList = Collections.emptySet();

        Attraction attraction = new Attraction(name, priority, contact, address, activities, tagList);

        return new AddCommand(attraction);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional}
     * values in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
