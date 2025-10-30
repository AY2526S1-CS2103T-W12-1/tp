package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;


/**
 * Parses input and creates new AddCommentCommand object
 */
public class AddCommentCommandParser implements Parser<AddCommentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommentCommand
     * and returns an AddCommentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommentCommand parse(String args) throws ParseException{
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMMENT);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommentCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMMENT);

        if (argMultimap.getValue(PREFIX_COMMENT).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommentCommand.MESSAGE_USAGE));
        }

        return new AddCommentCommand(index, ParserUtil.parseComment(argMultimap.getValue(PREFIX_COMMENT).get()));
    }
}
