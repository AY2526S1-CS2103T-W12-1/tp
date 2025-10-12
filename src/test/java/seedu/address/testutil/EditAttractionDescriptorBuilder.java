package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditAttractionDescriptor;
import seedu.address.model.attraction.Activities;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Contact;
import seedu.address.model.attraction.Name;
import seedu.address.model.attraction.Priority;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditAttractionDescriptor objects.
 */
public class EditAttractionDescriptorBuilder {

    private EditCommand.EditAttractionDescriptor descriptor;

    public EditAttractionDescriptorBuilder() {
        descriptor = new EditCommand.EditAttractionDescriptor();
    }

    public EditAttractionDescriptorBuilder(EditAttractionDescriptor descriptor) {
        this.descriptor = new EditCommand.EditAttractionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAttractionDescriptor} with fields containing {@code attraction}'s details
     */
    public EditAttractionDescriptorBuilder(Attraction attraction) {
        descriptor = new EditCommand.EditAttractionDescriptor();
        descriptor.setName(attraction.getName());
        descriptor.setPriority(attraction.getPriority());
        descriptor.setContact(attraction.getContact());
        descriptor.setAddress(attraction.getAddress());
        descriptor.setTags(attraction.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditAttractionDescriptor} that we are building.
     */
    public EditAttractionDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditAttractionDescriptor} that we are building.
     */
    public EditAttractionDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(new Priority(priority));
        return this;
    }

    /**
     * Sets the {@code Contact} of the {@code EditAttractionDescriptor} that we are building.
     */
    public EditAttractionDescriptorBuilder withContact(String contact) {
        descriptor.setContact(new Contact(contact));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditAttractionDescriptor} that we are building.
     */
    public EditAttractionDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Activities} of the {@code EditAttractionDescriptor} that we are building.
     */
    public EditAttractionDescriptorBuilder withActivities(String activities) {
        descriptor.setActivities(new Activities(activities));
        return this;
    }
    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditAttractionDescriptor}
     * that we are building.
     */
    public EditAttractionDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditAttractionDescriptor build() {
        return descriptor;
    }
}
