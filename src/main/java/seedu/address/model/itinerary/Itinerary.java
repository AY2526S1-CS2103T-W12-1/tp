package seedu.address.model.itinerary;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.UniqueAttractionList;

/**
 * Represents an itinerary in the Maplet. Guarantees: details are present and
 * not null, attractions are unique.
 */
public class Itinerary {

    private final ItineraryName name;
    private final LocalDateTime createdAt;
    private final UniqueAttractionList attractions = new UniqueAttractionList();

    /**
     * Every field must be not null.
     */
    public Itinerary(ItineraryName name, LocalDateTime createdAt, List<Attraction> attractions) {
        requireNonNull(name);
        requireNonNull(createdAt);
        requireNonNull(attractions);
        this.name = name;
        this.createdAt = createdAt;
        this.attractions.setAttractions(attractions);
    }

    public ItineraryName getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ObservableList<Attraction> getAttractions() {
        return attractions.asUnmodifiableObservableList();
    }

    public boolean hasAttraction(Attraction attraction) {
        return attractions.contains(attraction);
    }

    public void addAttraction(Attraction attraction) {
        attractions.add(attraction);
    }

    public void removeAttraction(Attraction attraction) {
        attractions.remove(attraction);
    }

    /**
     * Returns true if both itineraries have the same name.
     */
    public boolean isSameItinerary(Itinerary otherItinerary) {
        if (otherItinerary == this) {
            return true;
        }

        return otherItinerary != null
                && otherItinerary.getName().equals(getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Itinerary)) {
            return false;
        }

        Itinerary otherItinerary = (Itinerary) other;
        return name.equals(otherItinerary.name)
                && createdAt.equals(otherItinerary.createdAt)
                && attractions.equals(otherItinerary.attractions);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("createdAt", createdAt)
                .add("attractions", attractions)
                .toString();
    }
}
