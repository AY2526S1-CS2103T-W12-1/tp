package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.UniqueAttractionList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameAttraction comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

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

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Attractions in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
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
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setAttractions(newData.getAttractionList());
    }

    //// attraction-level operations

    /**
     * Returns true if a attraction with the same identity as {@code attraction} exists in the address book.
     */
    public boolean hasAttraction(Attraction attraction) {
        requireNonNull(attraction);
        return attractions.contains(attraction);
    }

    /**
     * Adds a attraction to the address book.
     * The attraction must not already exist in the address book.
     */
    public void addAttraction(Attraction p) {
        attractions.add(p);
    }

    /**
     * Replaces the given attraction {@code target} in the list with {@code editedAttraction}.
     * {@code target} must exist in the address book.
     * The attraction identity of {@code editedAttraction} must not be the same as another existing attraction in the
     * address book.
     */
    public void setAttraction(Attraction target, Attraction editedAttraction) {
        requireNonNull(editedAttraction);

        attractions.setAttraction(target, editedAttraction);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
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
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return attractions.equals(otherAddressBook.attractions);
    }

    @Override
    public int hashCode() {
        return attractions.hashCode();
    }
}
