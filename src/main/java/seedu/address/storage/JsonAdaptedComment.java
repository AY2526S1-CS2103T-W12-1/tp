package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attraction.Comment;

/**
 * Jackson-friendly version of {@link Comment}.
 */
public class JsonAdaptedComment {
    private final String comment;

    /**
     * Constructs a {@code JsonAdaptedComment} with the given {@code comment}.
     */
    @JsonCreator
    public JsonAdaptedComment(String comment) {
        this.comment = comment;
    }

    /**
     * Converts a given {@code Comment} into this class for Jackson use.
     */
    public JsonAdaptedComment(Comment source) {
        comment = source.comment;
    }

    @JsonValue
    public String getComment() {
        return comment;
    }

    /**
     * Converts this Jackson-friendly adapted comment object into the model's {@code Comment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted comment.
     */
    public Comment toModelType() throws IllegalValueException {
        if (!Comment.isValidComment(comment)) {
            throw new IllegalValueException(Comment.MESSAGE_CONSTRAINTS);
        }
        return new Comment(comment);
    }
}
