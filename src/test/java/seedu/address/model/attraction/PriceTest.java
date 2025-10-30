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
        String invalidPrice = "%200";
        assertThrows(IllegalArgumentException.class, () -> new Price(invalidPrice));
    }

    @Test
    public void isValidPrice() {
        // null price
        assertThrows(NullPointerException.class, () -> Price.isValidPrice(null));

        // invalid prices
        assertFalse(Price.isValidPrice("JP 2")); // Insufficient alphabets (3 letters required)
        assertFalse(Price.isValidPrice("")); // empty string

        // valid price
        assertTrue(Price.isValidPrice("JPY 200"));// 3 Letter currency code
        assertTrue(Price.isValidPrice("$2")); // no space
        assertTrue(Price.isValidPrice("USD 12.20")); // decimals
        assertTrue(Price.isValidPrice("5 USD")); // Units at the end
        assertTrue(Price.isValidPrice("5$")); // Units at the end
        assertTrue(Price.isValidPrice("US$ 5")); // 2-letter iso code with D replaced by $
        assertTrue(Price.isValidPrice("5")); // No units
        assertTrue(Price.isValidPrice("5           US$")); // A lot of spaces
        assertTrue(Price.isValidPrice("5")); // A lot of spaces
    }

    @Test
    public void equals() {
        Price price = new Price("15 USD");

        // same values -> returns true
        assertTrue(price.equals(new Price("15 USD")));
        assertTrue(price.equals(new Price("USD 15")));
        assertTrue(price.equals(new Price("USD 15.00")));

        // same object -> returns true
        assertTrue(price.equals(price));

        // null -> returns false
        assertFalse(price.equals(null));

        // different types -> returns false
        assertFalse(price.equals(5.0f));

        // different values -> returns false
        assertFalse(price.equals(new Price("12 USD")));
        assertFalse(price.equals(new Price("15 SGD")));
        assertFalse(price.equals(new Price("$15")));
    }
}
