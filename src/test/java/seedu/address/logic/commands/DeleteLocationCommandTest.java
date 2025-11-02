

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLocations.SINGAPORE;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.ActiveTab;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.location.DeleteLocationCommand;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyMaplet;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.location.Location;
import seedu.address.model.location.LocationName;

public class DeleteLocationCommandTest {

    @Test
    public void constructor_nullLocationName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteLocationCommand(null));
    }

    @Test
    public void execute_existingLocation_success() throws Exception {
        ModelStubWithLocation modelStub = new ModelStubWithLocation(Collections.singletonList(SINGAPORE));
        DeleteLocationCommand command = new DeleteLocationCommand(SINGAPORE.getName());

        CommandResult commandResult = command.execute(modelStub);

        assertEquals(String.format(DeleteLocationCommand.MESSAGE_DELETE_LOCATION_SUCCESS, SINGAPORE.getName()),
                commandResult.getFeedbackToUser());
        assertTrue(modelStub.getLocationList().isEmpty());
    }

    @Test
    public void execute_locationDoesNotExist_throwsCommandException() {
        ModelStubWithLocation modelStub = new ModelStubWithLocation(Collections.singletonList(SINGAPORE));
        DeleteLocationCommand command = new DeleteLocationCommand(new LocationName("Sentosa"));

        assertThrows(CommandException.class,
                DeleteLocationCommand.MESSAGE_LOCATION_NOT_FOUND, () -> command.execute(modelStub));
    }

    @Test
    public void equals() {
        DeleteLocationCommand deleteSingapore = new DeleteLocationCommand(new LocationName("Singapore"));
        DeleteLocationCommand deleteSentosa = new DeleteLocationCommand(new LocationName("Sentosa"));

        assertTrue(deleteSingapore.equals(deleteSingapore));
        assertTrue(deleteSingapore.equals(new DeleteLocationCommand(new LocationName("Singapore"))));
        assertFalse(deleteSingapore.equals(deleteSentosa));
        assertFalse(deleteSingapore.equals(null));
        assertFalse(deleteSingapore.equals(1));
    }

    private static class ModelStub implements Model {
        @Override
        public void setActiveTab(ActiveTab activeTab) {
        }

        @Override
        public ActiveTab getActiveTab() {
            return ActiveTab.ATTRACTIONS;
        }
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
        public void setMaplet(ReadOnlyMaplet maplet) {
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
        public boolean isAttractionInAnyItinerary(Attraction attraction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAttraction(Attraction target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAttraction(Attraction attraction) {
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

        @Override
        public void updateSortedAttractionList(Comparator<Attraction> comparator) {
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
        public void addLocation(Location location) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLocation(Location target, Location editedLocation) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isAttractionInAnyLocation(Attraction attraction) {
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
        public void updateSortedItineraryList(Comparator<Itinerary> comparator) {
            throw new AssertionError("This method should not be called.");
        }
    }

    private static class ModelStubWithLocation extends ModelStub {
        private final ObservableList<Location> locations = FXCollections.observableArrayList();

        ModelStubWithLocation(List<Location> locations) {
            this.locations.setAll(locations);
        }

        @Override
        public boolean hasLocationName(LocationName locationName) {
            requireNonNull(locationName);
            return locations.stream().anyMatch(location -> location.getName().equals(locationName));
        }

        @Override
        public void deleteLocation(LocationName locationName) {
            requireNonNull(locationName);
            locations.removeIf(location -> location.getName().equals(locationName));
        }

        @Override
        public ObservableList<Location> getLocationList() {
            return FXCollections.unmodifiableObservableList(locations);
        }
    }
}
