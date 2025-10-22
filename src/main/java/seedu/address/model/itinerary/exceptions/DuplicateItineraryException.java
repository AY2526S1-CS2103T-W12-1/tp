package seedu.address.model.itinerary.exceptions;

/**
 * Signals that the operation will result in duplicate Itinerary objects.
 */
public class DuplicateItineraryException extends RuntimeException {
    public DuplicateItineraryException() {
        super("Operation would result in duplicate itineraries");
    }
}

