package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.attraction.Attraction;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the attractions list.
     * This list will not contain any duplicate attractions.
     */
    ObservableList<Attraction> getAttractionList();

}
