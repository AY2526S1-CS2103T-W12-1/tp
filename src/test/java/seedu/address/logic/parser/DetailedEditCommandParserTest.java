package seedu.address.logic.parser;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class DetailedEditCommandParserTest {
    private static final String VALID_INDEX = " 1";
    private static final String INVALID_INDEX = " -1";

    @Test
    public void parse_invalidInputs_throwsParseException() throws ParseException {
        assertThrows(ParseException.class, () ->
                new DetailedEditCommandParser().parse("dedit" + INVALID_INDEX));
    }

}
