package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryName;

/**
 * A utility class to help with building Itinerary objects.
 */
public class ItineraryBuilder {

    public static final String DEFAULT_NAME = "Japan Trip";
    public static final LocalDateTime DEFAULT_CREATED_AT = LocalDateTime.of(2024, 10, 21, 10, 0);

    private ItineraryName name;
    private LocalDateTime createdAt;
    private List<Attraction> attractions;

    /**
     * Creates an {@code ItineraryBuilder} with the default details.
     */
    public ItineraryBuilder() {
        name = new ItineraryName(DEFAULT_NAME);
        createdAt = DEFAULT_CREATED_AT;
        attractions = new ArrayList<>();
    }

    /**
     * Initializes the ItineraryBuilder with the data of {@code itineraryToCopy}.
     */
    public ItineraryBuilder(Itinerary itineraryToCopy) {
        name = itineraryToCopy.getName();
        createdAt = itineraryToCopy.getCreatedAt();
        attractions = new ArrayList<>(itineraryToCopy.getAttractions());
    }

    /**
     * Sets the {@code ItineraryName} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withName(String name) {
        this.name = new ItineraryName(name);
        return this;
    }

    /**
     * Sets the {@code createdAt} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Sets the {@code attractions} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withAttractions(Attraction... attractions) {
        this.attractions = new ArrayList<>(Arrays.asList(attractions));
        return this;
    }

    /**
     * Sets the {@code attractions} of the {@code Itinerary} that we are building.
     */
    public ItineraryBuilder withAttractions(List<Attraction> attractions) {
        this.attractions = new ArrayList<>(attractions);
        return this;
    }

    public Itinerary build() {
        return new Itinerary(name, createdAt, attractions);
    }
}
