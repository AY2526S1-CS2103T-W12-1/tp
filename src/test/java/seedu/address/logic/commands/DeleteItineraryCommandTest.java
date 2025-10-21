package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryName;
import seedu.address.testutil.AttractionBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteItineraryCommand}.
 */
public class DeleteItineraryCommandTest {

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Model model = createModelWithItineraries();
        Itinerary itineraryToDelete = model.getFilteredItineraryList().get(0);
        DeleteItineraryCommand deleteCommand = new DeleteItineraryCommand(Index.fromOneBased(1));

        String expectedMessage = String.format(DeleteItineraryCommand.MESSAGE_DELETE_ITINERARY_SUCCESS,
                itineraryToDelete.getName());

        Model expectedModel = new ModelManager(model.getMaplet(), new UserPrefs());
        expectedModel.deleteItinerary(itineraryToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Model model = createModelWithItineraries();
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredItineraryList().size() + 1);
        DeleteItineraryCommand deleteCommand = new DeleteItineraryCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Model model = createModelWithItineraries();
        Itinerary itineraryToKeep = model.getFilteredItineraryList().get(1);
        model.updateFilteredItineraryList(itinerary -> itinerary.equals(itineraryToKeep));

        Itinerary itineraryToDelete = model.getFilteredItineraryList().get(0);
        DeleteItineraryCommand deleteCommand = new DeleteItineraryCommand(Index.fromOneBased(1));

        String expectedMessage = String.format(DeleteItineraryCommand.MESSAGE_DELETE_ITINERARY_SUCCESS,
                itineraryToDelete.getName());

        Model expectedModel = new ModelManager(model.getMaplet(), new UserPrefs());
        expectedModel.deleteItinerary(itineraryToDelete);
        expectedModel.updateFilteredItineraryList(itinerary -> itinerary.equals(itineraryToKeep));

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        Model model = createModelWithItineraries();
        Itinerary itineraryToKeep = model.getFilteredItineraryList().get(0);
        model.updateFilteredItineraryList(itinerary -> itinerary.equals(itineraryToKeep));

        DeleteItineraryCommand deleteCommand = new DeleteItineraryCommand(Index.fromOneBased(2));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteItineraryCommand deleteFirstCommand = new DeleteItineraryCommand(Index.fromOneBased(1));
        DeleteItineraryCommand deleteSecondCommand = new DeleteItineraryCommand(Index.fromOneBased(2));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteItineraryCommand deleteFirstCommandCopy = new DeleteItineraryCommand(Index.fromOneBased(1));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different itinerary -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteItineraryCommand deleteCommand = new DeleteItineraryCommand(targetIndex);
        String expected = DeleteItineraryCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    private Model createModelWithItineraries() {
        Attraction attractionOne = new AttractionBuilder().withName("Universal Studios").build();
        Attraction attractionTwo = new AttractionBuilder().withName("Gardens by the Bay").build();
        Attraction attractionThree = new AttractionBuilder().withName("Singapore Zoo").build();

        Itinerary firstItinerary = new Itinerary(new ItineraryName("Family Trip"),
                LocalDateTime.of(2024, 10, 21, 10, 0),
                List.of(attractionOne, attractionTwo));
        Itinerary secondItinerary = new Itinerary(new ItineraryName("City Tour"),
                LocalDateTime.of(2024, 10, 22, 9, 0),
                new ArrayList<>(List.of(attractionThree)));

        seedu.address.model.Maplet maplet = new seedu.address.model.Maplet();
        maplet.addAttraction(attractionOne);
        maplet.addAttraction(attractionTwo);
        maplet.addAttraction(attractionThree);
        maplet.addItinerary(firstItinerary);
        maplet.addItinerary(secondItinerary);

        return new ModelManager(maplet, new UserPrefs());
    }
}
