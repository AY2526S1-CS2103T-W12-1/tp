package seedu.address.logic.commands.location;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.location.LocationName;

/**
 * Deletes an existing location from the maplet.
 */
public class DeleteLocationCommand extends Command {

    public static final String COMMAND_WORD = "delete-location";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the specified location. "
            + "Parameters: ln/LOCATION_NAME\n"
            + "Example: " + COMMAND_WORD + " ln/Singapore";

    public static final String MESSAGE_DELETE_LOCATION_SUCCESS = "Deleted location: %1$s";
    public static final String MESSAGE_LOCATION_NOT_FOUND = "The specified location does not exist in the maplet.";

    private final LocationName targetLocationName;

    public DeleteLocationCommand(LocationName targetLocationName) {
        this.targetLocationName = requireNonNull(targetLocationName);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasLocationName(targetLocationName)) {
            throw new CommandException(MESSAGE_LOCATION_NOT_FOUND);
        }

        model.deleteLocation(targetLocationName);
        return new CommandResult(String.format(MESSAGE_DELETE_LOCATION_SUCCESS, targetLocationName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteLocationCommand)) {
            return false;
        }

        DeleteLocationCommand otherCommand = (DeleteLocationCommand) other;
        return targetLocationName.equals(otherCommand.targetLocationName);
    }

    @Override
    public String toString() {
        return DeleteLocationCommand.class.getCanonicalName()
                + "{targetLocationName=" + targetLocationName + "}";
    }
}
