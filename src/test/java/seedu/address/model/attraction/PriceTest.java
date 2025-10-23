package seedu.address.model.attraction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

// adapted from addressTest
public class PriceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Price(null));
    }

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        String invalidPrice = "abc200";
        assertThrows(IllegalArgumentException.class, () -> new Price(invalidPrice));
    }

    @Test
    public void isValidPrice() {
        // null price
        assertThrows(NullPointerException.class, () -> Price.isValidPrice(null));

        // invalid prices
        assertFalse(Price.isValidPrice("abc2")); // Has alphabets
        assertFalse(Price.isValidPrice("")); // empty string

        // valid opening hours
        assertTrue(Price.isValidPrice("12"));
        assertTrue(Price.isValidPrice("1500")); // very expensive
        assertTrue(Price.isValidPrice("5")); // very cheap
    }

    @Test
    public void equals() {
        Price price = new Price("15");

        // same values -> returns true
        assertTrue(price.equals(new Price("15")));

        // same object -> returns true
        assertTrue(price.equals(price));

        // null -> returns false
        assertFalse(price.equals(null));

        // different types -> returns false
        assertFalse(price.equals(5.0f));

        // different values -> returns false
        assertFalse(price.equals(new Price("12")));
    }
}
