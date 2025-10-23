package seedu.address.model.location.exceptions;

/**
 * Signals that the operation is unable to find the specified location.
 */
public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException() {
        super("Location not found in list");
    }
}
