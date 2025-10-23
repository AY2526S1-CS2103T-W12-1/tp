package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attraction.Attraction;

/**
 * Adds an attraction to the Maplet.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an attraction to the Maplet. "
            + "Parameters: "
            + PREFIX_NAME + "ATTRACTION_NAME "
            + PREFIX_PRIORITY + "PRIORITY_LEVEL "
            + PREFIX_CONTACT + "CONTACT "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_ACTIVITIES + "DESCRIPTION/ACTIVITY "
            + "[" + PREFIX_TAG + "TAG]..."
            + "[" + PREFIX_COMMENT + "COMMENT]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Gardens by the Bay "
            + PREFIX_PRIORITY + "7 "
            + PREFIX_CONTACT + "info@gardens.com "
            + PREFIX_ADDRESS + "18 Marina Gardens Dr, Singapore 018953 "
            + PREFIX_ACTIVITIES + "Explore the Flower Dome "
            + PREFIX_TAG + "park "
            + PREFIX_TAG + "nature "
            + PREFIX_COMMENT + "Good for relaxing";

    public static final String MESSAGE_SUCCESS = "New attraction added: %1$s";
    public static final String MESSAGE_DUPLICATE_ATTRACTION = "This attraction already exists in the Maplet";

    private final Attraction toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Attraction}
     */
    public AddCommand(Attraction attraction) {
        requireNonNull(attraction);
        toAdd = attraction;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAttraction(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATTRACTION);
        }

        model.addAttraction(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
