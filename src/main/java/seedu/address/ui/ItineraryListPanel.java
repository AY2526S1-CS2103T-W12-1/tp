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
import seedu.address.model.itinerary.Itinerary;

/**
 * Panel containing the list of itineraries.
 */
public class ItineraryListPanel extends UiPart<Region> {

    private static final String FXML = "ItineraryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ItineraryListPanel.class);
    private final Consumer<Itinerary> selectionHandler;

    @FXML
    private ListView<Itinerary> itineraryListView;

    /**
     * Creates a {@code ItineraryListPanel} with the given
     * {@code ObservableList}.
     */
    public ItineraryListPanel(ObservableList<Itinerary> itineraryList, Consumer<Itinerary> selectionHandler) {
        super(FXML);
        this.selectionHandler = Objects.requireNonNull(selectionHandler);
        itineraryListView.setItems(itineraryList);
        itineraryListView.setCellFactory(listView -> new ItineraryListViewCell());
        itineraryListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> this.selectionHandler.accept(newValue));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an
     * {@code Itinerary} using an {@code ItineraryCard}.
     */
    class ItineraryListViewCell extends ListCell<Itinerary> {

        @Override
        protected void updateItem(Itinerary itinerary, boolean empty) {
            super.updateItem(itinerary, empty);

            if (empty || itinerary == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ItineraryCard(itinerary, getIndex() + 1).getRoot());
            }
        }
    }
}
