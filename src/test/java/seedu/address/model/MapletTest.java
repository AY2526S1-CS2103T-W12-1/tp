package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.ALICE;
import static seedu.address.testutil.TypicalAttractions.getTypicalMaplet;
import static seedu.address.testutil.TypicalItineraries.EUROPE_TOUR;
import static seedu.address.testutil.TypicalItineraries.JAPAN_TRIP;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.exceptions.DuplicateAttractionException;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.exceptions.DuplicateItineraryException;
import seedu.address.testutil.AttractionBuilder;
import seedu.address.testutil.ItineraryBuilder;

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
        String expected = Maplet.class.getCanonicalName()
                + "{attractions=" + maplet.getAttractionList()
                + ", itineraries=" + maplet.getItineraryList() + "}";
        assertEquals(expected, maplet.toString());
    }

    @Test
    public void hasItinerary_nullItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> maplet.hasItinerary(null));
    }

    @Test
    public void hasItinerary_itineraryNotInMaplet_returnsFalse() {
        assertFalse(maplet.hasItinerary(JAPAN_TRIP));
    }

    @Test
    public void hasItinerary_itineraryInMaplet_returnsTrue() {
        maplet.addItinerary(JAPAN_TRIP);
        assertTrue(maplet.hasItinerary(JAPAN_TRIP));
    }

    @Test
    public void hasItinerary_itineraryWithSameIdentityFieldsInMaplet_returnsTrue() {
        maplet.addItinerary(JAPAN_TRIP);
        Itinerary editedJapanTrip = new ItineraryBuilder(JAPAN_TRIP)
                .withAttractions()
                .build();
        assertTrue(maplet.hasItinerary(editedJapanTrip));
    }

    @Test
    public void getItineraryList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> maplet.getItineraryList().remove(0));
    }

    @Test
    public void resetData_withDuplicateItineraries_throwsDuplicateItineraryException() {
        Itinerary editedJapanTrip = new ItineraryBuilder(JAPAN_TRIP)
                .withAttractions()
                .build();
        List<Itinerary> newItineraries = Arrays.asList(JAPAN_TRIP, editedJapanTrip);
        MapletStubWithItineraries newData = new MapletStubWithItineraries(Collections.emptyList(), newItineraries);

        assertThrows(DuplicateItineraryException.class, () -> maplet.resetData(newData));
    }

    @Test
    public void hashCode_sameData_sameHash() {
        Maplet maplet1 = new Maplet();
        maplet1.addAttraction(ALICE);
        maplet1.addItinerary(JAPAN_TRIP);

        Maplet maplet2 = new Maplet();
        maplet2.addAttraction(ALICE);
        maplet2.addItinerary(JAPAN_TRIP);

        assertEquals(maplet1.hashCode(), maplet2.hashCode());
    }

    @Test
    public void equals_differentItineraries_returnsFalse() {
        Maplet maplet1 = new Maplet();
        maplet1.addItinerary(JAPAN_TRIP);

        Maplet maplet2 = new Maplet();
        maplet2.addItinerary(EUROPE_TOUR);

        assertFalse(maplet1.equals(maplet2));
    }

    @Test
    public void equals_sameData_returnsTrue() {
        Maplet maplet1 = new Maplet();
        maplet1.addAttraction(ALICE);
        maplet1.addItinerary(JAPAN_TRIP);

        Maplet maplet2 = new Maplet();
        maplet2.addAttraction(ALICE);
        maplet2.addItinerary(JAPAN_TRIP);

        assertTrue(maplet1.equals(maplet2));
    }

    /**
     * A stub ReadOnlyMaplet whose attractions list can violate interface
     * constraints.
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

        @Override
        public ObservableList<Itinerary> getItineraryList() {
            return FXCollections.observableArrayList();
        }
    }

    /**
     * A stub ReadOnlyMaplet whose itineraries list can violate interface constraints.
     */
    private static class MapletStubWithItineraries implements ReadOnlyMaplet {

        private final ObservableList<Attraction> attractions = FXCollections.observableArrayList();
        private final ObservableList<Itinerary> itineraries = FXCollections.observableArrayList();

        MapletStubWithItineraries(Collection<Attraction> attractions, Collection<Itinerary> itineraries) {
            this.attractions.setAll(attractions);
            this.itineraries.setAll(itineraries);
        }

        @Override
        public ObservableList<Attraction> getAttractionList() {
            return attractions;
        }

        @Override
        public ObservableList<Itinerary> getItineraryList() {
            return itineraries;
        }
    }

}
