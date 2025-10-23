package seedu.address.model.location;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.attraction.Name;

/**
 * Represents a grouping of attractions within the same location.
 * Guarantees: fields are present and not null, field values are validated, immutable.
 */
public class Location {

    public static final String MESSAGE_EMPTY_ATTRACTIONS =
            "A location must contain at least one attraction.";

    private final LocationName name;
    private final Set<Name> attractions = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Location(LocationName name, Set<Name> attractions) {
        requireAllNonNull(name, attractions);
        if (attractions.isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_EMPTY_ATTRACTIONS);
        }
        this.name = name;
        this.attractions.addAll(attractions);
    }

    public LocationName getName() {
        return name;
    }

    /**
     * Returns an immutable set of attraction names, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Name> getAttractionNames() {
        return Collections.unmodifiableSet(attractions);
    }

    /**
     * Returns true if both locations have the same name.
     * This defines a weaker notion of equality between two locations.
     */
    public boolean isSameLocation(Location otherLocation) {
        if (otherLocation == this) {
            return true;
        }

        return otherLocation != null
                && otherLocation.getName().equals(getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Location)) {
            return false;
        }

        Location otherLocation = (Location) other;
        return name.equals(otherLocation.name)
                && attractions.equals(otherLocation.attractions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attractions);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("attractions", attractions)
                .toString();
    }
}
