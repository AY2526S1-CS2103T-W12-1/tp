package seedu.address.ui;

import java.util.Set;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.attraction.Comment;

/**
 * Controller for the comments window displayed when clicking view comments
 */
public class CommentWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(CommentWindow.class);
    private static final String FXML = "CommentWindow.fxml";

    @FXML
    private VBox commentWindow;

    @FXML
    private Scene windowScene;

    private Set<Comment> comments;

    /**
     * Creates a new CommentWindow.
     *
     * @param root Stage to use as the root of the CommentWindow.
     */
    public CommentWindow(Stage root) {
        super(FXML, root);
    }
    /**
     * Creates a new CommentWindow.
     */
    public CommentWindow(Set<Comment> comments) {
        this(new Stage());
        this.comments = comments;
        addCommentsToWindow();

        //Initialize commentWindow to close on ESCAPE keypress.
        this.windowScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                hide();
            }
        });
    }

    private void addCommentsToWindow() {
        for (Comment comment : comments) {
            Label commentLabel = new Label("Comment: " + comment.comment);
            commentLabel.getStyleClass().add("cell_big_label");
            commentLabel.wrapTextProperty().setValue(true);

            assert commentWindow != null;
            commentWindow.getChildren().add(commentLabel);
        }
    }

    /**
     * Shows the comments window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing comments about an attraction");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the commemt window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the comment window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the comment window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
