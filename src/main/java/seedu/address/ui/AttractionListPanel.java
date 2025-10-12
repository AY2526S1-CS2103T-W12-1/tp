package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.attraction.Attraction;

/**
 * Panel containing the list of persons.
 */
public class AttractionListPanel extends UiPart<Region> {
    private static final String FXML = "AttractionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AttractionListPanel.class);

    @FXML
    private ListView<Attraction> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public AttractionListPanel(ObservableList<Attraction> attractionList) {
        super(FXML);
        personListView.setItems(attractionList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Attraction> {
        @Override
        protected void updateItem(Attraction attraction, boolean empty) {
            super.updateItem(attraction, empty);

            if (empty || attraction == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AttractionCard(attraction, getIndex() + 1).getRoot());
            }
        }
    }

}
