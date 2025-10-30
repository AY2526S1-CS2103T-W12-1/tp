package seedu.address.ui;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.location.Location;

/**
 * Panel containing the list of locations.
 */
public class LocationListPanel extends UiPart<Region> {
    private static final String FXML = "LocationListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(LocationListPanel.class);

    @FXML
    private ListView<Location> locationListView;

    private final Consumer<Location> selectionHandler;

    /**
     * Creates a {@code LocationListPanel} that displays the given list of locations and
     * notifies the given {@code selectionHandler} whenever the user selects a location.
     *
     * @param locationList     list of locations to show in the panel
     * @param selectionHandler callback to run when a location is selected; receives the selected location
     */
    public LocationListPanel(ObservableList<Location> locationList, Consumer<Location> selectionHandler) {
        super(FXML);
        locationListView.setItems(locationList);
        this.selectionHandler = Objects.requireNonNull(selectionHandler);
        locationListView.setCellFactory(listView -> new LocationListViewCell());
        locationListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue)
                    -> this.selectionHandler.accept(newValue));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Location} using a {@code LocationCard}.
     */
    class LocationListViewCell extends ListCell<Location> {
        @Override
        protected void updateItem(Location location, boolean empty) {
            super.updateItem(location, empty);

            if (empty || location == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LocationCard(location, getIndex() + 1).getRoot());
            }
        }
    }
}
