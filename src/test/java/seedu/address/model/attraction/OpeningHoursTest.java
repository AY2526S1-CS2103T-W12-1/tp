package seedu.address.model.attraction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

// adapted from addressTest
public class OpeningHoursTest {

    // The static NON_SPECIFIED_HOURS should be used instead of a null constructor.
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OpeningHours(null));
    }

    @Test
    public void constructor_invalidOpeningHours_throwsIllegalArgumentException() {
        String invalidOpeningHours = "1300 - 2500";
        assertThrows(IllegalArgumentException.class, () -> new OpeningHours(invalidOpeningHours));
    }

    @Test
    public void isValidOpeningHours() {
        // null opening hours
        assertThrows(NullPointerException.class, () -> OpeningHours.isValidOpeningHours(null));

        // invalid opening hours
        assertFalse(OpeningHours.isValidOpeningHours("500 - 2100")); // Missing zero
        assertFalse(OpeningHours.isValidOpeningHours("0700  -  0800")); // too many spaces

        // valid opening hours
        assertTrue(OpeningHours.isValidOpeningHours("0100 - 0200"));
        assertTrue(OpeningHours.isValidOpeningHours("2200 - 0300")); // spanning over midnight
        assertTrue(OpeningHours.isValidOpeningHours("0000 - 2359")); // 24 hour
    }

    @Test
    public void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> OpeningHours.isValidTime(null));

        // invalid time
        assertFalse(OpeningHours.isValidTime("2500")); // Outside 24 hour
        assertFalse(OpeningHours.isValidTime("a120")); // alphabet

        // valid times
        assertTrue(OpeningHours.isValidTime("0222"));
        assertTrue(OpeningHours.isValidTime("0100"));
        assertTrue(OpeningHours.isValidTime("2359"));
    }

    @Test
    public void isOpen() {
        OpeningHours normalHours = new OpeningHours("1200 - 1800");
        OpeningHours pastMidnight = new OpeningHours("2200 - 0600");
        OpeningHours nullOpeningHours = OpeningHours.NON_SPECIFIED_HOURS;

        // outside of opening hours
        assertFalse(normalHours.isOpen("1100")); // Too early
        assertFalse(pastMidnight.isOpen("1200")); // Outside of opening hours

        // within opening hours
        assertTrue(normalHours.isOpen("1400"));
        assertTrue(pastMidnight.isOpen("0400"));
        assertTrue(nullOpeningHours.isOpen("0000"));
        assertTrue(nullOpeningHours.isOpen("1200"));
    }

    @Test
    public void equals() {
        OpeningHours openingHours = new OpeningHours("1200 - 1300");

        // same values -> returns true
        assertTrue(openingHours.equals(new OpeningHours("1200 - 1300")));

        // same object -> returns true
        assertTrue(openingHours.equals(openingHours));

        // null -> returns false
        assertFalse(openingHours.equals(null));

        // different types -> returns false
        assertFalse(openingHours.equals(5.0f));

        // different values -> returns false
        assertFalse(openingHours.equals(new OpeningHours("1300 - 1200")));

        OpeningHours nullOpeningHours = OpeningHours.NON_SPECIFIED_HOURS;

        // null and null -> returns true
        assertTrue(nullOpeningHours.equals(OpeningHours.NON_SPECIFIED_HOURS));

        // same object -> returns true
        assertTrue(nullOpeningHours.equals(nullOpeningHours));

        // different values -> returns false
        assertFalse(nullOpeningHours.equals(new OpeningHours("1300 - 1200")));

    }
}
