package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attraction.Activities;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Contact;
import seedu.address.model.attraction.Name;
import seedu.address.model.attraction.Priority;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Attraction}.
 */
class JsonAdaptedAttraction {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Attraction's %s field is missing!";

    private final String name;
    private final String priority;
    private final String contact;
    private final String address;
    private final String activities;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAttraction} with the given attraction details.
     */
    @JsonCreator
    public JsonAdaptedAttraction(@JsonProperty("name") String name, @JsonProperty("priority") String priority,
                                 @JsonProperty("contact") String contact, @JsonProperty("address") String address,
                                 @JsonProperty("activities") String activities,
                                 @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.priority = priority;
        this.contact = contact;
        this.address = address;
        this.activities = activities;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Attraction} into this class for Jackson use.
     */
    public JsonAdaptedAttraction(Attraction source) {
        name = source.getName().fullName;
        priority = source.getPriority().value;
        contact = source.getContact().value;
        address = source.getAddress().value;
        activities = source.getActivities().activities;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted attraction object into the model's {@code Attraction} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attraction.
     */
    public Attraction toModelType() throws IllegalValueException {
        final List<Tag> attractionTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            attractionTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Priority.class.getSimpleName()));
        }
        if (!Priority.isValidPriority(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
        final Priority modelPriority = new Priority(priority);

        if (contact == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Contact.class.getSimpleName()));
        }
        if (!Contact.isValidContact(contact)) {
            throw new IllegalValueException(Contact.MESSAGE_CONSTRAINTS);
        }
        final Contact modelContact = new Contact(contact);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (activities == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Activities.class.getSimpleName()));
        }
        if (!Activities.isValidActivities(activities)) {
            throw new IllegalValueException(Activities.MESSAGE_CONSTRAINTS);
        }
        final Activities modelActivities = new Activities(activities);

        final Set<Tag> modelTags = new HashSet<>(attractionTags);
        return new Attraction(modelName, modelPriority, modelContact, modelAddress, modelActivities, modelTags);
    }

}
