package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class DetailedEditCommandTest {

    @Test
    public void constructor_validInputs_success() {
        DetailedEditCommand command = new DetailedEditCommand(Index.fromZeroBased(1));
        assertEquals(DetailedEditCommand.class.getCanonicalName()
                + "{index=seedu.address.commons.core.index.Index{zeroBasedIndex=1}}", command.toString());
    }

    @Test
    public void constructor_nullInputs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DetailedEditCommand(null));
    }
}
