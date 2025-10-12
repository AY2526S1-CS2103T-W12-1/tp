package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAttractionAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ATTRACTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ATTRACTION;
import static seedu.address.testutil.TypicalAttractions.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditAttractionDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.testutil.EditAttractionDescriptorBuilder;
import seedu.address.testutil.AttractionBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Attraction editedAttraction = new AttractionBuilder().build();
        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder(editedAttraction).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ATTRACTION, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ATTRACTION_SUCCESS, Messages.format(editedAttraction));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setAttraction(model.getFilteredAttractionList().get(0), editedAttraction);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastAttraction = Index.fromOneBased(model.getFilteredAttractionList().size());
        Attraction lastAttraction = model.getFilteredAttractionList().get(indexLastAttraction.getZeroBased());

        AttractionBuilder attractionInList = new AttractionBuilder(lastAttraction);
        Attraction editedAttraction = attractionInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastAttraction, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ATTRACTION_SUCCESS, Messages.format(editedAttraction));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setAttraction(lastAttraction, editedAttraction);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ATTRACTION, new EditAttractionDescriptor());
        Attraction editedAttraction = model.getFilteredAttractionList().get(INDEX_FIRST_ATTRACTION.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ATTRACTION_SUCCESS, Messages.format(editedAttraction));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showAttractionAtIndex(model, INDEX_FIRST_ATTRACTION);

        Attraction attractionInFilteredList = model.getFilteredAttractionList().get(INDEX_FIRST_ATTRACTION.getZeroBased());
        Attraction editedAttraction = new AttractionBuilder(attractionInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ATTRACTION,
                new EditAttractionDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ATTRACTION_SUCCESS, Messages.format(editedAttraction));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setAttraction(model.getFilteredAttractionList().get(0), editedAttraction);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAttractionUnfilteredList_failure() {
        Attraction firstAttraction = model.getFilteredAttractionList().get(INDEX_FIRST_ATTRACTION.getZeroBased());
        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder(firstAttraction).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ATTRACTION, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ATTRACTION);
    }

    @Test
    public void execute_duplicateAttractionFilteredList_failure() {
        showAttractionAtIndex(model, INDEX_FIRST_ATTRACTION);

        // edit attraction in filtered list into a duplicate in address book
        Attraction attractionInList = model.getAddressBook().getAttractionList().get(INDEX_SECOND_ATTRACTION.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ATTRACTION,
                new EditAttractionDescriptorBuilder(attractionInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ATTRACTION);
    }

    @Test
    public void execute_invalidAttractionIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAttractionList().size() + 1);
        EditAttractionDescriptor descriptor = new EditAttractionDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidAttractionIndexFilteredList_failure() {
        showAttractionAtIndex(model, INDEX_FIRST_ATTRACTION);
        Index outOfBoundIndex = INDEX_SECOND_ATTRACTION;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getAttractionList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditAttractionDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_ATTRACTION, DESC_AMY);

        // same values -> returns true
        EditAttractionDescriptor copyDescriptor = new EditCommand.EditAttractionDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_ATTRACTION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_ATTRACTION, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_ATTRACTION, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditAttractionDescriptor editAttractionDescriptor = new EditAttractionDescriptor();
        EditCommand editCommand = new EditCommand(index, editAttractionDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editAttractionDescriptor="
                + editAttractionDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
