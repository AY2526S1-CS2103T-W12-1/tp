package seedu.address.testutil;

import seedu.address.model.Maplet;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.location.Location;

/**
 * A utility class to help with building Maplet objects.
 * Example usage: <br>
 *     {@code Maplet ab = new MapletBuilder().withAttraction("John", "Doe").build();}
 */
public class MapletBuilder {

    private Maplet maplet;

    public MapletBuilder() {
        maplet = new Maplet();
    }

    public MapletBuilder(Maplet maplet) {
        this.maplet = maplet;
    }

    /**
     * Adds a new {@code Attraction} to the {@code Maplet} that we are building.
     */
    public MapletBuilder withAttraction(Attraction attraction) {
        maplet.addAttraction(attraction);
        return this;
    }

    /**
     * Adds a new {@code Location} to the {@code Maplet} that we are building.
     */
    public MapletBuilder withLocation(Location location) {
        maplet.addLocation(location);
        return this;
    }

    public Maplet build() {
        return maplet;
    }
}
