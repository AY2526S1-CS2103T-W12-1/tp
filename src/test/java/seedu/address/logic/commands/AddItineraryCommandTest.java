package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.ActiveTab;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyMaplet;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryName;
import seedu.address.model.location.Location;
import seedu.address.model.location.LocationName;
import seedu.address.testutil.AttractionBuilder;

public class AddItineraryCommandTest {

    private static final ItineraryName VALID_ITINERARY_NAME = new ItineraryName("Family Trip");

    @Test
    public void execute_itineraryAcceptedByModel_addSuccessful() throws Exception {
        Attraction attractionOne = new AttractionBuilder().withName("Universal Studios").build();
        Attraction attractionTwo = new AttractionBuilder().withName("Gardens by the Bay").build();
        ModelStubAcceptingItineraryAdded modelStub =
                new ModelStubAcceptingItineraryAdded(List.of(attractionOne, attractionTwo));

        List<Index> attractionIndexes = Arrays.asList(Index.fromOneBased(1), Index.fromOneBased(2));
        AddItineraryCommand command = new AddItineraryCommand(VALID_ITINERARY_NAME, attractionIndexes);
        CommandResult commandResult = command.execute(modelStub);

        assertEquals(String.format(AddItineraryCommand.MESSAGE_SUCCESS, VALID_ITINERARY_NAME),
                commandResult.getFeedbackToUser());
        assertEquals(1, modelStub.itinerariesAdded.size());
        Itinerary addedItinerary = modelStub.itinerariesAdded.get(0);
        assertEquals(VALID_ITINERARY_NAME, addedItinerary.getName());
        assertEquals(Arrays.asList(attractionOne, attractionTwo), new ArrayList<>(addedItinerary.getAttractions()));
        assertFalse(addedItinerary.getCreatedAt().isAfter(LocalDateTime.now()));
    }

