package seedu.address.model.attraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.ALICE;
import static seedu.address.testutil.TypicalAttractions.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AttractionBuilder;

public class AttractionTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Attraction attraction = new AttractionBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> attraction.getTags().remove(0));
    }

    @Test
    public void isSameAttraction() {
        // same object -> returns true
        assertTrue(ALICE.isSameAttraction(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameAttraction(null));

        // same name, all other attributes different -> returns true
        Attraction editedAlice = new AttractionBuilder(ALICE).withPriority(VALID_PRIORITY_BOB)
                .withContact(VALID_CONTACT_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameAttraction(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new AttractionBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameAttraction(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Attraction editedBob = new AttractionBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameAttraction(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new AttractionBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameAttraction(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Attraction aliceCopy = new AttractionBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different attraction -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Attraction editedAlice = new AttractionBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different priority -> returns false
        editedAlice = new AttractionBuilder(ALICE).withPriority(VALID_PRIORITY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different contact -> returns false
        editedAlice = new AttractionBuilder(ALICE).withContact(VALID_CONTACT_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new AttractionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new AttractionBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Attraction.class.getCanonicalName() + "{name=" + ALICE.getName()
                + ", priority=" + ALICE.getPriority()
                + ", contact=" + ALICE.getContact() + ", address=" + ALICE.getAddress()
                + ", activities=" + ALICE.getActivities() + ", opening hours=" + ALICE.getOpeningHours()
                + ", price=" + ALICE.getPrice() + ", tags=" + ALICE.getTags()
                + ", comments=[]" + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    public void hashCode_sameFields_sameHash() {
        Attraction aliceCopy = new AttractionBuilder(ALICE).build();
        assertEquals(ALICE.hashCode(), aliceCopy.hashCode());
    }

}
