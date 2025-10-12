package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Activities;
import seedu.address.model.person.Address;
import seedu.address.model.person.Contact;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Priority;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PRIORITY = "5";
    public static final String DEFAULT_CONTACT = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_ACTIVITIES = "Sightseeing";

    private Name name;
    private Priority priority;
    private Contact contact;
    private Address address;
    private Activities activities;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        priority = new Priority(DEFAULT_PRIORITY);
        contact = new Contact(DEFAULT_CONTACT);
        address = new Address(DEFAULT_ADDRESS);
        activities = new Activities(DEFAULT_ACTIVITIES);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        priority = personToCopy.getPriority();
        contact = personToCopy.getContact();
        address = personToCopy.getAddress();
        activities = personToCopy.getActivities();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Activities} of the {@code Person} that we are building.
     */
    public PersonBuilder withActivities(String activities) {
        this.activities = new Activities(activities);
        return this;
    }
    /**
     * Sets the {@code Priority} of the {@code Person} that we are building.
     */
    public PersonBuilder withPriority(String priority) {
        this.priority = new Priority(priority);
        return this;
    }

    /**
     * Sets the {@code Contact} of the {@code Person} that we are building.
     */
    public PersonBuilder withContact(String contact) {
        this.contact = new Contact(contact);
        return this;
    }

    public Person build() {
        return new Person(name, priority, contact, address, activities, tags);
    }

}
