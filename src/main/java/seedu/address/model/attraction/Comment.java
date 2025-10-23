package seedu.address.model.attraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a comment given to an attraction
 * Guarantees: immutable; is valid as declared in {@link #isValidComment(String)}
 **/
public class Comment {
    public static final String MESSAGE_CONSTRAINTS = "Comments must not be empty";

    /*
     * The first character of the description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = ".*\\S.*";

    public final String comment;

    /**
     * Constructs a {@code Activities}.
     *
     * @param comment A valid comment.
     */
    public Comment(String comment) {
        requireNonNull(comment);
        checkArgument(isValidComment(comment), MESSAGE_CONSTRAINTS);
        this.comment = comment;
    }

    /**
     * Returns true if a given string is a valid Activities.
     */
    public static boolean isValidComment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return comment;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Comment)) {
            return false;
        }

        Comment otherComment = (Comment) other;
        return comment.equals(otherComment.comment);
    }

    @Override
    public int hashCode() {
        return comment.hashCode();
    }
}
