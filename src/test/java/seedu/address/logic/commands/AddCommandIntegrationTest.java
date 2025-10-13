package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAttractions.getTypicalMaplet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.testutil.AttractionBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMaplet(), new UserPrefs());
    }

    @Test
    public void execute_newAttraction_success() {
        Attraction validAttraction = new AttractionBuilder().build();

        Model expectedModel = new ModelManager(model.getMaplet(), new UserPrefs());
        expectedModel.addAttraction(validAttraction);

        assertCommandSuccess(new AddCommand(validAttraction), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validAttraction)),
                expectedModel);
    }

    @Test
    public void execute_duplicateAttraction_throwsCommandException() {
        Attraction attractionInList = model.getMaplet().getAttractionList().get(0);
        assertCommandFailure(new AddCommand(attractionInList), model,
                AddCommand.MESSAGE_DUPLICATE_ATTRACTION);
    }

}
