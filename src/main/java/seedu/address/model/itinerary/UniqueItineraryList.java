package seedu.address.model.itinerary;

import java.util.Iterator;
import java.util.List;
import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.itinerary.exceptions.DuplicateItineraryException;
import seedu.address.model.itinerary.exceptions.ItineraryNotFoundException;

/**
 * A list of itineraries that enforces uniqueness between its elements and does
 * not allow nulls. An itinerary is considered unique by comparing using
 * {@link Itinerary#isSameItinerary(Itinerary)}. Duplicated logic from
 * UniqueAttractionList.
 */
public class UniqueItineraryList implements Iterable<Itinerary> {

    private final ObservableList<Itinerary> internalList = FXCollections.observableArrayList();
    private final ObservableList<Itinerary> internalUnmodifiableList
            = FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent itinerary as the given
     * argument.
     */
    public boolean contains(Itinerary toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameItinerary);
    }

    /**
     * Adds an itinerary to the list. The itinerary must not already exist in
     * the list.
     */
    public void add(Itinerary toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateItineraryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the itinerary {@code target} with {@code editedItinerary}.
     */
    public void setItinerary(Itinerary target, Itinerary editedItinerary) {
        requireNonNull(target);
        requireNonNull(editedItinerary);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ItineraryNotFoundException();
        }
        if (!target.isSameItinerary(editedItinerary) && contains(editedItinerary)) {
            throw new DuplicateItineraryException();
        }
        internalList.set(index, editedItinerary);
    }

    /**
     * Removes the equivalent itinerary from the list.
     */
    public void remove(Itinerary toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ItineraryNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     */
    public void setItineraries(UniqueItineraryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code itineraries}.
     */
    public void setItineraries(List<Itinerary> itineraries) {
        requireNonNull(itineraries);
        if (!itinerariesAreUnique(itineraries)) {
            throw new DuplicateItineraryException();
        }
        internalList.setAll(itineraries);
    }

    public ObservableList<Itinerary> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Itinerary> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueItineraryList)) {
            return false;
        }

        UniqueItineraryList otherUniqueItineraryList = (UniqueItineraryList) other;
        return internalList.equals(otherUniqueItineraryList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    private boolean itinerariesAreUnique(List<Itinerary> itineraries) {
        for (int i = 0; i < itineraries.size() - 1; i++) {
            for (int j = i + 1; j < itineraries.size(); j++) {
                if (itineraries.get(i).isSameItinerary(itineraries.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
