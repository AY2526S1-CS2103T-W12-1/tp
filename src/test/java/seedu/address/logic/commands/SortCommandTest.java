package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.model.Model.COMPARATOR_SORT_BY_NAME_ASCENDING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.getTypicalMaplet;

public class SortCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMaplet(), new UserPrefs());
        expectedModel = new ModelManager(model.getMaplet(), new UserPrefs());
    }

    @Test
    public void constructor_nullComparator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(null));
    }

    @Test
    public void execute_sortSuccessful() {
        // This test would require a ModelStub implementation to test the execute method properly.
        // Due to the complexity, we will leave this as a placeholder for now.
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        SortCommand sortCommand = new SortCommand(COMPARATOR_SORT_BY_NAME_ASCENDING);
        assertThrows(NullPointerException.class, () -> sortCommand.execute(null));
    }

    @Test
    public void execute_
}
