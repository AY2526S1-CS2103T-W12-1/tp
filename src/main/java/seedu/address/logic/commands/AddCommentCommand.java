package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.EditCommand.MESSAGE_DUPLICATE_ATTRACTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ATTRACTIONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attraction.Activities;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Comment;
import seedu.address.model.attraction.Contact;
import seedu.address.model.attraction.Name;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Price;
import seedu.address.model.attraction.Priority;
import seedu.address.model.tag.Tag;

/**
 * Adds a comment to an attraction
 */
public class AddCommentCommand extends Command {
    public static final String COMMAND_WORD = "comment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a comment to an attraction."
            + "Parameters: INDEX (Must be a positive integer) "
            + PREFIX_COMMENT + "COMMENT\n"
            + "Example: " + COMMAND_WORD
            + " INDEX " + PREFIX_COMMENT
            + "This place is hard to reach.";

    public static final String MESSAGE_SUCCESS = "New comment added: %1$s";

    private final Index index;
    private final Comment comment;

    /**
     * Constructor that initializes the AddCommentCommand obhect with the comment to be added and the index
     * of the attraction
     * @param index of the attraction to be modified
     * @param comment to be added
     */
    public AddCommentCommand(Index index, Comment comment) {
        requireNonNull(comment);
        requireNonNull(index);

        this.index = index;
        this.comment = comment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Attraction> lastShownList = model.getFilteredAttractionList();

        //get attraction to add comment to
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
        }

        Attraction attractionToComment = lastShownList.get(index.getZeroBased());
        Attraction commentedAttraction = attractionWithComment(attractionToComment, comment);

        if (!attractionToComment.isSameAttraction(commentedAttraction) && model.hasAttraction(commentedAttraction)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATTRACTION);
        }

        model.setAttraction(attractionToComment, commentedAttraction);
        model.updateFilteredAttractionList(PREDICATE_SHOW_ALL_ATTRACTIONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(commentedAttraction)));
    }

    /**
     * Creates a copy of the attraction with the added comment
     * @param attractionToComment attraction to be replaced
     * @param comment comment to be added to the attraction to be replaced
     * @return attraction with the newly added comment
     */
    private Attraction attractionWithComment(Attraction attractionToComment, Comment comment) {
        Name name = attractionToComment.getName();
        Priority priority = attractionToComment.getPriority();
        Contact contact = attractionToComment.getContact();
        Address address = attractionToComment.getAddress();
        Activities activities = attractionToComment.getActivities();
        OpeningHours openingHours = attractionToComment.getOpeningHours();
        Price price = attractionToComment.getPrice();
        Set<Tag> tags = attractionToComment.getTags();
        Set<Comment> comments = new HashSet<>(attractionToComment.getComments());

        comments.add(comment);

        Attraction attractionWithComment = new Attraction(name, priority,
                contact, address, activities, openingHours, price, tags, comments);

        return attractionWithComment;
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommentCommand)) {
            return false;
        }

        AddCommentCommand otherAddCommentCommand = (AddCommentCommand) other;
        return comment.equals(otherAddCommentCommand.comment);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("comment", comment)
                .toString();
    }
}
