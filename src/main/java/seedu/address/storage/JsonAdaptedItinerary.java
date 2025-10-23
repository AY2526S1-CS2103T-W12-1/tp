package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryName;

/**
 * Jackson-friendly version of {@link Itinerary}.
 */
class JsonAdaptedItinerary {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Itinerary's %s field is missing!";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private final String name;
    private final String createdAt;
    private final List<String> attractionNames = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedItinerary(@JsonProperty("name") String name,
            @JsonProperty("createdAt") String createdAt,
            @JsonProperty("attractions") List<String> attractionNames) {
        this.name = name;
        this.createdAt = createdAt;
        if (attractionNames != null) {
            this.attractionNames.addAll(attractionNames);
        }
    }

    public JsonAdaptedItinerary(Itinerary itinerary) {
        name = itinerary.getName().fullName;
        createdAt = itinerary.getCreatedAt().format(DATE_TIME_FORMATTER);
        attractionNames.addAll(itinerary.getAttractions().stream()
                .map(attraction -> attraction.getName().fullName)
                .collect(Collectors.toList()));
    }

    public Itinerary toModelType(List<Attraction> availableAttractions) throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ItineraryName.class.getSimpleName()));
        }

        final ItineraryName modelName = new ItineraryName(name);

        if (createdAt == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "createdAt"));
        }
        final LocalDateTime modelCreatedAt;
        try {
            modelCreatedAt = LocalDateTime.parse(createdAt, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException("Invalid itinerary timestamp: " + createdAt);
        }

        List<Attraction> matchedAttractions = new ArrayList<>();
        for (String attractionName : attractionNames) {
            matchedAttractions.add(findAttractionByName(attractionName, availableAttractions));
        }

        return new Itinerary(modelName, modelCreatedAt, matchedAttractions);
    }

    private Attraction findAttractionByName(String attractionName, List<Attraction> availableAttractions)
            throws IllegalValueException {
        return availableAttractions.stream()
                .filter(attraction -> attraction.getName().fullName.equals(attractionName))
                .findFirst()
                .orElseThrow(() -> new IllegalValueException(
                "Itinerary references missing attraction: " + attractionName));
    }
}
