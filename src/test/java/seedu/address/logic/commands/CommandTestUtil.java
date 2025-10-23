package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION_ATTRACTION_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Maplet;
import seedu.address.model.Model;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditAttractionDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PRIORITY_AMY = "1";
    public static final String VALID_PRIORITY_BOB = "2";
    public static final String VALID_CONTACT_AMY = "amy@example.com";
    public static final String VALID_CONTACT_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_ACTIVITIES_AMY = "Sightseeing";
    public static final String VALID_ACTIVITIES_BOB = "See temple";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_LOCATION_NAME_SINGAPORE = "Singapore";
    public static final String VALID_LOCATION_NAME_SENTOSA = "Sentosa";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PRIORITY_DESC_AMY = " " + PREFIX_PRIORITY + VALID_PRIORITY_AMY;
    public static final String PRIORITY_DESC_BOB = " " + PREFIX_PRIORITY + VALID_PRIORITY_BOB;
    public static final String CONTACT_DESC_AMY = " " + PREFIX_CONTACT + VALID_CONTACT_AMY;
    public static final String CONTACT_DESC_BOB = " " + PREFIX_CONTACT + VALID_CONTACT_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String ACTIVITIES_DESC_AMY = " " + PREFIX_ACTIVITIES + VALID_ACTIVITIES_AMY;
    public static final String ACTIVITIES_DESC_BOB = " " + PREFIX_ACTIVITIES + VALID_ACTIVITIES_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String LOCATION_NAME_DESC_SINGAPORE = " "
            + PREFIX_LOCATION_NAME + VALID_LOCATION_NAME_SINGAPORE;
    public static final String LOCATION_NAME_DESC_SENTOSA = " " + PREFIX_LOCATION_NAME + VALID_LOCATION_NAME_SENTOSA;
    public static final String LOCATION_ATTRACTION_INDEX_DESC_FIRST =
            " " + PREFIX_LOCATION_ATTRACTION_INDEX + "1";
    public static final String LOCATION_ATTRACTION_INDEX_DESC_SECOND =
            " " + PREFIX_LOCATION_ATTRACTION_INDEX + "2";

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY + "a"; // 'a' not allowed in priority
    // outside allowed 1-10 range
    public static final String INVALID_PRIORITY_OUT_OF_RANGE_DESC =
            " " + PREFIX_PRIORITY + "11";
    public static final String INVALID_CONTACT_DESC = " " + PREFIX_CONTACT + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_ACTIVITIES_DESC = " " + PREFIX_ACTIVITIES + " "; //no empty string activities
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_LOCATION_NAME_DESC = " " + PREFIX_LOCATION_NAME + "Sentosa&"; // '&' not allowed

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditAttractionDescriptor DESC_AMY;
    public static final EditCommand.EditAttractionDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditAttractionDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPriority(VALID_PRIORITY_AMY).withContact(VALID_CONTACT_AMY).withAddress(VALID_ADDRESS_AMY)
                .withActivities(VALID_ACTIVITIES_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditAttractionDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPriority(VALID_PRIORITY_BOB).withContact(VALID_CONTACT_BOB).withAddress(VALID_ADDRESS_BOB)
                .withActivities(VALID_ACTIVITIES_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches
     * {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to
     * {@link #assertCommandSuccess(Command, Model, CommandResult, Model)} that
     * takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the Maplet, filtered attraction list and selected attraction in
     * {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Maplet expectedMaplet = new Maplet(actualModel.getMaplet());
        List<Attraction> expectedFilteredList = new ArrayList<>(actualModel.getFilteredAttractionList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedMaplet, actualModel.getMaplet());
        assertEquals(expectedFilteredList, actualModel.getFilteredAttractionList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the attraction at the
     * given {@code targetIndex} in the {@code model}'s Maplet.
     */
    public static void showAttractionAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAttractionList().size());

        Attraction attraction = model.getFilteredAttractionList().get(targetIndex.getZeroBased());
        final String[] splitName = attraction.getName().fullName.split("\\s+");
        model.updateFilteredAttractionList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredAttractionList().size());
    }

}
