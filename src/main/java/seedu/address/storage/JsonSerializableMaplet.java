package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Maplet;
import seedu.address.model.ReadOnlyMaplet;
import seedu.address.model.person.Person;

/**
 * An Immutable Maplet that is serializable to JSON format.
 */
@JsonRootName(value = "maplet")
class JsonSerializableMaplet {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMaplet} with the given persons.
     */
    @JsonCreator
    public JsonSerializableMaplet(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyMaplet} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMaplet}.
     */
    public JsonSerializableMaplet(ReadOnlyMaplet source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Maplet into the model's {@code Maplet} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Maplet toModelType() throws IllegalValueException {
        Maplet maplet = new Maplet();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (maplet.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            maplet.addPerson(person);
        }
        return maplet;
    }

}
