package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTIVITIES_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACTIVITIES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Maplet;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withContact("alice@example.com")
            .withPriority("9").withActivities("Sightseeing")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withContact("johnd@example.com").withPriority("8").withActivities("Sightseeing")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPriority("7")
            .withContact("heinz@example.com").withAddress("wall street").withActivities("Sightseeing").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPriority("6")
            .withContact("cornelia@example.com").withAddress("10th street")
            .withActivities("Sightseeing").withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPriority("5")
            .withContact("werner@example.com").withAddress("michegan ave").withActivities("Sightseeing").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPriority("4")
            .withContact("lydia@example.com").withAddress("little tokyo").withActivities("Sightseeing").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPriority("3")
            .withContact("anna@example.com").withAddress("4th street").withActivities("Sightseeing").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPriority("2")
            .withContact("stefan@example.com").withAddress("little india").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPriority("1")
            .withContact("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPriority(VALID_PRIORITY_AMY)
            .withContact(VALID_CONTACT_AMY).withAddress(VALID_ADDRESS_AMY)
            .withActivities(VALID_ACTIVITIES_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPriority(VALID_PRIORITY_BOB)
            .withContact(VALID_CONTACT_BOB).withAddress(VALID_ADDRESS_BOB)
            .withActivities(VALID_ACTIVITIES_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code Maplet} with all the typical persons.
     */
    public static Maplet getTypicalMaplet() {
        Maplet maplet = new Maplet();
        for (Person person : getTypicalPersons()) {
            maplet.addPerson(person);
        }
        return maplet;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
