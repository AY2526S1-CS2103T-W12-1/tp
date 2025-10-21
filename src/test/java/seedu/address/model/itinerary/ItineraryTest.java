package seedu.address.model.itinerary;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItineraryBuilder;
import static seedu.address.testutil.TypicalAttractions.ALICE;
import static seedu.address.testutil.TypicalAttractions.BENSON;
import static seedu.address.testutil.TypicalAttractions.CARL;
import static seedu.address.testutil.TypicalItineraries.EUROPE_TOUR;
import static seedu.address.testutil.TypicalItineraries.JAPAN_TRIP;

public class ItineraryTest {

    @Test
    public void isSameItinerary() {
        assertTrue(JAPAN_TRIP.isSameItinerary(JAPAN_TRIP));

        assertFalse(JAPAN_TRIP.isSameItinerary(null));

        Itinerary editedJapanTrip = new ItineraryBuilder(JAPAN_TRIP)
                .withCreatedAt(LocalDateTime.of(2025, 10, 21, 10, 0))
                .withAttractions(CARL)
                .build();
        assertTrue(JAPAN_TRIP.isSameItinerary(editedJapanTrip));

        editedJapanTrip = new ItineraryBuilder(JAPAN_TRIP).withName("Different Trip").build();
        assertFalse(JAPAN_TRIP.isSameItinerary(editedJapanTrip));
    }

    @Test
    public void equals() {
        Itinerary japanTripCopy = new ItineraryBuilder(JAPAN_TRIP).build();
        assertTrue(JAPAN_TRIP.equals(japanTripCopy));

        assertTrue(JAPAN_TRIP.equals(JAPAN_TRIP));

        assertFalse(JAPAN_TRIP.equals(null));

        assertFalse(JAPAN_TRIP.equals(5));

        assertFalse(JAPAN_TRIP.equals(EUROPE_TOUR));

        Itinerary editedJapanTrip = new ItineraryBuilder(JAPAN_TRIP).withName("Different Trip").build();
        assertFalse(JAPAN_TRIP.equals(editedJapanTrip));

        editedJapanTrip = new ItineraryBuilder(JAPAN_TRIP)
                .withCreatedAt(LocalDateTime.of(2025, 1, 1, 10, 0))
                .build();
        assertFalse(JAPAN_TRIP.equals(editedJapanTrip));

        editedJapanTrip = new ItineraryBuilder(JAPAN_TRIP).withAttractions(CARL).build();
        assertFalse(JAPAN_TRIP.equals(editedJapanTrip));
    }

    @Test
    public void hashCode_sameFields_sameHash() {
        Itinerary japanTripCopy = new ItineraryBuilder(JAPAN_TRIP).build();
        assertEquals(JAPAN_TRIP.hashCode(), japanTripCopy.hashCode());
    }

    @Test
    public void toStringMethod() {
        String expected = Itinerary.class.getCanonicalName()
                + "{name=" + JAPAN_TRIP.getName()
                + ", createdAt=" + JAPAN_TRIP.getCreatedAt()
                + ", attractions=" + JAPAN_TRIP.getAttractions()
                + "}";
        assertEquals(expected, JAPAN_TRIP.toString());
    }

    @Test
    public void hasAttraction_attractionInItinerary_returnsTrue() {
        assertTrue(JAPAN_TRIP.hasAttraction(ALICE));
    }

    @Test
    public void hasAttraction_attractionNotInItinerary_returnsFalse() {
        assertFalse(JAPAN_TRIP.hasAttraction(CARL));
    }

    @Test
    public void addAttraction_success() {
        Itinerary itinerary = new ItineraryBuilder().withAttractions().build();
        itinerary.addAttraction(ALICE);
        assertTrue(itinerary.hasAttraction(ALICE));
    }

    @Test
    public void removeAttraction_success() {
        Itinerary itinerary = new ItineraryBuilder().withAttractions(ALICE, BENSON).build();
        itinerary.removeAttraction(ALICE);
        assertFalse(itinerary.hasAttraction(ALICE));
        assertTrue(itinerary.hasAttraction(BENSON));
    }

    @Test
    public void getAttractions_returnsUnmodifiableList() {
        Itinerary itinerary = new ItineraryBuilder().withAttractions(ALICE).build();
        try {
            itinerary.getAttractions().remove(0);
            assertFalse(true, "Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // Expected
            assertTrue(true);
        }
    }
}
