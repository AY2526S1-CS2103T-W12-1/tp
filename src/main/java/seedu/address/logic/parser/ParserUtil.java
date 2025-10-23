package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attraction.Activities;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Comment;
import seedu.address.model.attraction.Contact;
import seedu.address.model.attraction.Name;
import seedu.address.model.attraction.Priority;
import seedu.address.model.itinerary.ItineraryName;
import seedu.address.model.location.LocationName;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String priority} into a {@code Priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code priority} is invalid.
     */
    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim();
        if (!Priority.isValidPriority(trimmedPriority)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }
        return new Priority(trimmedPriority);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String contact} into an {@code Contact}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code contact} is invalid.
     */
    public static Contact parseContact(String contact) throws ParseException {
        requireNonNull(contact);
        String trimmedContact = contact.trim();
        if (!Contact.isValidContact(trimmedContact)) {
            throw new ParseException(Contact.MESSAGE_CONSTRAINTS);
        }
        return new Contact(trimmedContact);
    }
    /**
     * Parses a {@code String activities} into an {@code Activities}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code activities} is invalid.
     */
    public static Activities parseActivities(String activities) throws ParseException {
        requireNonNull(activities);
        String trimmedActivities = activities.trim();
        if (!Activities.isValidActivities(trimmedActivities)) {
            throw new ParseException(Activities.MESSAGE_CONSTRAINTS);
        }
        return new Activities(trimmedActivities);
    }

    /**
     * Parses a {@code String itineraryName} into an {@code ItineraryName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code itineraryName} is invalid.
     */
    public static ItineraryName parseItineraryName(String itineraryName) throws ParseException {
        requireNonNull(itineraryName);
        String trimmedName = itineraryName.trim();
        if (!ItineraryName.isValidName(trimmedName)) {
            throw new ParseException(ItineraryName.MESSAGE_CONSTRAINTS);
        }
        return new ItineraryName(trimmedName);
    }
    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code Collection<String> comments} into a {@code Set<Comment>}.
     */
    public static Set<Comment> parseComments(Collection<String> comments) throws ParseException {
        requireNonNull(comments);
        final Set<Comment> commentSet = new HashSet<>();
        for (String commentName : comments) {
            commentSet.add(parseComment(commentName));
        }
        return commentSet;
    }

    /**
     * Parses a {@code String comment} into a {@code Comment}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code comment} is invalid.
     */
    public static Comment parseComment(String comment) throws ParseException {
        requireNonNull(comment);
        String trimmedComment = comment.trim();
        if (!Comment.isValidComment(trimmedComment)) {
            throw new ParseException(Comment.MESSAGE_CONSTRAINTS);
        }
        return new Comment(trimmedComment);
    }

    /**
     * Parses a {@code String locationName} into a {@code LocationName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code locationName} is invalid.
     */
    public static LocationName parseLocationName(String locationName) throws ParseException {
        requireNonNull(locationName);
        String trimmedLocationName = locationName.trim();
        if (!LocationName.isValidLocationName(trimmedLocationName)) {
            throw new ParseException(LocationName.MESSAGE_CONSTRAINTS);
        }
        return new LocationName(trimmedLocationName);
    }
}
