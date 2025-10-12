package seedu.address.model.attraction.exceptions;

/**
 * Signals that the operation will result in duplicate Attractions (Attractions are considered duplicates if they have
 * the same identity).
 */
public class DuplicateAttractionException extends RuntimeException {
    public DuplicateAttractionException() {
        super("Operation would result in duplicate attractions");
    }
}
