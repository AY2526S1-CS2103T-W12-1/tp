package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.UniqueAttractionList;

/**
 * Wraps all data at the Maplet level.
 * Duplicates are not allowed (by .isSameAttraction comparison)
 */
public class Maplet implements ReadOnlyMaplet {

    private final UniqueAttractionList attractions;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        attractions = new UniqueAttractionList();
    }

    public Maplet() {}

    /**
     * Creates a Maplet using the Attractions in the {@code toBeCopied}
     */
    public Maplet(ReadOnlyMaplet toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the attraction list with {@code attractions}.
     * {@code attractions} must not contain duplicate attractions.
     */
    public void setAttractions(List<Attraction> attractions) {
        this.attractions.setAttractions(attractions);
    }

    /**
     * Resets the existing data of this {@code Maplet} with {@code newData}.
     */
    public void resetData(ReadOnlyMaplet newData) {
        requireNonNull(newData);

        setAttractions(newData.getAttractionList());
    }

    //// attraction-level operations

    /**
     * Returns true if a attraction with the same identity as {@code attraction} exists in the Maplet.
     */
    public boolean hasAttraction(Attraction attraction) {
        requireNonNull(attraction);
        return attractions.contains(attraction);
    }

    /**
     * Adds a attraction to the Maplet.
     * The attraction must not already exist in the Maplet.
     */
    public void addAttraction(Attraction p) {
        attractions.add(p);
    }

    /**
     * Replaces the given attraction {@code target} in the list with {@code editedAttraction}.
     * {@code target} must exist in the Maplet.
     * The attraction identity of {@code editedAttraction} must not be the same as another existing attraction in the
     * Maplet.
     */
    public void setAttraction(Attraction target, Attraction editedAttraction) {
        requireNonNull(editedAttraction);

        attractions.setAttraction(target, editedAttraction);
    }

    /**
     * Removes {@code key} from this {@code Maplet}.
     * {@code key} must exist in the Maplet.
     */
    public void removeAttraction(Attraction key) {
        attractions.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("attractions", attractions)
                .toString();
    }

    @Override
    public ObservableList<Attraction> getAttractionList() {
        return attractions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Maplet)) {
            return false;
        }

        Maplet otherMaplet = (Maplet) other;
        return attractions.equals(otherMaplet.attractions);
    }

    @Override
    public int hashCode() {
        return attractions.hashCode();
    }
}
