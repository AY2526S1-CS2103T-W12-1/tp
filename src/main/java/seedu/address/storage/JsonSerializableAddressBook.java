package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.attraction.Attraction;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_ATTRACTION = "Attractions list contains duplicate attraction(s).";

    private final List<JsonAdaptedAttraction> attractions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given attractions.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("attractions") List<JsonAdaptedAttraction> attractions) {
        this.attractions.addAll(attractions);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        attractions.addAll(source.getAttractionList().stream().map(JsonAdaptedAttraction::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedAttraction jsonAdaptedAttraction : attractions) {
            Attraction attraction = jsonAdaptedAttraction.toModelType();
            if (addressBook.hasAttraction(attraction)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ATTRACTION);
            }
            addressBook.addAttraction(attraction);
        }
        return addressBook;
    }

}
