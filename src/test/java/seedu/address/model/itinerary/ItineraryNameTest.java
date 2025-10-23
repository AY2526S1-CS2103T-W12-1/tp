package seedu.address.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ItineraryNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItineraryName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new ItineraryName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> ItineraryName.isValidName(null));

        // invalid name
        assertFalse(ItineraryName.isValidName("")); // empty string
        assertFalse(ItineraryName.isValidName(" ")); // spaces only

        // valid name
        assertTrue(ItineraryName.isValidName("Japan Trip")); // alphabets with space
        assertTrue(ItineraryName.isValidName("12345")); // numbers only
        assertTrue(ItineraryName.isValidName("Europe 2025")); // alphanumeric
        assertTrue(ItineraryName.isValidName("Trip to Australia!")); // with special chars
    }

    @Test
    public void equals() {
        ItineraryName name = new ItineraryName("Japan Trip");

        assertTrue(name.equals(new ItineraryName("Japan Trip")));

        assertTrue(name.equals(name));

        assertFalse(name.equals(null));

        assertFalse(name.equals(5.0f));

        assertFalse(name.equals(new ItineraryName("Europe Tour")));
    }

    @Test
    public void hashCode_sameValues_sameHash() {
        ItineraryName name1 = new ItineraryName("Japan Trip");
        ItineraryName name2 = new ItineraryName("Japan Trip");
        assertTrue(name1.hashCode() == name2.hashCode());
    }

    @Test
    public void toString_returnsFullName() {
        ItineraryName name = new ItineraryName("Japan Trip");
        assertTrue(name.toString().equals("Japan Trip"));
    }
}
