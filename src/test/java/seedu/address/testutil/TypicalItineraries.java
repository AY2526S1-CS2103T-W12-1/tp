package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.itinerary.Itinerary;
import static seedu.address.testutil.TypicalAttractions.ALICE;
import static seedu.address.testutil.TypicalAttractions.BENSON;
import static seedu.address.testutil.TypicalAttractions.CARL;

/**
 * A utility class containing a list of {@code Itinerary} objects to be used in
 * tests.
 */
public class TypicalItineraries {

    public static final Itinerary JAPAN_TRIP = new ItineraryBuilder()
            .withName("Japan Trip")
            .withCreatedAt(LocalDateTime.of(2024, 10, 21, 10, 0))
            .withAttractions(ALICE, BENSON)
            .build();

    public static final Itinerary EUROPE_TOUR = new ItineraryBuilder()
            .withName("Europe Tour")
            .withCreatedAt(LocalDateTime.of(2024, 11, 15, 14, 30))
            .withAttractions(CARL)
            .build();

    public static final Itinerary SINGAPORE_VISIT = new ItineraryBuilder()
            .withName("Singapore Visit")
            .withCreatedAt(LocalDateTime.of(2024, 12, 1, 9, 0))
            .withAttractions()
            .build();

    private TypicalItineraries() {
    }

    public static List<Itinerary> getTypicalItineraries() {
        return new ArrayList<>(Arrays.asList(JAPAN_TRIP, EUROPE_TOUR, SINGAPORE_VISIT));
    }
}
