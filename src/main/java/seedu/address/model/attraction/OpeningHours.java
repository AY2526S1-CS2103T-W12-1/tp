package seedu.address.model.attraction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an attraction's opening hours in Maplet.
 * Guarantees: immutable; is valid as declared in {@link #isValidOpeningHours(String)}
 */
public class OpeningHours {

    public static final String MESSAGE_CONSTRAINTS = "Opening hours should be in the HHMM - HHMM 24-hour format";
    public static final String TIME_CONSTRAINTS = "Time should be in the HHMM 24-hour format";
    public static final String OPENING_HOURS_VALIDATION_REGEX =
            "(?<opensAt>([0-1][0-9]|2[0-3])[0-5][0-9])"
            + "\\s?-\\s?"
            + "(?<closesAt>([0-1][0-9]|2[0-3])[0-5][0-9])$";
    // Valid times: 2359, 0000, 1900
    // Invalid times: 2400, 2900
    public static final String TIME_VALIDATION_REGEX = "^([0-1][0-9]|2[0-3])[0-5][0-9]$";
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    public static final String FULL_DAY_OPENING_HOURS = "24-Hour";
    public static final OpeningHours NON_SPECIFIED_HOURS = new OpeningHours();
    public final LocalTime opensAt;
    public final LocalTime closesAt;
    private final boolean pastMidnight;

    /**
     * Constructs an {@code OpeningHours}.
     *
     * @param openingHours A valid set of opening hours.
     */
    public OpeningHours(String openingHours) {
        requireNonNull(openingHours);
        checkArgument(isValidOpeningHours(openingHours), MESSAGE_CONSTRAINTS);
        final Pattern p = Pattern.compile(OPENING_HOURS_VALIDATION_REGEX);
        final Matcher matcher = p.matcher(openingHours);
        matcher.matches();
        opensAt = LocalTime.parse(matcher.group("opensAt"), TIME_FORMATTER);
        closesAt = LocalTime.parse(matcher.group("closesAt"), TIME_FORMATTER);
        pastMidnight = opensAt.isAfter(closesAt);
    }

    private OpeningHours() {
        this.opensAt = null;
        this.closesAt = null;
        this.pastMidnight = false;
    }

    /**
     * Returns true if a given string is a valid set of Opening Hours.
     */
    public static boolean isValidOpeningHours(String test) {
        requireNonNull(test);
        return test.matches(OPENING_HOURS_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        requireNonNull(test);
        return test.matches(TIME_VALIDATION_REGEX);
    }

    /**
     * Returns true if the given time is within the opening hours.
     * Supports the scenario where opening hours span past midnight.
     */
    public boolean isOpen(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), TIME_CONSTRAINTS);
        LocalTime timeQuery = LocalTime.parse(time, TIME_FORMATTER);
        if (this.equals(NON_SPECIFIED_HOURS)) {
            return true;
        }
        if (pastMidnight) {
            return opensAt.isBefore(timeQuery) || closesAt.isAfter(timeQuery) || opensAt.equals(timeQuery);
        } else {
            return opensAt.equals(timeQuery) || (opensAt.isBefore(timeQuery) && closesAt.isAfter(timeQuery));
        }
    }

    @Override
    public String toString() {
        if (this == NON_SPECIFIED_HOURS) {
            return FULL_DAY_OPENING_HOURS;
        }
        return opensAt.format(TIME_FORMATTER) + " - " + closesAt.format(TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OpeningHours)) {
            return false;
        }

        if (other == NON_SPECIFIED_HOURS || this == NON_SPECIFIED_HOURS) {
            return false;
        }

        OpeningHours otherOpeningHours = (OpeningHours) other;
        return opensAt.equals(otherOpeningHours.opensAt) && closesAt.equals(otherOpeningHours.closesAt);
    }
}
