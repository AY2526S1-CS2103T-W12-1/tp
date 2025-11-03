package seedu.address.ui;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPENING_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.tag.Tag;

/**
 * Logic for displaying attraction values in a separate EditWindow
 */
public class EditWindow extends UiPart<Stage> {
    private static final String FXML = "EditWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(CommentWindow.class);

    private Attraction attraction;
    private Index index;
    private int rowIndex;
    private final CommandBox.CommandExecutor commandExecutor;

    @FXML
    private GridPane gridPane;

    @FXML
    private TextField name;

    @FXML
    private TextField priority;

    @FXML
    private TextField contact;

    @FXML
    private TextField address;

    @FXML
    private TextField activities;

    @FXML
    private TextField openTime;

    @FXML
    private TextField price;

    private EditWindow(Stage root, Logic logic, CommandBox.CommandExecutor commandExecutor, Index index) {
        super(FXML, root);
        this.index = index;
        this.attraction = returnAttractionToEdit(logic);
        this.rowIndex = gridPane.getRowCount();
        this.commandExecutor = commandExecutor;
        setTextFields();

        gridPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                saveFields();
            }

            if (event.getCode() == KeyCode.ESCAPE) {
                hide();
            }
        });
    }

    /**
     * Initializes EditWindow with the index field
     * @param logic logicManager component of maplet which we will use to access attractions
     * @param index index to find the right attraction
     */
    public EditWindow(Logic logic, CommandBox.CommandExecutor commandExecutor, Index index) {
        this(new Stage(), logic, commandExecutor, index);
    }

    /**
     * Returns an attraction from the attraction list based on the index
     * @param logic logicManager component
     * @return attraction to be editted
     */
    private Attraction returnAttractionToEdit(Logic logic) {
        List<Attraction> lastShownList = logic.getFilteredAttractionList();
        return lastShownList.get(this.index.getZeroBased());
    }

    /**
     * Set text field values to based on the attributes of the attraction
     */
    private void setTextFields() {
        name.setText(attraction.getName().toString());
        priority.setText(attraction.getPriority().toString());
        contact.setText(attraction.getContact().toString());
        address.setText(attraction.getAddress().toString());
        activities.setText(attraction.getActivities().toString());
        openTime.setText(attraction.getOpeningHours().toString());
        price.setText(attraction.getPrice().toString());

        //process tags
        addSetToTextFields(attraction.getTags(), "Tag");
        //process comments
        addSetToTextFields(attraction.getComments(), "Comment");

    }

    private <T> void addSetToTextFields(Set<T> set, String fieldName) {
        if (!set.isEmpty()) {
            Set<T> newSet = new HashSet<>(set);
            for (T element: newSet) {
                if (element instanceof Tag) {
                    createNewTextFields(fieldName, ((Tag) element).tagName);
                } else {
                    createNewTextFields(fieldName, element.toString());
                }
            }
        }
    }

    /**
     * Creates a next textField with the fieldValue inside
     * @param fieldName name of the fieldValue
     * @param fieldValue the value associated with the fieldName
     */
    private void createNewTextFields(String fieldName, String fieldValue) {
        //add label in column 0 and rowIndex
        Label label = new Label(fieldName);
        GridPane.setConstraints(label, 0, rowIndex);
        gridPane.getChildren().add(label);

        //add textField in column 1 and rowIndex
        TextField textField = new TextField();
        textField.setText(fieldValue);
        textField.getStyleClass().add(fieldName);
        GridPane.setConstraints(textField, 1, rowIndex);
        gridPane.getChildren().add(textField);

        //increment rowIndex after adding
        rowIndex++;
    }


    /**
     * save edited fields into Maplet by calling the edit command with the textField values
     */
    private void saveFields() {
        StringBuilder sb = new StringBuilder();
        sb.append(EditCommand.COMMAND_WORD + " " + index.getOneBased() + " ");

        String name = this.name.getText().trim();
        String priority = this.priority.getText().trim();
        String contact = this.contact.getText().trim();
        String address = this.address.getText().trim();
        String activities = this.activities.getText().trim();
        String openTime = this.openTime.getText().trim();
        String price = this.price.getText().trim();

        sb.append(PREFIX_NAME + name + " ").append(PREFIX_PRIORITY + priority + " ")
                .append(PREFIX_CONTACT + contact + " ").append(PREFIX_ADDRESS + address + " ")
                .append(PREFIX_ACTIVITIES + activities + " ").append(PREFIX_OPENING_HOURS + openTime + " ")
                .append(PREFIX_PRICE + price + " ");

        appendFieldValuesToSb("Tag", sb);
        appendFieldValuesToSb("Comment", sb);

        logger.fine("Fields saved");

        executeEditCommand(sb.toString());
    }

    private void appendFieldValuesToSb(String fieldValue, StringBuilder sb) {
        gridPane.getChildren().stream()
                .filter(node -> node instanceof TextField)
                .map(node -> (TextField) node)
                .filter(field -> field.getStyleClass().contains(fieldValue))
                .forEach(textField -> {
                    if (fieldValue.equals("Tag")) {
                        sb.append(PREFIX_TAG + textField.getText().trim() + " ");
                    } else {
                        sb.append(PREFIX_COMMENT + textField.getText().trim() + " ");
                    }
                });
    }


    private void executeEditCommand(String commandText) {
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            getRoot().close();
        } catch (CommandException | ParseException e) {
            focus();
        }
    }


    /**
     * Shows the edit window.
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
        logger.fine("Showing EditWindow");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the edit window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the edit window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the edit window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
