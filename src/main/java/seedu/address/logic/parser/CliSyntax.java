package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PRIORITY = new Prefix("p/");
    public static final Prefix PREFIX_CONTACT = new Prefix("c/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_ACTIVITIES = new Prefix("act/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_COMMENT = new Prefix("com/");
    public static final Prefix PREFIX_ITINERARY_ATTRACTION_INDEX = new Prefix("ai/");
    public static final Prefix PREFIX_LOCATION_NAME = new Prefix("ln/");
    public static final Prefix PREFIX_LOCATION_ATTRACTION_INDEX = new Prefix("i/");

}
