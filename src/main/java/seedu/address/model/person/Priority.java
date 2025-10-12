package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an attraction's priority number in the Maplet.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {


    public static final String MESSAGE_CONSTRAINTS =
            "Priority level should be from a number from 1 - 10";
    public static final String VALIDATION_REGEX = "\\b([1-9]|10)\\b";
    public final String value;

    /**
     * Constructs a {@code Priority}.
     *
     * @param priority A valid priority value.
     */
    public Priority(String priority) {
        requireNonNull(priority);
        checkArgument(isValidPriority(priority), MESSAGE_CONSTRAINTS);
        value = priority;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPriority(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Priority)) {
            return false;
        }

        Priority otherPriority = (Priority) other;
        return value.equals(otherPriority.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
