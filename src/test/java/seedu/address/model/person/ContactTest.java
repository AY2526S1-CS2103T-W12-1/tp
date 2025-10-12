package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Contact(null));
    }

    @Test
    public void constructor_invalidContact_throwsIllegalArgumentException() {
        String invalidContact = "";
        assertThrows(IllegalArgumentException.class, () -> new Contact(invalidContact));
    }

    @Test
    public void isValidContact() {
        // null contact
        assertThrows(NullPointerException.class, () -> Contact.isValidContact(null));

        // invalid contacts
        assertFalse(Contact.isValidContact("")); // empty string
        assertFalse(Contact.isValidContact(" ")); // spaces only
        assertFalse(Contact.isValidContact("bob!yahoo")); // neither email nor phone
        assertFalse(Contact.isValidContact("12")); // too short to be phone

        // valid contacts
        assertTrue(Contact.isValidContact("alice@example.com")); // valid email
        assertTrue(Contact.isValidContact("1234567")); // valid phone
    }
}
