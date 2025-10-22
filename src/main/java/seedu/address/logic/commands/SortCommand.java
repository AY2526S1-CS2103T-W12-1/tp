package seedu.address.logic.commands;

import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ATTRACTIONS;

/**
 * Sorts all attractions in the Maplet by field.
 */
public class SortCommand extends Command{
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all attractions in the Maplet by field PREFIX. "
            + "Parameters: "
            + "PREFIX\n"
            + "Examples: " + COMMAND_WORD + " "
            + PREFIX_NAME + ", "
            + COMMAND_WORD + " "
            + PREFIX_PRIORITY + ", "
            + COMMAND_WORD + " "
            + PREFIX_CONTACT + ", "
            + COMMAND_WORD + " "
            + PREFIX_ADDRESS;

    public static final String MESSAGE_SUCCESS = "Listed all attractions";

    private final String sortPrefix;

    public SortCommand(String sortPrefix) {
        this.sortPrefix = sortPrefix;
    }

    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAttractionList(PREDICATE_SHOW_ALL_ATTRACTIONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
