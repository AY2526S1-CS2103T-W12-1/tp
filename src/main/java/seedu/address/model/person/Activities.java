package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Activities {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    
    public final String activities;

    public Activities(String remark) {
        requireNonNull(remark);
        activities = remark;
    }

    /**
     * Returns true if a given string is a valid Activities.
     */
    public static boolean isValidActivities(String test) { return true; }
    
    @Override
    public String toString() { return activities; }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        
        if (!(other instanceof Activities)) {
            return false;
        }
        
        Activities otherActivities = (Activities) other;
        return activities.equals(otherActivities.activities);
    }
    
    @Override
    public int hashCode() {
        return activities.hashCode();
    }
}