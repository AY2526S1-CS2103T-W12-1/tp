package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.attraction.Attraction;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
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

    private final Comparator<Attraction> comparator;

    /**
     * Creates a SortCommand to sort the attractions by the given comparator.
     */
    public SortCommand(Comparator<Attraction> comparator) {
        requireNonNull(comparator);
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAttractionList(PREDICATE_SHOW_ALL_ATTRACTIONS);
        //Must come after updating filtered list as line above resets sort.
        model.updateSortedAttractionList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherSortCommand = (SortCommand) other;
        return comparator.equals(otherSortCommand.comparator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("comparator", comparator)
                .toString();
    }
}
