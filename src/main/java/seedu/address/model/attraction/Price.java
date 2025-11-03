package seedu.address.model.attraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an attraction's expected cost in Maplet.
 * Guarantees: immutable; is valid as declared in {@link #isValidCost(String)}
 */
public class Price {
    public static final String MESSAGE_CONSTRAINTS = "Price should be a numerical value, with 0 to 3 decimal places. "
            + "It can have a 3-letter or a 2-letter and symbol prefix and / or suffix to indicate its currency.";

    /*
     * Accepts the 3-letter ISO code (USD, SGD), both before or after a numerical value (decimals allowed)
     */
    public static final String VALIDATION_REGEX = "^(?<units1>[A-Za-z]{3}|\\p{Sc}|[A-Za-z]{2}\\p{Sc})?"
            + "\\s*(?<value>\\d+(\\.\\d{1,3})?)\\s*"
            + "(?<units2>[A-Za-z]{3}|\\p{Sc}|[A-Za-z]{2}\\p{Sc})?$";

    public final String value;
    private final HashSet<String> units;
    private final Double priceValue;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        final Pattern p = Pattern.compile(VALIDATION_REGEX);
        final Matcher matcher = p.matcher(price);
        matcher.matches();
        units = new HashSet<>();
        units.add(matcher.group("units1"));
        units.add(matcher.group("units2"));
        priceValue = Double.valueOf(matcher.group("value"));
        value = price;
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
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

        // instanceof handles nulls
        if (!(other instanceof Price)) {
            return false;
        }

        Price otherPrice = (Price) other;
        return units.equals(otherPrice.units) && priceValue.equals(otherPrice.priceValue);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

