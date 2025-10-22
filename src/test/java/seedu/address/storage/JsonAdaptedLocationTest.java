package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attraction.Name;
import seedu.address.model.location.Location;
import seedu.address.model.location.LocationName;
import seedu.address.testutil.TypicalAttractions;
import seedu.address.testutil.TypicalLocations;

public class JsonAdaptedLocationTest {

    private static final List<String> VALID_ATTRACTION_NAMES = Arrays.asList(
            TypicalAttractions.ALICE.getName().fullName,
            TypicalAttractions.BENSON.getName().fullName);

    @Test
    public void toModelType_validLocationDetails_returnsLocation() throws Exception {
        JsonAdaptedLocation adaptedLocation = new JsonAdaptedLocation(TypicalLocations.SINGAPORE);
        Location modelLocation = adaptedLocation.toModelType(TypicalAttractions.getTypicalAttractions());
        assertEquals(TypicalLocations.SINGAPORE, modelLocation);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedLocation adaptedLocation = new JsonAdaptedLocation(null, VALID_ATTRACTION_NAMES);
        String expectedMessage = String.format(JsonAdaptedLocation.MISSING_FIELD_MESSAGE_FORMAT,
                LocationName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () ->
                adaptedLocation.toModelType(TypicalAttractions.getTypicalAttractions()));
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedLocation adaptedLocation = new JsonAdaptedLocation(" ", VALID_ATTRACTION_NAMES);
        assertThrows(IllegalValueException.class, LocationName.MESSAGE_CONSTRAINTS, () ->
                adaptedLocation.toModelType(TypicalAttractions.getTypicalAttractions()));
    }

    @Test
    public void toModelType_emptyAttractions_throwsIllegalValueException() {
        JsonAdaptedLocation adaptedLocation = new JsonAdaptedLocation("Singapore", Collections.emptyList());
        assertThrows(IllegalValueException.class, Location.MESSAGE_EMPTY_ATTRACTIONS, () ->
                adaptedLocation.toModelType(TypicalAttractions.getTypicalAttractions()));
    }

    @Test
    public void toModelType_nullAttractionName_throwsIllegalValueException() {
        JsonAdaptedLocation adaptedLocation = new JsonAdaptedLocation("Singapore",
                Arrays.asList(TypicalAttractions.ALICE.getName().fullName, null));
        String expectedMessage = String.format(JsonAdaptedLocation.MISSING_FIELD_MESSAGE_FORMAT,
                Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () ->
                adaptedLocation.toModelType(TypicalAttractions.getTypicalAttractions()));
    }

    @Test
    public void toModelType_invalidAttractionName_throwsIllegalValueException() {
        JsonAdaptedLocation adaptedLocation = new JsonAdaptedLocation("Singapore",
                Arrays.asList(TypicalAttractions.ALICE.getName().fullName, "@lice"));
        assertThrows(IllegalValueException.class, Name.MESSAGE_CONSTRAINTS, () ->
                adaptedLocation.toModelType(TypicalAttractions.getTypicalAttractions()));
    }

    @Test
    public void toModelType_nonexistentAttractionReference_throwsIllegalValueException() {
        JsonAdaptedLocation adaptedLocation = new JsonAdaptedLocation("Singapore",
                Arrays.asList(TypicalAttractions.ALICE.getName().fullName, "Nonexistent Attraction"));
        String expectedMessage = String.format(JsonAdaptedLocation.MESSAGE_INVALID_ATTRACTION_REFERENCE,
                new Name("Nonexistent Attraction"));
        assertThrows(IllegalValueException.class, expectedMessage, () ->
                adaptedLocation.toModelType(TypicalAttractions.getTypicalAttractions()));
    }

    @Test
    public void toModelType_duplicateAttractionReference_throwsIllegalValueException() {
        JsonAdaptedLocation adaptedLocation = new JsonAdaptedLocation("Singapore",
                Arrays.asList(TypicalAttractions.ALICE.getName().fullName,
                        TypicalAttractions.ALICE.getName().fullName));
        assertThrows(IllegalValueException.class, JsonAdaptedLocation.MESSAGE_DUPLICATE_ATTRACTION_REFERENCE, () ->
                adaptedLocation.toModelType(TypicalAttractions.getTypicalAttractions()));
    }
}