package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ATTRACTIONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITINERARIES;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;


/**
 * Sorts all attractions in the Maplet by field.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all attractions in the Maplet by PREFIX, specifically Name(n/), Priority(p/) or Price(pr/). "
            + "Parameters: "
            + "PREFIX\n"
            + "Examples: " + COMMAND_WORD + " "
            + PREFIX_NAME + ", "
            + COMMAND_WORD + " "
            + PREFIX_PRIORITY + ", "
            + COMMAND_WORD + " "
            + PREFIX_PRICE;

    public static final String MESSAGE_SUCCESS = "Sorted all Attractions and Itineraries";

    private final Comparator<Attraction> attractionComparator;
    private final Comparator<Itinerary> itineraryComparator;

    /**
     * Creates a SortCommand to sort the attractions by the given comparator.
     */
    public SortCommand(Comparator<Attraction> attractionComparator, Comparator<Itinerary> itineraryComparator) {
        requireNonNull(attractionComparator);
        requireNonNull(itineraryComparator);
        this.attractionComparator = attractionComparator;
        this.itineraryComparator = itineraryComparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAttractionList(PREDICATE_SHOW_ALL_ATTRACTIONS);
        model.updateFilteredItineraryList(PREDICATE_SHOW_ALL_ITINERARIES);
        model.updateSortedAttractionList(attractionComparator);
        model.updateSortedItineraryList(itineraryComparator);
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
        return attractionComparator.equals(otherSortCommand.attractionComparator)
                && itineraryComparator.equals(otherSortCommand.itineraryComparator);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("attractionComparator", attractionComparator)
                .add("itineraryComparator", itineraryComparator)
                .toString();
    }
}
