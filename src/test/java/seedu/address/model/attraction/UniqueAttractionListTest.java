package seedu.address.model.attraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.ALICE;
import static seedu.address.testutil.TypicalAttractions.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.attraction.exceptions.DuplicateAttractionException;
import seedu.address.model.attraction.exceptions.AttractionNotFoundException;
import seedu.address.testutil.AttractionBuilder;

public class UniqueAttractionListTest {

    private final UniqueAttractionList uniqueAttractionList = new UniqueAttractionList();

    @Test
    public void contains_nullAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAttractionList.contains(null));
    }

    @Test
    public void contains_attractionNotInList_returnsFalse() {
        assertFalse(uniqueAttractionList.contains(ALICE));
    }

    @Test
    public void contains_attractionInList_returnsTrue() {
        uniqueAttractionList.add(ALICE);
        assertTrue(uniqueAttractionList.contains(ALICE));
    }

    @Test
    public void contains_attractionWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAttractionList.add(ALICE);
        Attraction editedAlice = new AttractionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueAttractionList.contains(editedAlice));
    }

    @Test
    public void add_nullAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAttractionList.add(null));
    }

    @Test
    public void add_duplicateAttraction_throwsDuplicateAttractionException() {
        uniqueAttractionList.add(ALICE);
        assertThrows(DuplicateAttractionException.class, () -> uniqueAttractionList.add(ALICE));
    }

    @Test
    public void setAttraction_nullTargetAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAttractionList.setAttraction(null, ALICE));
    }

    @Test
    public void setAttraction_nullEditedAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAttractionList.setAttraction(ALICE, null));
    }

    @Test
    public void setAttraction_targetAttractionNotInList_throwsAttractionNotFoundException() {
        assertThrows(AttractionNotFoundException.class, () -> uniqueAttractionList.setAttraction(ALICE, ALICE));
    }

    @Test
    public void setAttraction_editedAttractionIsSameAttraction_success() {
        uniqueAttractionList.add(ALICE);
        uniqueAttractionList.setAttraction(ALICE, ALICE);
        UniqueAttractionList expectedUniqueAttractionList = new UniqueAttractionList();
        expectedUniqueAttractionList.add(ALICE);
        assertEquals(expectedUniqueAttractionList, uniqueAttractionList);
    }

    @Test
    public void setAttraction_editedAttractionHasSameIdentity_success() {
        uniqueAttractionList.add(ALICE);
        Attraction editedAlice = new AttractionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueAttractionList.setAttraction(ALICE, editedAlice);
        UniqueAttractionList expectedUniqueAttractionList = new UniqueAttractionList();
        expectedUniqueAttractionList.add(editedAlice);
        assertEquals(expectedUniqueAttractionList, uniqueAttractionList);
    }

    @Test
    public void setAttraction_editedAttractionHasDifferentIdentity_success() {
        uniqueAttractionList.add(ALICE);
        uniqueAttractionList.setAttraction(ALICE, BOB);
        UniqueAttractionList expectedUniqueAttractionList = new UniqueAttractionList();
        expectedUniqueAttractionList.add(BOB);
        assertEquals(expectedUniqueAttractionList, uniqueAttractionList);
    }

    @Test
    public void setAttraction_editedAttractionHasNonUniqueIdentity_throwsDuplicateAttractionException() {
        uniqueAttractionList.add(ALICE);
        uniqueAttractionList.add(BOB);
        assertThrows(DuplicateAttractionException.class, () -> uniqueAttractionList.setAttraction(ALICE, BOB));
    }

    @Test
    public void remove_nullAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAttractionList.remove(null));
    }

    @Test
    public void remove_attractionDoesNotExist_throwsAttractionNotFoundException() {
        assertThrows(AttractionNotFoundException.class, () -> uniqueAttractionList.remove(ALICE));
    }

    @Test
    public void remove_existingAttraction_removesAttraction() {
        uniqueAttractionList.add(ALICE);
        uniqueAttractionList.remove(ALICE);
        UniqueAttractionList expectedUniqueAttractionList = new UniqueAttractionList();
        assertEquals(expectedUniqueAttractionList, uniqueAttractionList);
    }

    @Test
    public void setAttractions_nullUniqueAttractionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAttractionList.setAttractions((UniqueAttractionList) null));
    }

    @Test
    public void setAttractions_uniqueAttractionList_replacesOwnListWithProvidedUniqueAttractionList() {
        uniqueAttractionList.add(ALICE);
        UniqueAttractionList expectedUniqueAttractionList = new UniqueAttractionList();
        expectedUniqueAttractionList.add(BOB);
        uniqueAttractionList.setAttractions(expectedUniqueAttractionList);
        assertEquals(expectedUniqueAttractionList, uniqueAttractionList);
    }

    @Test
    public void setAttractions_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAttractionList.setAttractions((List<Attraction>) null));
    }

    @Test
    public void setAttractions_list_replacesOwnListWithProvidedList() {
        uniqueAttractionList.add(ALICE);
        List<Attraction> attractionList = Collections.singletonList(BOB);
        uniqueAttractionList.setAttractions(attractionList);
        UniqueAttractionList expectedUniqueAttractionList = new UniqueAttractionList();
        expectedUniqueAttractionList.add(BOB);
        assertEquals(expectedUniqueAttractionList, uniqueAttractionList);
    }

    @Test
    public void setAttractions_listWithDuplicateAttractions_throwsDuplicateAttractionException() {
        List<Attraction> listWithDuplicateAttractions = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateAttractionException.class, () -> uniqueAttractionList.setAttractions(listWithDuplicateAttractions));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueAttractionList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueAttractionList.asUnmodifiableObservableList().toString(), uniqueAttractionList.toString());
    }
}
