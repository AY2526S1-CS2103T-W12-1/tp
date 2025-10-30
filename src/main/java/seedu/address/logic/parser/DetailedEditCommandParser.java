package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DetailedEditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for DetailedEditCommand
 */
public class DetailedEditCommandParser implements Parser<DetailedEditCommand> {
    /**
     * Parses command string and abstracts the index value
     * @param args command string
     * @return DetailedEditCommand containing the correct index value
     * @throws ParseException when message is in an invalid format
     */
    public DetailedEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DetailedEditCommand.MESSAGE_USAGE), pe);
        }

        return new DetailedEditCommand(index);

    }
}
