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
        assertTrue(Contact.isValidContact("1")); // valid priority
    }

    @Test
    public void equals() {
        Contact contact1 = new Contact("alice@example.com");
        Contact contact2 = new Contact("alice@example.com");
        Contact contact3 = new Contact("bob@example.com");

        // same object -> true
        assertTrue(contact1.equals(contact1));

        // same values -> true
        assertTrue(contact1.equals(contact2));

        // different values -> false
        assertFalse(contact1.equals(contact3));

        // different type -> false
        assertFalse(contact1.equals(42));

        // null -> false
        assertFalse(contact1.equals(null));
    }

    @Test
    public void hashCode_sameValue_sameHash() {
        Contact c1 = new Contact("alice@example.com");
        Contact c2 = new Contact("alice@example.com");
        assertTrue(c1.hashCode() == c2.hashCode());
    }

    @Test
    public void toString_returnsValue() {
        Contact contact = new Contact("alice@example.com");
        assertTrue(contact.toString().equals("alice@example.com"));
    }


}
