package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryName;
import static seedu.address.storage.JsonAdaptedItinerary.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.ALICE;
import static seedu.address.testutil.TypicalAttractions.BENSON;
import static seedu.address.testutil.TypicalItineraries.JAPAN_TRIP;

public class JsonAdaptedItineraryTest {

    private static final String VALID_NAME = "Japan Trip";
    private static final String VALID_CREATED_AT = LocalDateTime.of(2024, 10, 21, 10, 0)
            .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    private static final List<String> VALID_ATTRACTIONS = Arrays.asList("Alice Pauline", "Benson Meier");
    private static final List<Attraction> AVAILABLE_ATTRACTIONS = Arrays.asList(ALICE, BENSON);

    private static final String INVALID_NAME = "";
    private static final String INVALID_CREATED_AT = "not-a-date";
    private static final String MISSING_ATTRACTION = "Nonexistent Attraction";

    @Test
    public void toModelType_validItineraryDetails_returnsItinerary() throws Exception {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(JAPAN_TRIP);
        assertEquals(JAPAN_TRIP, itinerary.toModelType(AVAILABLE_ATTRACTIONS));
    }

    @Test
    public void toModelType_validConstructor_returnsItinerary() throws Exception {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(
                VALID_NAME, VALID_CREATED_AT, VALID_ATTRACTIONS);
        Itinerary result = itinerary.toModelType(AVAILABLE_ATTRACTIONS);
        assertEquals(VALID_NAME, result.getName().fullName);
        assertEquals(2, result.getAttractions().size());
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(
                null, VALID_CREATED_AT, VALID_ATTRACTIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                ItineraryName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage,
                () -> itinerary.toModelType(AVAILABLE_ATTRACTIONS));
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(
                INVALID_NAME, VALID_CREATED_AT, VALID_ATTRACTIONS);
        assertThrows(IllegalArgumentException.class, () -> itinerary.toModelType(AVAILABLE_ATTRACTIONS));
    }

    @Test
    public void toModelType_nullCreatedAt_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(
                VALID_NAME, null, VALID_ATTRACTIONS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "createdAt");
        assertThrows(IllegalValueException.class, expectedMessage,
                () -> itinerary.toModelType(AVAILABLE_ATTRACTIONS));
    }

    @Test
    public void toModelType_invalidCreatedAt_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(
                VALID_NAME, INVALID_CREATED_AT, VALID_ATTRACTIONS);
        String expectedMessage = "Invalid itinerary timestamp: " + INVALID_CREATED_AT;
        assertThrows(IllegalValueException.class, expectedMessage,
                () -> itinerary.toModelType(AVAILABLE_ATTRACTIONS));
    }

    @Test
    public void toModelType_nullAttractions_returnsItineraryWithEmptyList() throws Exception {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(
                VALID_NAME, VALID_CREATED_AT, null);
        Itinerary result = itinerary.toModelType(AVAILABLE_ATTRACTIONS);
        assertEquals(0, result.getAttractions().size());
    }

    @Test
    public void toModelType_missingAttraction_throwsIllegalValueException() {
        List<String> invalidAttractions = new ArrayList<>(VALID_ATTRACTIONS);
        invalidAttractions.add(MISSING_ATTRACTION);
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(
                VALID_NAME, VALID_CREATED_AT, invalidAttractions);
        String expectedMessage = "Itinerary references missing attraction: " + MISSING_ATTRACTION;
        assertThrows(IllegalValueException.class, expectedMessage,
                () -> itinerary.toModelType(AVAILABLE_ATTRACTIONS));
    }

    @Test
    public void toModelType_emptyAttractionsList_returnsItineraryWithNoAttractions() throws Exception {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(
                VALID_NAME, VALID_CREATED_AT, new ArrayList<>());
        Itinerary result = itinerary.toModelType(AVAILABLE_ATTRACTIONS);
        assertEquals(0, result.getAttractions().size());
    }
}
