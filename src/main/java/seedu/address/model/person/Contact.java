package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's contact in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidContact(String)}
 */
public class Contact {

    public static final String MESSAGE_CONSTRAINTS = "Contacts should either be a valid email address of the "
            + "format local-part@domain, or a phone number containing at least 3 digits.";
    private static final String SPECIAL_CHARACTERS = "+_.-";
    // alphanumeric and special characters
    private static final String ALPHANUMERIC_NO_UNDERSCORE = "[^\\W_]+"; // alphanumeric characters except underscore
    private static final String LOCAL_PART_REGEX = "^" + ALPHANUMERIC_NO_UNDERSCORE + "([" + SPECIAL_CHARACTERS + "]"
            + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_PART_REGEX = ALPHANUMERIC_NO_UNDERSCORE
            + "(-" + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_LAST_PART_REGEX = "(" + DOMAIN_PART_REGEX + "){2,}$";
    private static final String DOMAIN_REGEX = "(" + DOMAIN_PART_REGEX + "\\.)*" + DOMAIN_LAST_PART_REGEX;
    public static final String EMAIL_VALIDATION_REGEX = LOCAL_PART_REGEX + "@" + DOMAIN_REGEX;



    public final String value;

    /**
     * Constructs a {@code Contact}.
     *
     * @param contact A valid contact string.
     */
    public Contact(String contact) {
        requireNonNull(contact);
        checkArgument(isValidContact(contact), MESSAGE_CONSTRAINTS);
        value = contact;
    }

    /**
     * Returns true if a given string is a valid contact.
     */
    public static boolean isValidContact(String test) {
        requireNonNull(test);
        return test.matches(EMAIL_VALIDATION_REGEX) || Phone.isValidPhone(test);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Contact)) {
            return false;
        }

        Contact otherContact = (Contact) other;
        return value.equals(otherContact.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
