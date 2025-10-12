package seedu.address.model.attraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.attraction.exceptions.AttractionNotFoundException;
import seedu.address.model.attraction.exceptions.DuplicateAttractionException;

/**
 * A list of attractions that enforces uniqueness between its elements and does not allow nulls.
 * An attraction is considered unique by comparing using {@code Attraction#isSameAttraction(Attraction)}.
 * As such, adding and updating of attractions uses Attraction#isSameAttraction(Attraction) for equality so as to ensure
 * that the attraction being added or updated is unique in terms of identity in the UniqueAttractionList. However, the
 * removal of an attraction uses Attraction#equals(Object) so as to ensure that the attraction with exactly the same
 * fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Attraction#isSameAttraction(Attraction)
 */
public class UniqueAttractionList implements Iterable<Attraction> {

    private final ObservableList<Attraction> internalList = FXCollections.observableArrayList();
    private final ObservableList<Attraction> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent attraction as the given argument.
     */
    public boolean contains(Attraction toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAttraction);
    }

    /**
     * Adds an attraction to the list.
     * The attraction must not already exist in the list.
     */
    public void add(Attraction toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAttractionException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the attraction {@code target} in the list with {@code editedAttraction}.
     * {@code target} must exist in the list.
     * The attraction identity of {@code editedAttraction} must not be the same as another existing attraction in the
     * list.
     */
    public void setAttraction(Attraction target, Attraction editedAttraction) {
        requireAllNonNull(target, editedAttraction);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AttractionNotFoundException();
        }

        if (!target.isSameAttraction(editedAttraction) && contains(editedAttraction)) {
            throw new DuplicateAttractionException();
        }

        internalList.set(index, editedAttraction);
    }

    /**
     * Removes the equivalent attraction from the list.
     * The attraction must exist in the list.
     */
    public void remove(Attraction toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AttractionNotFoundException();
        }
    }

    public void setAttractions(UniqueAttractionList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code attractions}.
     * {@code attractions} must not contain duplicate attractions.
     */
    public void setAttractions(List<Attraction> attractions) {
        requireAllNonNull(attractions);
        if (!attractionsAreUnique(attractions)) {
            throw new DuplicateAttractionException();
        }

        internalList.setAll(attractions);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Attraction> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Attraction> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueAttractionList)) {
            return false;
        }

        UniqueAttractionList otherUniqueAttractionList = (UniqueAttractionList) other;
        return internalList.equals(otherUniqueAttractionList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code attractions} contains only unique attractions.
     */
    private boolean attractionsAreUnique(List<Attraction> attractions) {
        for (int i = 0; i < attractions.size() - 1; i++) {
            for (int j = i + 1; j < attractions.size(); j++) {
                if (attractions.get(i).isSameAttraction(attractions.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
