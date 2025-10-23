package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.attraction.Attraction;


/**
 * An UI component that displays information of a {@code Attraction}.
 */
public class AttractionCard extends UiPart<Region> {

    private static final String FXML = "AttractionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Maplet level 4</a>
     */

    public final Attraction attraction;

    private final CommentWindow commentWindow;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label priority;
    @FXML
    private Label address;
    @FXML
    private Label contact;
    @FXML
    private Label activities;
    @FXML
    private FlowPane tags;
    @FXML
    private Button commentButton;


    /**
     * Creates a {@code AttractionCode} with the given {@code Attraction} and index to display.
     */
    public AttractionCard(Attraction attraction, int displayedIndex) {
        super(FXML);
        this.attraction = attraction;
        id.setText(displayedIndex + ". ");
        name.setText(attraction.getName().fullName);
        priority.setText("Priority: " + attraction.getPriority().value);
        address.setText(attraction.getAddress().value);
        contact.setText(attraction.getContact().value);
        activities.setText(attraction.getActivities().activities);
        attraction.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        commentWindow = new CommentWindow(attraction.getComments());
    }

    /**
     * function is called when the comments button is clicked
     */
    @FXML
    public void openCommentWindow() {
        if (!commentWindow.isShowing()) {
            commentWindow.show();
        } else {
            commentWindow.focus();
        }
    }

}
