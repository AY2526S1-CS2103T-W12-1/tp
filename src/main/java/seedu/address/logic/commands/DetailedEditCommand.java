package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;

import java.util.Optional;

public class DetailedEditCommand extends Command {
    public static final String COMMAND_WORD = "dedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens edit window for the specified attraction.\n"
            + "PARAMETERS: "
            + "INDEX (Must be a positive integer)\n"
            + "Example: " + COMMAND_WORD
            + " 1";

    public static final String SUCCESS_MESSAGE = "Opened editing window.";

    private final Index index;


    public DetailedEditCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SUCCESS_MESSAGE, false, false, true, Optional.of(index));
    }
}
