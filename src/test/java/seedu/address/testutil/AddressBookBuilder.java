package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.attraction.Attraction;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withAttraction("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Attraction} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withAttraction(Attraction attraction) {
        addressBook.addAttraction(attraction);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
