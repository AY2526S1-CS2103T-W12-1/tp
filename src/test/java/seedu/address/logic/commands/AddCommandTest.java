package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Maplet;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyMaplet;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.location.Location;
import seedu.address.model.location.LocationName;
import seedu.address.testutil.AttractionBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_attractionAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAttractionAdded modelStub = new ModelStubAcceptingAttractionAdded();
        Attraction validAttraction = new AttractionBuilder().build();

        CommandResult commandResult = new AddCommand(validAttraction).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validAttraction)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAttraction), modelStub.attractionsAdded);
    }

    @Test
    public void execute_duplicateAttraction_throwsCommandException() {
        Attraction validAttraction = new AttractionBuilder().build();
        AddCommand addCommand = new AddCommand(validAttraction);
        ModelStub modelStub = new ModelStubWithAttraction(validAttraction);

        assertThrows(
                CommandException.class, AddCommand.MESSAGE_DUPLICATE_ATTRACTION, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Attraction alice = new AttractionBuilder().withName("Alice").build();
        Attraction bob = new AttractionBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different attraction -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getMapletFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMapletFilePath(Path mapletFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAttraction(Attraction attraction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addLocation(Location location) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLocation(Location target, Location editedLocation) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMaplet(ReadOnlyMaplet newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMaplet getMaplet() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAttraction(Attraction attraction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLocation(Location location) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLocationName(LocationName locationName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAttraction(Attraction target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteLocation(LocationName locationName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAttraction(Attraction target, Attraction editedAttraction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Attraction> getFilteredAttractionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Location> getLocationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAttractionList(Predicate<Attraction> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedAttractionList(Comparator<Attraction> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasItinerary(seedu.address.model.itinerary.Itinerary itinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteItinerary(seedu.address.model.itinerary.Itinerary itinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addItinerary(seedu.address.model.itinerary.Itinerary itinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItinerary(seedu.address.model.itinerary.Itinerary target,
                seedu.address.model.itinerary.Itinerary editedItinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<seedu.address.model.itinerary.Itinerary> getFilteredItineraryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredItineraryList(Predicate<seedu.address.model.itinerary.Itinerary> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single attraction.
     */
    private class ModelStubWithAttraction extends ModelStub {
        private final Attraction attraction;

        ModelStubWithAttraction(Attraction attraction) {
            requireNonNull(attraction);
            this.attraction = attraction;
        }

        @Override
        public boolean hasAttraction(Attraction attraction) {
            requireNonNull(attraction);
            return this.attraction.isSameAttraction(attraction);
        }
    }

    /**
     * A Model stub that always accept the attraction being added.
     */
    private class ModelStubAcceptingAttractionAdded extends ModelStub {
        final ArrayList<Attraction> attractionsAdded = new ArrayList<>();

        @Override
        public boolean hasAttraction(Attraction attraction) {
            requireNonNull(attraction);
            return attractionsAdded.stream().anyMatch(attraction::isSameAttraction);
        }

        @Override
        public void addAttraction(Attraction attraction) {
            requireNonNull(attraction);
            attractionsAdded.add(attraction);
        }

        @Override
        public ReadOnlyMaplet getMaplet() {
            return new Maplet();
        }
    }

}
