package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Maplet;
import seedu.address.model.Model;

/**
 * Clears the Maplet.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Maplet has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setMaplet(new Maplet());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
