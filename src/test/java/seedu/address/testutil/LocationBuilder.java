package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.attraction.Name;
import seedu.address.model.location.Location;
import seedu.address.model.location.LocationName;

/**
 * A utility class to help with building {@link Location} objects.
 */
public class LocationBuilder {

    public static final String DEFAULT_LOCATION_NAME = "Singapore";
    public static final String[] DEFAULT_ATTRACTIONS = {"Alice Pauline"};

    private LocationName locationName;
    private Set<Name> attractionNames;

    /**
     * LocationBuilder Constructor
     */
    public LocationBuilder() {
        locationName = new LocationName(DEFAULT_LOCATION_NAME);
        attractionNames = convertToNameSet(DEFAULT_ATTRACTIONS);
    }

    /**
     * LocationBuilder Constructor
     * @param location
     */
    public LocationBuilder(Location location) {
        locationName = location.getName();
        attractionNames = new HashSet<>(location.getAttractionNames());
    }

    /**
     * Sets the {@code LocationName} of the {@code Location} that we are building.
     */
    public LocationBuilder withLocationName(String name) {
        locationName = new LocationName(name);
        return this;
    }

    /**
     * Sets the attraction names of the {@code Location} that we are building.
     */
    public LocationBuilder withAttractionNames(String... names) {
        attractionNames = convertToNameSet(names);
        return this;
    }

    public Location build() {
        return new Location(locationName, attractionNames);
    }

    private Set<Name> convertToNameSet(String... names) {
        return Arrays.stream(names)
                .map(Name::new)
                .collect(Collectors.toSet());
    }
}
