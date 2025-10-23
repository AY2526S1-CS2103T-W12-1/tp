package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ATTRACTIONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attraction.Activities;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Comment;
import seedu.address.model.attraction.Contact;
import seedu.address.model.attraction.Name;
import seedu.address.model.attraction.Priority;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing attraction in the Maplet.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the attraction identified "
            + "by the index number used in the displayed attraction list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "ATTRACTION_NAME] "
            + "[" + PREFIX_PRIORITY + "PRIORITY_LEVEL] "
            + "[" + PREFIX_CONTACT + "CONTACT] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_ACTIVITIES + "DESCRIPTION/ACTIVITY] "
            + "[" + PREFIX_TAG + "TAG] "
            + "[" + PREFIX_COMMENT + "COMMENT]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PRIORITY + "5 "
            + PREFIX_CONTACT + "info@example.com "
            + PREFIX_ACTIVITIES + "Reserve tour";

    public static final String MESSAGE_EDIT_ATTRACTION_SUCCESS = "Edited Attraction: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ATTRACTION = "This attraction already exists in the Maplet.";

    private final Index index;
    private final EditAttractionDescriptor editAttractionDescriptor;

    /**
     * @param index of the attraction in the filtered attraction list to edit
     * @param editAttractionDescriptor details to edit the attraction with
     */
    public EditCommand(Index index, EditAttractionDescriptor editAttractionDescriptor) {
        requireNonNull(index);
        requireNonNull(editAttractionDescriptor);

        this.index = index;
        this.editAttractionDescriptor = new EditAttractionDescriptor(editAttractionDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Attraction> lastShownList = model.getFilteredAttractionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
        }

        Attraction attractionToEdit = lastShownList.get(index.getZeroBased());
        Attraction editedAttraction = createEditedAttraction(attractionToEdit, editAttractionDescriptor);

        if (!attractionToEdit.isSameAttraction(editedAttraction) && model.hasAttraction(editedAttraction)) {
            throw new CommandException(MESSAGE_DUPLICATE_ATTRACTION);
        }

        model.setAttraction(attractionToEdit, editedAttraction);
        model.updateFilteredAttractionList(PREDICATE_SHOW_ALL_ATTRACTIONS);
        return new CommandResult(String.format(MESSAGE_EDIT_ATTRACTION_SUCCESS, Messages.format(editedAttraction)));
    }

    /**
     * Creates and returns a {@code Attraction} with the details of
     * {@code attractionToEdit} edited with {@code editAttractionDescriptor}.
     */
    private static Attraction createEditedAttraction(
            Attraction attractionToEdit, EditAttractionDescriptor editAttractionDescriptor) {
        assert attractionToEdit != null;

        Name updatedName = editAttractionDescriptor.getName().orElse(attractionToEdit.getName());
        Priority updatedPriority = editAttractionDescriptor.getPriority().orElse(attractionToEdit.getPriority());
        Contact updatedContact = editAttractionDescriptor.getContact().orElse(attractionToEdit.getContact());
        Address updatedAddress = editAttractionDescriptor.getAddress().orElse(attractionToEdit.getAddress());
        Activities updatedActivities = editAttractionDescriptor
                .getActivities().orElse(attractionToEdit.getActivities());
        Set<Tag> updatedTags = editAttractionDescriptor.getTags().orElse(attractionToEdit.getTags());
        Set<Comment> updatedComments = editAttractionDescriptor.getComments().orElse(attractionToEdit.getComments());

        return new Attraction(
                updatedName, updatedPriority, updatedContact, updatedAddress, updatedActivities,
                updatedTags, updatedComments);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editAttractionDescriptor.equals(otherEditCommand.editAttractionDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editAttractionDescriptor", editAttractionDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the attraction with. Each non-empty field
     * value will replace the corresponding field value of the attraction.
     */
    public static class EditAttractionDescriptor {

        private Name name;
        private Priority priority;
        private Contact contact;
        private Address address;
        private Activities activities;
        private Set<Tag> tags;
        private Set<Comment> comments;

        public EditAttractionDescriptor() {
        }

        /**
         * Copy constructor. A defensive copy of {@code tags} is used
         * internally.
         */
        public EditAttractionDescriptor(EditAttractionDescriptor toCopy) {
            setName(toCopy.name);
            setPriority(toCopy.priority);
            setContact(toCopy.contact);
            setAddress(toCopy.address);
            setActivities(toCopy.activities);
            setTags(toCopy.tags);
            setComments(toCopy.comments);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, priority, contact, address, activities, tags, comments);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        public Optional<Priority> getPriority() {
            return Optional.ofNullable(priority);
        }

        public void setContact(Contact contact) {
            this.contact = contact;
        }

        public Optional<Contact> getContact() {
            return Optional.ofNullable(contact);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setActivities(Activities activities) {
            this.activities = activities;
        }

        public Optional<Activities> getActivities() {
            return Optional.ofNullable(activities);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}. A defensive copy of
         * {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Sets {@code comments} to this object's {@code comments}. A defensive copy of
         * {@code comments} is used internally.
         */
        public void setComments(Set<Comment> comments) {
            this.comments = (comments != null) ? new HashSet<>(comments) : null;
        }



        /**
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException} if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Returns an unmodifiable comment set, which throws
         * {@code UnsupportedOperationException} if modification is attempted.
         * Returns {@code Optional#empty()} if {@code comment} is null.
         */
        public Optional<Set<Comment>> getComments() {
            return (comments != null) ? Optional.of(Collections.unmodifiableSet(comments)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAttractionDescriptor)) {
                return false;
            }

            EditAttractionDescriptor otherEditAttractionDescriptor = (EditAttractionDescriptor) other;
            return Objects.equals(name, otherEditAttractionDescriptor.name)
                    && Objects.equals(priority, otherEditAttractionDescriptor.priority)
                    && Objects.equals(contact, otherEditAttractionDescriptor.contact)
                    && Objects.equals(address, otherEditAttractionDescriptor.address)
                    && Objects.equals(activities, otherEditAttractionDescriptor.activities)
                    && Objects.equals(tags, otherEditAttractionDescriptor.tags)
                    && Objects.equals(comments, otherEditAttractionDescriptor.comments);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("priority", priority)
                    .add("contact", contact)
                    .add("address", address)
                    .add("activities", activities)
                    .add("tags", tags)
                    .add("comments", comments)
                    .toString();
        }
    }
}
