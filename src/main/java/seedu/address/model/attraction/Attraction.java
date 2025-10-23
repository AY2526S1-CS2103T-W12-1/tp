package seedu.address.model.attraction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents an Attraction in the Maplet.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Attraction {

    // Identity fields
    private final Name name;
    private final Priority priority;
    private final Contact contact;

    // Data fields
    private final Address address;
    private final Activities activities;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Comment> comments = new HashSet<>();

    /**
     * Every field must be present and not null except for comments which are optional
     */
    public Attraction(Name name, Priority priority, Contact contact,
                      Address address, Activities activities, Set<Tag> tags, Set<Comment> comments) {
        requireAllNonNull(name, priority, contact, address, tags, comments);
        this.name = name;
        this.priority = priority;
        this.contact = contact;
        this.address = address;
        this.activities = activities;
        this.tags.addAll(tags);
        this.comments.addAll(comments);
    }

    public Name getName() {
        return name;
    }

    public Priority getPriority() {
        return priority;
    }

    public Contact getContact() {
        return contact;
    }

    public Address getAddress() {
        return address;
    }

    public Activities getActivities() {
        return activities;
    }

    //returns a copy to prevent unintentional mutation of comment attribute
    public Set<Comment> getComments() {
        return Collections.unmodifiableSet(comments);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both attractions have the same name.
     * This defines a weaker notion of equality between two attractions.
     */
    public boolean isSameAttraction(Attraction otherAttraction) {
        if (otherAttraction == this) {
            return true;
        }

        return otherAttraction != null
                && otherAttraction.getName().equals(getName());
    }

    /**
     * Returns true if both attractions have the same identity and data fields.
     * This defines a stronger notion of equality between two attractions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Attraction)) {
            return false;
        }

        Attraction otherAttraction = (Attraction) other;
        return name.equals(otherAttraction.name)
                && priority.equals(otherAttraction.priority)
                && contact.equals(otherAttraction.contact)
                && address.equals(otherAttraction.address)
                && activities.equals(otherAttraction.activities)
                && tags.equals(otherAttraction.tags)
                && comments.equals(otherAttraction.comments);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, priority, contact, address, tags, comments);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("priority", priority)
                .add("contact", contact)
                .add("address", address)
                .add("activities", activities)
                .add("tags", tags)
                .add("comments", comments)
                .toString();
    }

}
