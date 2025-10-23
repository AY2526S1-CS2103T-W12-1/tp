package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.location.Location;

/**
 * Unmodifiable view of a Maplet.
 */
public interface ReadOnlyMaplet {

    /**
     * Returns an unmodifiable view of the attractions list.
     * This list will not contain any duplicate attractions.
     */
    ObservableList<Attraction> getAttractionList();

    /**
     * Returns an unmodifiable view of the itineraries list.
     * This list will not contain any duplicate itineraries.
     */
    ObservableList<Itinerary> getItineraryList();

    /**
     * Returns an unmodifiable view of the locations list.
     * This list will not contain any duplicate locations.
     */
    ObservableList<Location> getLocationList();

}
