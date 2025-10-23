package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTIVITIES_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTIVITIES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPENING_HOURS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPENING_HOURS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Maplet;
import seedu.address.model.attraction.Attraction;


/**
 * A utility class containing a list of {@code Attraction} objects to be used in tests.
 */
public class TypicalAttractions {

    public static final Attraction ALICE = new AttractionBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withContact("alice@example.com")
            .withPriority("9").withActivities("Sightseeing").withOpeningHours("1200 - 1500").withPrice("15")
            .withTags("friends").build();
    public static final Attraction BENSON = new AttractionBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withContact("johnd@example.com").withPriority("8").withActivities("Sightseeing")
            .withOpeningHours("1200 - 1500").withPrice("15").withTags("owesMoney", "friends")
            .withComments("Survived a war", "is Alive!!").build();
    public static final Attraction CARL = new AttractionBuilder().withName("Carl Kurz").withPriority("7")
            .withContact("heinz@example.com").withAddress("wall street").withActivities("Sightseeing")
            .withOpeningHours("1200 - 1500").withPrice("15").build();
    public static final Attraction DANIEL = new AttractionBuilder().withName("Daniel Meier").withPriority("6")
            .withContact("cornelia@example.com").withAddress("10th street")
            .withActivities("Sightseeing").withOpeningHours("1200 - 1500").withPrice("15").withTags("friends").build();
    public static final Attraction ELLE = new AttractionBuilder().withName("Elle Meyer").withPriority("5")
            .withContact("werner@example.com").withAddress("michegan ave").withActivities("Sightseeing")
            .withOpeningHours("1200 - 1500").withPrice("15").build();
    public static final Attraction FIONA = new AttractionBuilder().withName("Fiona Kunz").withPriority("4")
            .withContact("lydia@example.com").withAddress("little tokyo").withActivities("Sightseeing")
            .withOpeningHours("1200 - 1500").withPrice("15").build();
    public static final Attraction GEORGE = new AttractionBuilder().withName("George Best").withPriority("3")
            .withContact("anna@example.com").withAddress("4th street").withActivities("Sightseeing")
            .withOpeningHours("1200 - 1500").withPrice("15").build();

    // Manually added
    public static final Attraction HOON = new AttractionBuilder().withName("Hoon Meier").withPriority("2")
            .withContact("stefan@example.com").withAddress("little india").build();
    public static final Attraction IDA = new AttractionBuilder().withName("Ida Mueller").withPriority("1")
            .withContact("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Attraction's details found in {@code CommandTestUtil}
    public static final Attraction AMY = new AttractionBuilder().withName(VALID_NAME_AMY)
            .withPriority(VALID_PRIORITY_AMY)
            .withContact(VALID_CONTACT_AMY).withAddress(VALID_ADDRESS_AMY)
            .withActivities(VALID_ACTIVITIES_AMY).withOpeningHours(VALID_OPENING_HOURS_AMY).withPrice(VALID_PRICE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Attraction BOB = new AttractionBuilder().withName(VALID_NAME_BOB)
            .withPriority(VALID_PRIORITY_BOB)
            .withContact(VALID_CONTACT_BOB).withAddress(VALID_ADDRESS_BOB)
            .withActivities(VALID_ACTIVITIES_BOB).withOpeningHours(VALID_OPENING_HOURS_BOB).withPrice(VALID_PRICE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalAttractions() {} // prevents instantiation

    /**
     * Returns an {@code Maplet} with all the typical attractions.
     */
    public static Maplet getTypicalMaplet() {
        Maplet maplet = new Maplet();
        for (Attraction attraction : getTypicalAttractions()) {
            maplet.addAttraction(attraction);
        }
        return maplet;
    }

    public static List<Attraction> getTypicalAttractions() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
