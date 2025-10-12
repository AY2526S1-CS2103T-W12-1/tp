package seedu.address.testutil;

import seedu.address.model.Maplet;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Maplet objects.
 * Example usage: <br>
 *     {@code Maplet ab = new MapletBuilder().withPerson("John", "Doe").build();}
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
     * Adds a new {@code Person} to the {@code Maplet} that we are building.
     */
    public MapletBuilder withPerson(Person person) {
        maplet.addPerson(person);
        return this;
    }

    public Maplet build() {
        return maplet;
    }
}
