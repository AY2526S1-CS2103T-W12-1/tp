package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.COMPARATOR_SORT_BY_NAME_ASCENDING;
import static seedu.address.model.Model.COMPARATOR_SORT_BY_PRICE_DESCENDING;
import static seedu.address.model.Model.COMPARATOR_SORT_BY_PRIORITY_DESCENDING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.ALICE;
import static seedu.address.testutil.TypicalAttractions.BENSON;
import static seedu.address.testutil.TypicalAttractions.CARL;
import static seedu.address.testutil.TypicalAttractions.DANIEL;
import static seedu.address.testutil.TypicalAttractions.ELLE;
import static seedu.address.testutil.TypicalAttractions.FIONA;
import static seedu.address.testutil.TypicalAttractions.GEORGE;
import static seedu.address.testutil.TypicalAttractions.getTypicalMaplet;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortCommandTest {
    private Model model = new ModelManager(getTypicalMaplet(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getMaplet(), new UserPrefs());

    @Test
    public void equals() {
        SortCommand sortByNameCommand = new SortCommand(COMPARATOR_SORT_BY_NAME_ASCENDING);
        SortCommand sortByPriorityCommand = new SortCommand(COMPARATOR_SORT_BY_PRIORITY_DESCENDING);

        // same object -> returns true
        assertTrue(sortByNameCommand.equals(sortByNameCommand));

        // same values -> returns true
        assertTrue(sortByNameCommand.equals(new SortCommand(COMPARATOR_SORT_BY_NAME_ASCENDING)));

        // different types -> returns false
        assertFalse(sortByNameCommand.equals(1));

        // null -> returns false
        assertFalse(sortByNameCommand.equals(null));

        // different comparator -> returns false
        assertFalse(sortByNameCommand.equals(sortByPriorityCommand));
    }

    @Test
    public void constructor_nullComparator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        SortCommand sortCommand = new SortCommand(COMPARATOR_SORT_BY_NAME_ASCENDING);
        assertThrows(NullPointerException.class, () -> sortCommand.execute(null));
    }

    @Test
    public void execute_nameAscendingComparator_success() {
        SortCommand sortCommand = new SortCommand(COMPARATOR_SORT_BY_NAME_ASCENDING);
        sortCommand.execute(model);
        assertEquals(
                Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredAttractionList());
    }

    @Test
    public void execute_priorityDescendingComparator_success() {
        SortCommand sortCommand = new SortCommand(COMPARATOR_SORT_BY_PRIORITY_DESCENDING);
        sortCommand.execute(model);
        assertEquals(
                Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredAttractionList());
    }


    @Test
    public void execute_priceDescendingComparator_success() {
        SortCommand sortCommand = new SortCommand(COMPARATOR_SORT_BY_PRICE_DESCENDING);
        sortCommand.execute(model);
        assertEquals(
                Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredAttractionList());
    }

    @Test
    public void toStringMethod() {
        SortCommand sortCommand = new SortCommand(COMPARATOR_SORT_BY_NAME_ASCENDING);
        String expected = SortCommand.class.getCanonicalName()
                + "{comparator=" + COMPARATOR_SORT_BY_NAME_ASCENDING + "}";
        assertEquals(expected, sortCommand.toString());
    }
}
