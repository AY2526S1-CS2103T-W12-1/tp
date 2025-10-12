package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalMaplet;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class MapletTest {

    private final Maplet maplet = new Maplet();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), maplet.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> maplet.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMaplet_replacesData() {
        Maplet newData = getTypicalMaplet();
        maplet.resetData(newData);
        assertEquals(newData, maplet);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        MapletStub newData = new MapletStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> maplet.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> maplet.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInMaplet_returnsFalse() {
        assertFalse(maplet.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInMaplet_returnsTrue() {
        maplet.addPerson(ALICE);
        assertTrue(maplet.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInMaplet_returnsTrue() {
        maplet.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(maplet.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> maplet.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = Maplet.class.getCanonicalName() + "{persons=" + maplet.getPersonList() + "}";
        assertEquals(expected, maplet.toString());
    }

    /**
     * A stub ReadOnlyMaplet whose persons list can violate interface constraints.
     */
    private static class MapletStub implements ReadOnlyMaplet {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        MapletStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
