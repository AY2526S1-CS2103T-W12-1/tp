package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.attraction.Name;
import seedu.address.model.location.Location;

/**
 * An UI component that displays information of a {@code Location}.
 */
public class LocationCard extends UiPart<Region> {

    private static final String FXML = "LocationListCard.fxml";

    public final Location location;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label attractionCount;
    @FXML
    private FlowPane attractionNames;

    /**
     * Creates a {@code LocationCard} with the given {@code Location} and index to display.
     */
    public LocationCard(Location location, int displayedIndex) {
        super(FXML);
        this.location = location;
        id.setText(displayedIndex + ". ");
        name.setText(location.getName().value);
        attractionCount.setText(String.format("Attractions (%d)", location.getAttractionNames().size()));
        attractionNames.getChildren().clear();
        location.getAttractionNames().stream()
                .sorted(Comparator.comparing(attractionName -> attractionName.fullName.toLowerCase()))
                .forEach(this::addAttractionLabel);
    }

    private void addAttractionLabel(Name attractionName) {
        Label attractionLabel = new Label(attractionName.fullName);
        attractionLabel.getStyleClass().add("cell_small_label");
        attractionNames.getChildren().add(attractionLabel);
    }
}
