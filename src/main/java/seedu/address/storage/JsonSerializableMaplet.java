package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Maplet;
import seedu.address.model.ReadOnlyMaplet;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;

/**
 * An Immutable Maplet that is serializable to JSON format.
 */
@JsonRootName(value = "maplet")
class JsonSerializableMaplet {

    public static final String MESSAGE_DUPLICATE_ATTRACTION = "Attractions list contains duplicate attraction(s).";
    public static final String MESSAGE_DUPLICATE_ITINERARY = "Itinerary list contains duplicate itinerary(ies).";

    private final List<JsonAdaptedAttraction> attractions = new ArrayList<>();
    private final List<JsonAdaptedItinerary> itineraries = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMaplet} with the given attractions.
     */
    @JsonCreator
    public JsonSerializableMaplet(@JsonProperty("attractions") List<JsonAdaptedAttraction> attractions,
            @JsonProperty("itineraries") List<JsonAdaptedItinerary> itineraries) {
        this.attractions.addAll(attractions);
        this.itineraries.addAll(itineraries);

    }

    /**
     * Converts a given {@code ReadOnlyMaplet} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created
     * {@code JsonSerializableMaplet}.
     */
    public JsonSerializableMaplet(ReadOnlyMaplet source) {
        attractions.addAll(source.getAttractionList().stream().map(JsonAdaptedAttraction::new)
                .collect(Collectors.toList()));
        itineraries.addAll(source.getItineraryList().stream().map(JsonAdaptedItinerary::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Maplet into the model's {@code Maplet} object.
     *
     * @throws IllegalValueException if there were any data constraints
     * violated.
     */
    public Maplet toModelType() throws IllegalValueException {
        Maplet maplet = new Maplet();
        for (JsonAdaptedAttraction jsonAdaptedAttraction : attractions) {
            Attraction attraction = jsonAdaptedAttraction.toModelType();
            if (maplet.hasAttraction(attraction)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ATTRACTION);
            }
            maplet.addAttraction(attraction);
        }
        List<Attraction> attractionList = maplet.getAttractionList();
        for (JsonAdaptedItinerary jsonAdaptedItinerary : itineraries) {
            Itinerary itinerary = jsonAdaptedItinerary.toModelType(attractionList);
            if (maplet.hasItinerary(itinerary)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ITINERARY);
            }
            maplet.addItinerary(itinerary);
        }
        return maplet;
    }

}
