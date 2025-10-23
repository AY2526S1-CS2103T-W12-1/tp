package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditAttractionDescriptorBuilder;

public class EditAttractionDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditAttractionDescriptor descriptorWithSameValues =
                new EditCommand.EditAttractionDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditCommand.EditAttractionDescriptor editedAmy = new EditAttractionDescriptorBuilder(DESC_AMY)
                .withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different priority -> returns false
        editedAmy = new EditAttractionDescriptorBuilder(DESC_AMY).withPriority(VALID_PRIORITY_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditAttractionDescriptorBuilder(DESC_AMY).withContact(VALID_CONTACT_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditAttractionDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditAttractionDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditCommand.EditAttractionDescriptor editAttractionDescriptor = new EditCommand.EditAttractionDescriptor();
        String expected = EditCommand.EditAttractionDescriptor.class.getCanonicalName() + "{name="
                + editAttractionDescriptor.getName().orElse(null) + ", priority="
                + editAttractionDescriptor.getPriority().orElse(null) + ", contact="
                + editAttractionDescriptor.getContact().orElse(null) + ", address="
                + editAttractionDescriptor.getAddress().orElse(null) + ", activities="
                + editAttractionDescriptor.getOpeningHours().orElse(null) + ", opening hours="
                + editAttractionDescriptor.getPrice().orElse(null) + ", price="
                + editAttractionDescriptor.getActivities().orElse(null) + ", tags="
                + editAttractionDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editAttractionDescriptor.toString());
    }
}
