package seedu.address.testutil;

import static seedu.address.testutil.TypicalAttractions.ALICE;
import static seedu.address.testutil.TypicalAttractions.BENSON;
import static seedu.address.testutil.TypicalAttractions.CARL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.location.Location;

/**
 * A utility class containing a list of {@code Location} objects to be used in tests.
 */
public class TypicalLocations {

    public static final Location SINGAPORE = new LocationBuilder().withLocationName("Singapore")
            .withAttractionNames(ALICE.getName().fullName, BENSON.getName().fullName)
            .build();
    public static final Location CBD = new LocationBuilder().withLocationName("Central Business District")
            .withAttractionNames(BENSON.getName().fullName, CARL.getName().fullName)
            .build();

    private TypicalLocations() {}

    public static List<Location> getTypicalLocations() {
        return new ArrayList<>(Arrays.asList(SINGAPORE, CBD));
    }
}
