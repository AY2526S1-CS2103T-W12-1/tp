package seedu.address.ui;

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

    @FXML
    private ListView<Itinerary> itineraryListView;

    /**
     * Creates a {@code ItineraryListPanel} with the given {@code ObservableList}.
     */
    public ItineraryListPanel(ObservableList<Itinerary> itineraryList) {
        super(FXML);
        itineraryListView.setItems(itineraryList);
        itineraryListView.setCellFactory(listView -> new ItineraryListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Itinerary} using an {@code ItineraryCard}.
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
