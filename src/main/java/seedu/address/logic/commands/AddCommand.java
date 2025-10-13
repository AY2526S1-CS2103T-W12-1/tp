package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
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
 * Adds a attraction to the Maplet.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a attraction to the Maplet. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PRIORITY + "PRIORITY "
            + PREFIX_CONTACT + "CONTACT "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_ACTIVITIES + "ACTIVITIES "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PRIORITY + "9 "
            + PREFIX_CONTACT + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_ACTIVITIES + "bowling "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New address added: %1$s";
    public static final String MESSAGE_DUPLICATE_ATTRACTION = "This address already exists in the Maplet";

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