    @Test
    public void execute_invalidAttractionIndex_throwsCommandException() {
        Attraction attraction = new AttractionBuilder().withName("Universal Studios").build();
        ModelStubAcceptingItineraryAdded modelStub =
                new ModelStubAcceptingItineraryAdded(List.of(attraction));

        AddItineraryCommand command = new AddItineraryCommand(VALID_ITINERARY_NAME,
                List.of(Index.fromOneBased(2)));

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX, ()
                -> command.execute(modelStub));
        assertTrue(modelStub.itinerariesAdded.isEmpty());
    }

    @Test
    public void execute_duplicateItinerary_throwsCommandException() {
        Attraction attraction = new AttractionBuilder().withName("Universal Studios").build();
        ModelStubWithItinerary modelStub = new ModelStubWithItinerary(attraction);

        List<Index> attractionIndexes = List.of(Index.fromOneBased(1));
        AddItineraryCommand command = new AddItineraryCommand(VALID_ITINERARY_NAME, attractionIndexes);

        assertThrows(CommandException.class,
                AddItineraryCommand.MESSAGE_DUPLICATE_ITINERARY, () -> command.execute(modelStub));
    }

    @Test
    public void execute_duplicateAttractionReference_throwsCommandException() {
        Attraction attraction = new AttractionBuilder().withName("Universal Studios").build();
        ModelStubAcceptingItineraryAdded modelStub =
                new ModelStubAcceptingItineraryAdded(List.of(attraction));

        List<Index> attractionIndexes = Arrays.asList(Index.fromOneBased(1), Index.fromOneBased(1));
        AddItineraryCommand command = new AddItineraryCommand(VALID_ITINERARY_NAME, attractionIndexes);

        assertThrows(CommandException.class,
                AddItineraryCommand.MESSAGE_DUPLICATE_ATTRACTION_REFERENCE, () -> command.execute(modelStub));
    }

    @Test
    public void equals() {
        List<Index> firstIndexes = List.of(Index.fromOneBased(1));
        List<Index> secondIndexes = List.of(Index.fromOneBased(1), Index.fromOneBased(2));

        AddItineraryCommand firstCommand = new AddItineraryCommand(new ItineraryName("Trip"), firstIndexes);
        AddItineraryCommand secondCommand = new AddItineraryCommand(new ItineraryName("Holiday"), secondIndexes);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        AddItineraryCommand firstCommandCopy = new AddItineraryCommand(new ItineraryName("Trip"), firstIndexes);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different values -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void hashCode_sameValues_sameHash() {
        List<Index> indexes = List.of(Index.fromOneBased(1));
        AddItineraryCommand command1 = new AddItineraryCommand(VALID_ITINERARY_NAME, indexes);
        AddItineraryCommand command2 = new AddItineraryCommand(VALID_ITINERARY_NAME, indexes);
        assertEquals(command1.hashCode(), command2.hashCode());
    }

    @Test
    public void toStringMethod() {
        List<Index> indexes = List.of(Index.fromOneBased(1));
        AddItineraryCommand command = new AddItineraryCommand(VALID_ITINERARY_NAME, indexes);
        String expected = AddItineraryCommand.class.getCanonicalName()
                + "{itineraryName=" + VALID_ITINERARY_NAME
                + ", attractionIndexes=" + indexes + "}";
        assertEquals(expected, command.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private static class ModelStub implements Model {
        @Override
        public void setActiveTab(ActiveTab activeTab) {}

        @Override
        public ActiveTab getActiveTab() { return ActiveTab.ATTRACTIONS; }

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
        public void deleteAttraction(Attraction target) {
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
        public void updateFilteredAttractionList(Predicate<Attraction> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        //===Location====
        @Override
        public boolean hasLocation(Location location) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLocationName(LocationName locationName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isAttractionInAnyItinerary(Attraction attraction) {
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
        public ObservableList<Location> getLocationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteLocation(LocationName locationName) {
            throw new AssertionError("This method should not be called.");
        }

        //===Itinerary===
        @Override
        public void updateSortedAttractionList(java.util.Comparator<Attraction> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasItinerary(Itinerary itinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteItinerary(Itinerary itinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addItinerary(Itinerary itinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isAttractionInAnyLocation(Attraction attraction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItinerary(Itinerary target, Itinerary editedItinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Itinerary> getFilteredItineraryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredItineraryList(Predicate<Itinerary> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedItineraryList(java.util.Comparator<Itinerary> comparator) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that already contains an itinerary with the same name.
     */
    private static class ModelStubWithItinerary extends ModelStub {

        private final Itinerary itinerary;
        private final ObservableList<Attraction> attractions = FXCollections.observableArrayList();

        ModelStubWithItinerary(Attraction attraction) {
            requireNonNull(attraction);
            attractions.add(attraction);
            this.itinerary = new Itinerary(VALID_ITINERARY_NAME, LocalDateTime.now(), List.of(attraction));
        }

        @Override
        public boolean hasItinerary(Itinerary itinerary) {
            requireNonNull(itinerary);
            return this.itinerary.isSameItinerary(itinerary);
        }

        @Override
        public ObservableList<Attraction> getFilteredAttractionList() {
            return attractions;
        }

        @Override
        public void updateFilteredItineraryList(Predicate<Itinerary> predicate) {
            // no-op for testing
        }
    }

    /**
     * A Model stub that stores added itineraries.
     */
    private static class ModelStubAcceptingItineraryAdded extends ModelStub {

        final List<Itinerary> itinerariesAdded = new ArrayList<>();
        final ObservableList<Attraction> attractions = FXCollections.observableArrayList();

        ModelStubAcceptingItineraryAdded(List<Attraction> attractionList) {
            requireNonNull(attractionList);
            attractions.setAll(attractionList);
        }

        @Override
        public boolean hasItinerary(Itinerary itinerary) {
            requireNonNull(itinerary);
            return itinerariesAdded.stream().anyMatch(existing -> existing.isSameItinerary(itinerary));
        }

        @Override
        public void addItinerary(Itinerary itinerary) {
            requireNonNull(itinerary);
            itinerariesAdded.add(itinerary);
        }

        @Override
        public ObservableList<Attraction> getFilteredAttractionList() {
            return attractions;
        }

        @Override
        public void updateFilteredItineraryList(Predicate<Itinerary> predicate) {
            // no-op for testing
        }
    }
}
