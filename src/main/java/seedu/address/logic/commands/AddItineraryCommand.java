package seedu.address.logic.commands;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import static java.util.Objects.requireNonNull;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITINERARY_ATTRACTION_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import seedu.address.model.Model;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryName;

/**
 * Adds an itinerary to the Maplet.
 */
public class AddItineraryCommand extends Command {

    public static final String COMMAND_WORD = "additinerary";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new itinerary to the Maplet. "
            + "Parameters: "
            + PREFIX_NAME + "ITINERARY_NAME "
            + "[" + PREFIX_ITINERARY_ATTRACTION_INDEX + "ATTRACTION_INDEX]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Singapore Runaround "
            + PREFIX_ITINERARY_ATTRACTION_INDEX + "1 "
            + PREFIX_ITINERARY_ATTRACTION_INDEX + "2";

    public static final String MESSAGE_SUCCESS = "New itinerary added: %1$s";
    public static final String MESSAGE_DUPLICATE_ITINERARY
            = "An itinerary with the same name already exists in the Maplet.";
    public static final String MESSAGE_DUPLICATE_ATTRACTION_REFERENCE
            = "Each attraction should be referenced at most once when creating an itinerary.";

    private final ItineraryName itineraryName;
    private final List<Index> attractionIndexes;

    /**
     * Creates an AddItineraryCommand to add the specified itinerary details.
     */
    public AddItineraryCommand(ItineraryName itineraryName, List<Index> attractionIndexes) {
        requireNonNull(itineraryName);
        requireNonNull(attractionIndexes);
        this.itineraryName = itineraryName;
        this.attractionIndexes = attractionIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Attraction> lastShownList = model.getFilteredAttractionList();
        List<Attraction> attractions = new ArrayList<>();
        Set<Attraction> seenAttractions = new HashSet<>();

        for (Index index : attractionIndexes) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
            }
            Attraction attraction = lastShownList.get(index.getZeroBased());
            if (!seenAttractions.add(attraction)) {
                throw new CommandException(MESSAGE_DUPLICATE_ATTRACTION_REFERENCE);
            }
            attractions.add(attraction);
        }

        Itinerary itinerary = new Itinerary(itineraryName, LocalDateTime.now(), attractions);

        if (model.hasItinerary(itinerary)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITINERARY);
        }

        model.addItinerary(itinerary);
        model.updateFilteredItineraryList(Model.PREDICATE_SHOW_ALL_ITINERARIES);
        return new CommandResult(String.format(MESSAGE_SUCCESS, itinerary.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddItineraryCommand)) {
            return false;
        }

        AddItineraryCommand otherCommand = (AddItineraryCommand) other;
        return itineraryName.equals(otherCommand.itineraryName)
                && attractionIndexes.equals(otherCommand.attractionIndexes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itineraryName, attractionIndexes);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("itineraryName", itineraryName)
                .add("attractionIndexes", attractionIndexes)
                .toString();
    }
}
