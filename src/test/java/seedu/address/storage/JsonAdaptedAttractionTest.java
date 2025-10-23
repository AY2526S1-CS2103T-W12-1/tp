package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAttraction.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Contact;
import seedu.address.model.attraction.Name;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Priority;

public class JsonAdaptedAttractionTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PRIORITY = "11";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_CONTACT = "example.com";
    private static final String INVALID_ACTIVITIES = " ";
    private static final String INVALID_OPENING_HOURS = "3072 - 1200";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PRIORITY = BENSON.getPriority().toString();
    private static final String VALID_CONTACT = BENSON.getContact().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_ACTIVITIES = BENSON.getActivities().toString();
    private static final String VALID_OPENING_HOURS = BENSON.getOpeningHours().toString();
    private static final String VALID_PRICE = BENSON.getPrice().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validAttractionDetails_returnsAttraction() throws Exception {
        JsonAdaptedAttraction attraction = new JsonAdaptedAttraction(BENSON);
        assertEquals(BENSON, attraction.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction =
                new JsonAdaptedAttraction(
                        INVALID_NAME, VALID_PRIORITY, VALID_CONTACT,
                        VALID_ADDRESS, VALID_ACTIVITIES, VALID_OPENING_HOURS, VALID_PRICE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction =
                new JsonAdaptedAttraction(
                        null, VALID_PRIORITY, VALID_CONTACT, VALID_ADDRESS,
                        VALID_ACTIVITIES, VALID_OPENING_HOURS, VALID_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction =
                new JsonAdaptedAttraction(
                        VALID_NAME, INVALID_PRIORITY, VALID_CONTACT, VALID_ADDRESS,
                        VALID_ACTIVITIES, VALID_OPENING_HOURS, VALID_PRICE, VALID_TAGS);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction = new JsonAdaptedAttraction(
                VALID_NAME, null, VALID_CONTACT, VALID_ADDRESS,
                VALID_ACTIVITIES, VALID_OPENING_HOURS, VALID_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_invalidContact_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction =
                new JsonAdaptedAttraction(
                        VALID_NAME, VALID_PRIORITY, INVALID_CONTACT, VALID_ADDRESS,
                        VALID_ACTIVITIES, VALID_OPENING_HOURS, VALID_PRICE, VALID_TAGS);
        String expectedMessage = Contact.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_nullContact_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction = new JsonAdaptedAttraction(
                VALID_NAME, VALID_PRIORITY, null, VALID_ADDRESS,
                VALID_ACTIVITIES, VALID_OPENING_HOURS, VALID_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Contact.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction =
                new JsonAdaptedAttraction(
                        VALID_NAME, VALID_PRIORITY, VALID_CONTACT, INVALID_ADDRESS,
                        VALID_ACTIVITIES, VALID_OPENING_HOURS, VALID_PRICE, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction = new JsonAdaptedAttraction(
                VALID_NAME, VALID_PRIORITY, VALID_CONTACT, null,
                VALID_ACTIVITIES, VALID_OPENING_HOURS, VALID_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_invalidOpeningHours_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction =
                new JsonAdaptedAttraction(
                        VALID_NAME, VALID_PRIORITY, VALID_CONTACT, VALID_ADDRESS,
                        VALID_ACTIVITIES, INVALID_OPENING_HOURS, VALID_PRICE, VALID_TAGS);
        String expectedMessage = OpeningHours.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_nullOpeningHours_throwsIllegalValueException() {
        JsonAdaptedAttraction attraction = new JsonAdaptedAttraction(
                VALID_NAME, VALID_PRIORITY, VALID_CONTACT, VALID_ADDRESS,
                VALID_ACTIVITIES, null, VALID_PRICE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, OpeningHours.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, attraction::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedAttraction attraction = new JsonAdaptedAttraction(
                VALID_NAME, VALID_PRIORITY, VALID_CONTACT, VALID_ADDRESS,
                VALID_ACTIVITIES, VALID_OPENING_HOURS, VALID_PRICE, invalidTags);
        assertThrows(IllegalValueException.class, attraction::toModelType);
    }
}
