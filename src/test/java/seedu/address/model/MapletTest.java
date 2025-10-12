package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.ALICE;
import static seedu.address.testutil.TypicalAttractions.getTypicalMaplet;

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

public class MapletTest {

    private final Maplet maplet = new Maplet();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), maplet.getAttractionList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> maplet.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMaplet_replacesData() {
        Maplet newData = getTypicalMaplet();
        maplet.resetData(newData);
        assertEquals(newData, maplet);
    }

    @Test
    public void resetData_withDuplicateAttractions_throwsDuplicateAttractionException() {
        // Two attractions with the same identity fields
        Attraction editedAlice = new AttractionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Attraction> newAttractions = Arrays.asList(ALICE, editedAlice);
        MapletStub newData = new MapletStub(newAttractions);

        assertThrows(DuplicateAttractionException.class, () -> maplet.resetData(newData));
    }

    @Test
    public void hasAttraction_nullAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> maplet.hasAttraction(null));
    }

    @Test
    public void hasAttraction_attractionNotInMaplet_returnsFalse() {
        assertFalse(maplet.hasAttraction(ALICE));
    }

    @Test
    public void hasAttraction_attractionInMaplet_returnsTrue() {
        maplet.addAttraction(ALICE);
        assertTrue(maplet.hasAttraction(ALICE));
    }

    @Test
    public void hasAttraction_attractionWithSameIdentityFieldsInMaplet_returnsTrue() {
        maplet.addAttraction(ALICE);
        Attraction editedAlice = new AttractionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(maplet.hasAttraction(editedAlice));
    }

    @Test
    public void getAttractionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> maplet.getAttractionList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = Maplet.class.getCanonicalName() + "{attractions=" + maplet.getAttractionList() + "}";
        assertEquals(expected, maplet.toString());
    }

    /**
     * A stub ReadOnlyMaplet whose attractions list can violate interface constraints.
     */
    private static class MapletStub implements ReadOnlyMaplet {
        private final ObservableList<Attraction> attractions = FXCollections.observableArrayList();

        MapletStub(Collection<Attraction> attractions) {
            this.attractions.setAll(attractions);
        }

        @Override
        public ObservableList<Attraction> getAttractionList() {
            return attractions;
        }
    }

}
