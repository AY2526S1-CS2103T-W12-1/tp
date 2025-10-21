package seedu.address.model.itinerary;

import static java.util.Objects.requireNonNull;

/**
 * Represents an itinerary's name in the Maplet. Guarantees: immutable; is valid
 */
public class ItineraryName {

    public static final String MESSAGE_CONSTRAINTS = "Itinerary names should not be blank";

    public final String fullName;

    /**
     * Constructs an {@code ItineraryName}.
     */
    public ItineraryName(String name) {
        requireNonNull(name);
        fullName = name;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ItineraryName && fullName.equals(((ItineraryName) other).fullName));
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
