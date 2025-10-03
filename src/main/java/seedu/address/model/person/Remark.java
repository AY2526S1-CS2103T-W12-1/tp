package seedu.address.model.person;

public class Remark {
    public static final String MESSAGE_CONSTRAINTS =
            "Remarks can be any string, and it should not be blank";
    public static final String VALIDATION_REGEX = ".+";
    
    public final String value;

    public Remark(String remark) {
        this.value = remark;
    }

    /**
     * Returns true if a given string is a valid remark.
     */
    public static boolean isValidRemark(String test) {
        return test.matches(VALIDATION_REGEX);
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
        if (!(other instanceof Remark)) {
            return false;
        }
        Remark otherRemark = (Remark) other;
        return value.equals(otherRemark.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
