package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPENING_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditAttractionDescriptor;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Comment;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Attraction.
 */
public class AttractionUtil {

    /**
     * Returns an add command string for adding the {@code attraction}.
     */
    public static String getAddCommand(Attraction attraction) {
        return AddCommand.COMMAND_WORD + " " + getAttractionDetails(attraction);
    }

    /**
     * Returns the part of command string for the given {@code attraction}'s details.
     */
    public static String getAttractionDetails(Attraction attraction) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + attraction.getName().fullName + " ");
        sb.append(PREFIX_PRIORITY + attraction.getPriority().value + " ");
        sb.append(PREFIX_CONTACT + attraction.getContact().value + " ");
        sb.append(PREFIX_ADDRESS + attraction.getAddress().value + " ");
        sb.append(PREFIX_ACTIVITIES + attraction.getActivities().activities + " ");
        sb.append(PREFIX_OPENING_HOURS + attraction.getOpeningHours().toString() + " ");
        sb.append(PREFIX_PRICE + attraction.getPrice().value + " ");
        attraction.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        attraction.getComments().stream().forEach(
                s -> sb.append(PREFIX_COMMENT + s.comment + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditAttractionDescriptor}'s details.
     */
    public static String getEditAttractionDescriptorDetails(EditAttractionDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPriority().ifPresent(priority -> sb.append(PREFIX_PRIORITY).append(priority.value).append(" "));
        descriptor.getContact().ifPresent(contact -> sb.append(PREFIX_CONTACT).append(contact.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getActivities().ifPresent(
                activities -> sb.append(PREFIX_ACTIVITIES).append(activities.activities).append(" "));
        descriptor.getOpeningHours().ifPresent(
                openingHours -> sb.append(PREFIX_OPENING_HOURS).append(openingHours.toString()).append(" "));
        descriptor.getPrice().ifPresent(price -> sb.append(PREFIX_PRICE).append(price.value).append(" "));


        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG + " ");
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getComments().isPresent()) {
            Set<Comment> comments = descriptor.getComments().get();
            if (comments.isEmpty()) {
                sb.append(PREFIX_COMMENT);
            } else {
                comments.forEach(s -> sb.append(PREFIX_COMMENT).append(s.comment).append(" "));
            }
        }
        return sb.toString();
    }
}
