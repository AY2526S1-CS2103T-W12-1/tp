package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Command that returns a commandResult with the index of the attraction to be editted.
 */
public class DetailedEditCommand extends Command {
    public static final String COMMAND_WORD = "dedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens edit window for the specified attraction.\n"
            + "PARAMETERS: "
            + "INDEX (Must be a positive integer)\n"
            + "Example: " + COMMAND_WORD
            + " 1";

    public static final String SUCCESS_MESSAGE = "Opened editing window.";

    private final Index index;

    /**
     * Initializes DetailedEditCommand object with the index of the attraction to be editted
     * @param index of the attraction
     */
    public DetailedEditCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (index.getZeroBased() >= model.getFilteredAttractionList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
        }

        return new CommandResult(SUCCESS_MESSAGE, false, false, true, Optional.of(index));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .toString();
    }
}
