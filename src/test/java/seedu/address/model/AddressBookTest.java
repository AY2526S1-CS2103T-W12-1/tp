package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.ALICE;
import static seedu.address.testutil.TypicalAttractions.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.exceptions.DuplicateAttractionException;
import seedu.address.testutil.AttractionBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getAttractionList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateAttractions_throwsDuplicateAttractionException() {
        // Two attractions with the same identity fields
        Attraction editedAlice = new AttractionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Attraction> newAttractions = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newAttractions);

        assertThrows(DuplicateAttractionException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasAttraction_nullAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasAttraction(null));
    }

    @Test
    public void hasAttraction_attractionNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasAttraction(ALICE));
    }

    @Test
    public void hasAttraction_attractionInAddressBook_returnsTrue() {
        addressBook.addAttraction(ALICE);
        assertTrue(addressBook.hasAttraction(ALICE));
    }

    @Test
    public void hasAttraction_attractionWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addAttraction(ALICE);
        Attraction editedAlice = new AttractionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasAttraction(editedAlice));
    }

    @Test
    public void getAttractionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getAttractionList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{attractions=" + addressBook.getAttractionList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose attractions list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Attraction> attractions = FXCollections.observableArrayList();

        AddressBookStub(Collection<Attraction> attractions) {
            this.attractions.setAll(attractions);
        }

        @Override
        public ObservableList<Attraction> getAttractionList() {
            return attractions;
        }
    }

}
