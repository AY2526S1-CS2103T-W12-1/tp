package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.itinerary.Itinerary;

/**
 * Deletes an itinerary identified using its displayed index from the Maplet.
 */
public class DeleteItineraryCommand extends Command {

    public static final String COMMAND_WORD = "deleteitinerary";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the itinerary identified by the index number used in the displayed itinerary list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ITINERARY_SUCCESS = "Deleted Itinerary: %1$s";

    private final Index targetIndex;

    public DeleteItineraryCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Itinerary> lastShownList = model.getFilteredItineraryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
        }

        Itinerary itineraryToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteItinerary(itineraryToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ITINERARY_SUCCESS, itineraryToDelete.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteItineraryCommand)) {
            return false;
        }

        DeleteItineraryCommand otherCommand = (DeleteItineraryCommand) other;
        return targetIndex.equals(otherCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
