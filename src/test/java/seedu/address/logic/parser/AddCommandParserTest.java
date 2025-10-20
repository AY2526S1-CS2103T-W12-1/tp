package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import seedu.address.logic.commands.AddCommand;
import static seedu.address.logic.commands.CommandTestUtil.ACTIVITIES_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ACTIVITIES_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ACTIVITIES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CONTACT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_OUT_OF_RANGE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import seedu.address.model.attraction.Activities;
import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Contact;
import seedu.address.model.attraction.Name;
import seedu.address.model.attraction.Priority;
import seedu.address.testutil.AttractionBuilder;
import static seedu.address.testutil.TypicalAttractions.AMY;
import static seedu.address.testutil.TypicalAttractions.BOB;

public class AddCommandParserTest {

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Attraction expectedAttraction = new AttractionBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PRIORITY_DESC_BOB + CONTACT_DESC_BOB
                + ADDRESS_DESC_BOB + ACTIVITIES_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedAttraction));

        // multiple tags - all accepted
        Attraction expectedAttractionMultipleTags = new AttractionBuilder(BOB)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + PRIORITY_DESC_BOB + CONTACT_DESC_BOB + ADDRESS_DESC_BOB
                        + ACTIVITIES_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddCommand(expectedAttractionMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedAttractionString = NAME_DESC_BOB + PRIORITY_DESC_BOB + CONTACT_DESC_BOB
                + ADDRESS_DESC_BOB + ACTIVITIES_DESC_BOB + TAG_DESC_FRIEND;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedAttractionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple priorities
        assertParseFailure(parser, PRIORITY_DESC_AMY + validExpectedAttractionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRIORITY));

        // multiple contacts
        assertParseFailure(parser, CONTACT_DESC_AMY + validExpectedAttractionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT));

        // multiple addresses
        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedAttractionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // multiple activities
        assertParseFailure(parser, ACTIVITIES_DESC_AMY + validExpectedAttractionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ACTIVITIES));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedAttractionString
                + PRIORITY_DESC_AMY + CONTACT_DESC_AMY + NAME_DESC_AMY + ADDRESS_DESC_AMY + ACTIVITIES_DESC_AMY
                + validExpectedAttractionString,
                Messages.getErrorMessageForDuplicatePrefixes(
                        PREFIX_NAME, PREFIX_ADDRESS, PREFIX_CONTACT, PREFIX_PRIORITY, PREFIX_ACTIVITIES));

        // invalid value followed by valid value
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedAttractionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid contact
        assertParseFailure(parser, INVALID_CONTACT_DESC + validExpectedAttractionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT));

        // invalid phone
        assertParseFailure(parser, INVALID_PRIORITY_DESC + validExpectedAttractionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRIORITY));

        // invalid address
        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedAttractionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid activities
        assertParseFailure(parser, INVALID_ACTIVITIES_DESC + validExpectedAttractionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ACTIVITIES));

        // valid value followed by invalid value
        // invalid name
        assertParseFailure(parser, validExpectedAttractionString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid contact
        assertParseFailure(parser, validExpectedAttractionString + INVALID_CONTACT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_CONTACT));

        // invalid phone
        assertParseFailure(parser, validExpectedAttractionString + INVALID_PRIORITY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRIORITY));

        // invalid address
        assertParseFailure(parser, validExpectedAttractionString + INVALID_ADDRESS_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));

        // invalid activities
        assertParseFailure(parser, validExpectedAttractionString + INVALID_ACTIVITIES_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ACTIVITIES));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Attraction expectedAttraction = new AttractionBuilder(AMY).withTags().build();
        assertParseSuccess(parser,
                NAME_DESC_AMY + PRIORITY_DESC_AMY + CONTACT_DESC_AMY + ADDRESS_DESC_AMY + ACTIVITIES_DESC_AMY,
                new AddCommand(expectedAttraction));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PRIORITY_DESC_BOB + CONTACT_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PRIORITY_BOB + CONTACT_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing contact prefix
        assertParseFailure(parser, NAME_DESC_BOB + PRIORITY_DESC_BOB + VALID_CONTACT_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PRIORITY_DESC_BOB + CONTACT_DESC_BOB + VALID_ADDRESS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PRIORITY_BOB + VALID_CONTACT_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PRIORITY_DESC_BOB + CONTACT_DESC_BOB + ADDRESS_DESC_BOB
                + ACTIVITIES_DESC_BOB + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid priority format
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PRIORITY_DESC + CONTACT_DESC_BOB + ADDRESS_DESC_BOB
                + ACTIVITIES_DESC_BOB + TAG_DESC_FRIEND, Priority.MESSAGE_CONSTRAINTS);

        // invalid priority range
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PRIORITY_OUT_OF_RANGE_DESC + CONTACT_DESC_BOB
                + ADDRESS_DESC_BOB + ACTIVITIES_DESC_BOB + TAG_DESC_FRIEND, Priority.MESSAGE_CONSTRAINTS);

        // invalid contact
        assertParseFailure(parser, NAME_DESC_BOB + PRIORITY_DESC_BOB + INVALID_CONTACT_DESC + ADDRESS_DESC_BOB
                + ACTIVITIES_DESC_BOB + TAG_DESC_FRIEND, Contact.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PRIORITY_DESC_BOB + CONTACT_DESC_BOB + INVALID_ADDRESS_DESC
                + ACTIVITIES_DESC_BOB + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid activities
        assertParseFailure(parser, NAME_DESC_BOB + PRIORITY_DESC_BOB + CONTACT_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_ACTIVITIES_DESC + TAG_DESC_FRIEND, Activities.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PRIORITY_DESC_BOB + CONTACT_DESC_BOB + ADDRESS_DESC_BOB
                + ACTIVITIES_DESC_BOB + INVALID_TAG_DESC + TAG_DESC_FRIEND,
                seedu.address.model.tag.Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PRIORITY_DESC_BOB + CONTACT_DESC_BOB + INVALID_ADDRESS_DESC
                + ACTIVITIES_DESC_BOB + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PRIORITY_DESC_BOB + CONTACT_DESC_BOB
                + ADDRESS_DESC_BOB + ACTIVITIES_DESC_BOB + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
