package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.*;
import static seedu.address.testutil.TypicalItineraries.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortCommandTest {
    private Model model = new ModelManager(getTypicalMapletWithItineraries(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getMaplet(), new UserPrefs());

    @Test
    public void equals() {
        SortCommand sortByNameCommand = new SortCommand(
                COMPARATOR_SORT_BY_NAME_ASCENDING, COMPARATOR_SORT_BY_ITINERARY_NAME_ASCENDING);
        SortCommand sortByPriorityCommand = new SortCommand(
                COMPARATOR_SORT_BY_PRIORITY_DESCENDING, COMPARATOR_SORT_BY_ITINERARY_NAME_ASCENDING);

        // same object -> returns true
        assertTrue(sortByNameCommand.equals(sortByNameCommand));

        // same values -> returns true
        assertTrue(sortByNameCommand.equals(new SortCommand(
                COMPARATOR_SORT_BY_NAME_ASCENDING, COMPARATOR_SORT_BY_ITINERARY_NAME_ASCENDING)));

        // different types -> returns false
        assertFalse(sortByNameCommand.equals(1));

        // null -> returns false
        assertFalse(sortByNameCommand.equals(null));

        // different comparator -> returns false
        assertFalse(sortByNameCommand.equals(sortByPriorityCommand));
    }

    @Test
    public void constructor_nullAttractionComparator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(
                null, COMPARATOR_SORT_BY_ITINERARY_NAME_ASCENDING));
    }

    @Test
    public void constructor_nullItineraryComparator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(
                COMPARATOR_SORT_BY_NAME_ASCENDING, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        SortCommand sortCommand = new SortCommand(
                COMPARATOR_SORT_BY_NAME_ASCENDING, COMPARATOR_SORT_BY_ITINERARY_NAME_ASCENDING);
        assertThrows(NullPointerException.class, () -> sortCommand.execute(null));
    }

    @Test
    public void execute_nameAscendingComparator_success() {
        SortCommand sortCommand = new SortCommand(
                COMPARATOR_SORT_BY_NAME_ASCENDING, COMPARATOR_SORT_BY_ITINERARY_NAME_ASCENDING);
        sortCommand.execute(model);
        assertEquals(
                Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredAttractionList());
        assertEquals(
                Arrays.asList(EUROPE_TOUR, JAPAN_TRIP, SINGAPORE_VISIT),
                model.getFilteredItineraryList());
    }

    @Test
    public void execute_priorityDescendingComparator_success() {
        SortCommand sortCommand = new SortCommand(
                COMPARATOR_SORT_BY_PRIORITY_DESCENDING, COMPARATOR_SORT_BY_CUMULATIVE_ITINERARY_PRIORITY_DESCENDING);
        sortCommand.execute(model);
        assertEquals(
                Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredAttractionList());
        assertEquals(
                Arrays.asList(JAPAN_TRIP, EUROPE_TOUR, SINGAPORE_VISIT),
                model.getFilteredItineraryList());
    }


    @Test
    public void execute_priceDescendingComparator_success() {
        SortCommand sortCommand = new SortCommand(
                COMPARATOR_SORT_BY_PRICE_ASCENDING, COMPARATOR_SORT_BY_CUMULATIVE_ITINERARY_PRICE_ASCENDING);
        sortCommand.execute(model);
        assertEquals(
                Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredAttractionList());
        assertEquals(
                Arrays.asList(SINGAPORE_VISIT, EUROPE_TOUR, JAPAN_TRIP),
                model.getFilteredItineraryList());
    }

    @Test
    public void toStringMethod() {
        SortCommand sortCommand = new SortCommand(
                COMPARATOR_SORT_BY_NAME_ASCENDING, COMPARATOR_SORT_BY_ITINERARY_NAME_ASCENDING);
        String expected = SortCommand.class.getCanonicalName()
                + "{attractionComparator=" + COMPARATOR_SORT_BY_NAME_ASCENDING
                + ", itineraryComparator=" + COMPARATOR_SORT_BY_ITINERARY_NAME_ASCENDING + "}";
        assertEquals(expected, sortCommand.toString());
    }
}
