package seedu.address.ui;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.itinerary.Itinerary;

/**
 * An UI component that displays information of a {@code Itinerary}.
 */
public class ItineraryCard extends UiPart<Region> {

    private static final String FXML = "ItineraryListCard.fxml";
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    public final Itinerary itinerary;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label createdAt;
    @FXML
    private Label attractionCount;
    @FXML
    private FlowPane attractionNames;

    /**
     * Creates a {@code ItineraryCard} with the given {@code Itinerary} and
     * index to display.
     */
    public ItineraryCard(Itinerary itinerary, int displayedIndex) {
        super(FXML);
        this.itinerary = itinerary;
        id.setText(displayedIndex + ". ");
        name.setText(itinerary.getName().fullName);
        createdAt.setText("Created: " + itinerary.getCreatedAt().format(DATE_TIME_FORMATTER));
        int numberOfAttractions = itinerary.getAttractions().size();
        attractionCount.setText("Stops: " + numberOfAttractions);
        itinerary.getAttractions()
                .forEach(attraction -> attractionNames.getChildren()
                .add(new Label(attraction.getName().fullName)));
    }
}
