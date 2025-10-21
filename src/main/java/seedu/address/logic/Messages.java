package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.attraction.Attraction;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX = "The attraction index provided is invalid";
    public static final String MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX = "The itinerary index provided is invalid";
    public static final String MESSAGE_ATTRACTIONS_LISTED_OVERVIEW = "%1$d attractions listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code attraction} for display to the user.
     */
    public static String format(Attraction attraction) {
        final StringBuilder builder = new StringBuilder();
        builder.append(attraction.getName())
                .append("; Priority: ")
                .append(attraction.getPriority())
                .append("; Contact: ")
                .append(attraction.getContact())
                .append("; Address: ")
                .append(attraction.getAddress())
                .append("; Activities: ")
                .append(attraction.getActivities())
                .append("; Tags: ");
        attraction.getTags().forEach(builder::append);
        return builder.toString();
    }

}
