package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.location.Location;
import seedu.address.model.location.LocationName;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Attraction> PREDICATE_SHOW_ALL_ATTRACTIONS = unused -> true;
    Predicate<Itinerary> PREDICATE_SHOW_ALL_ITINERARIES = unused -> true;

    /** {@code Comparator} that sorts attractions by name in ascending order */
    Comparator<Attraction> COMPARATOR_SORT_BY_NAME_ASCENDING = (attr1, attr2) ->
            attr1.getName().fullName.compareToIgnoreCase(attr2.getName().fullName);
    Comparator<Attraction> COMPARATOR_SORT_BY_PRIORITY_DESCENDING = (attr1, attr2) ->
            Integer.compare(Integer.parseInt(attr2.getPriority().value), Integer.parseInt(attr1.getPriority().value));
    Comparator<Attraction> COMPARATOR_SORT_BY_CONTACT_ASCENDING = (attr1, attr2) ->
            attr1.getContact().value.compareToIgnoreCase(attr2.getContact().value);
    Comparator<Attraction> COMPARATOR_SORT_BY_ADDRESS_ASCENDING = (attr1, attr2) ->
            attr1.getAddress().value.compareToIgnoreCase(attr2.getAddress().value);

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' Maplet file path.
     */
    Path getMapletFilePath();

    /**
     * Sets the user prefs' Maplet data file path.
     */
    void setMapletFilePath(Path mapletFilePath);

    /**
     * Replaces Maplet data with the data in {@code maplet}.
     */
    void setMaplet(ReadOnlyMaplet maplet);

    /** Returns the Maplet */
    ReadOnlyMaplet getMaplet();

    /**
     * Returns true if an attraction with the same identity as {@code attraction} exists in the Maplet.
     */
    boolean hasAttraction(Attraction attraction);

    /**
     * Returns true if an attraction with the same identity as {@code location} exists in the Maplet.
     */
    boolean hasLocation(Location location);

    /**
     * Returns true if a location with the same name as {@code locationName} exists in the Maplet.
     */
    boolean hasLocationName(LocationName locationName);

    /**
     * Deletes the given attraction.
     * The attraction must exist in the Maplet.
     */
    void deleteAttraction(Attraction target);

    /**
     * Deletes the location with {@code locationName}.
     * The location must exist in the Maplet.
     */
    void deleteLocation(LocationName locationName);

    /**
     * Adds the given attraction.
     * {@code attraction} must not already exist in the Maplet.
     */
    void addAttraction(Attraction attraction);

    /**
     * Adds the given location.
     * {@code location} must not already exist in the Maplet.
     */
    void addLocation(Location location);

    /**
     * Replaces the given location {@code target} with {@code editedLocation}.
     * {@code target} must exist in the Maplet.
     */
    void setLocation(Location target, Location editedLocation);

    /**
     * Replaces the given attraction {@code target} with {@code editedAttraction}.
     * {@code target} must exist in the Maplet.
     * The attraction identity of {@code editedAttraction} must not be the same as another existing attraction in the
     * Maplet.
     */
    void setAttraction(Attraction target, Attraction editedAttraction);

    /** Returns an unmodifiable view of the filtered attraction list */
    ObservableList<Attraction> getFilteredAttractionList();

    /** Returns an unmodifiable view of the locations list */
    ObservableList<Location> getLocationList();

    /**
     * Updates the filter of the filtered attraction list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAttractionList(Predicate<Attraction> predicate);

    /**
     * Updates the sorted attraction list using the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedAttractionList(Comparator<Attraction> comparator);

    boolean hasItinerary(Itinerary itinerary);

    void deleteItinerary(Itinerary itinerary);

    void addItinerary(Itinerary itinerary);

    void setItinerary(Itinerary target, Itinerary editedItinerary);

    ObservableList<Itinerary> getFilteredItineraryList();

    void updateFilteredItineraryList(Predicate<Itinerary> predicate);
}
