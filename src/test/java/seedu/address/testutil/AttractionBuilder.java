package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.attraction.Activities;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Comment;
import seedu.address.model.attraction.Contact;
import seedu.address.model.attraction.Name;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Price;
import seedu.address.model.attraction.Priority;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Attraction objects.
 */
public class AttractionBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PRIORITY = "5";
    public static final String DEFAULT_CONTACT = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_ACTIVITIES = "Sightseeing";
    public static final String DEFAULT_OPENING_HOURS = "0000 - 2359";
    public static final String DEFAULT_PRICE = "10";

    private Name name;
    private Priority priority;
    private Contact contact;
    private Address address;
    private Activities activities;
    private OpeningHours openingHours;
    private Price price;
    private Set<Tag> tags;
    private Set<Comment> comments;

    /**
     * Creates a {@code AttractionBuilder} with the default details.
     */
    public AttractionBuilder() {
        name = new Name(DEFAULT_NAME);
        priority = new Priority(DEFAULT_PRIORITY);
        contact = new Contact(DEFAULT_CONTACT);
        address = new Address(DEFAULT_ADDRESS);
        activities = new Activities(DEFAULT_ACTIVITIES);
        openingHours = new OpeningHours(DEFAULT_OPENING_HOURS);
        price = new Price(DEFAULT_PRICE);
        tags = new HashSet<>();
        comments = new HashSet<>();
    }

    /**
     * Initializes the AttractionBuilder with the data of {@code attractionToCopy}.
     */
    public AttractionBuilder(Attraction attractionToCopy) {
        name = attractionToCopy.getName();
        priority = attractionToCopy.getPriority();
        contact = attractionToCopy.getContact();
        address = attractionToCopy.getAddress();
        activities = attractionToCopy.getActivities();
        openingHours = attractionToCopy.getOpeningHours();
        price = attractionToCopy.getPrice();
        tags = new HashSet<>(attractionToCopy.getTags());
        comments = new HashSet<>(attractionToCopy.getComments());
    }

    /**
     * Sets the {@code Name} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Attraction} that we are building.
     */
    public AttractionBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Activities} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withActivities(String activities) {
        this.activities = new Activities(activities);
        return this;
    }

    /**
     * Sets the {@code OpeningHours} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withOpeningHours(String openingHours) {
        this.openingHours = new OpeningHours(openingHours);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withPriority(String priority) {
        this.priority = new Priority(priority);
        return this;
    }

    /**
     * Sets the {@code Contact} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withContact(String contact) {
        this.contact = new Contact(contact);
        return this;
    }

    /**
     * Parses the {@code comments} into a {@code Set<Comment>}
     * and set it to the {@code Attraction} that we are building.
     */
    public AttractionBuilder withComments(String ... comments) {
        this.comments = SampleDataUtil.getCommentSet(comments);
        return this;
    }

    public Attraction build() {
        return new Attraction(name, priority, contact, address, activities, openingHours, price, tags, comments);
    }

}
