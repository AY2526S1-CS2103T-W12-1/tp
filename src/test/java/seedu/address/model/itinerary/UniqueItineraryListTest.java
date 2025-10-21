package seedu.address.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItineraries.EUROPE_TOUR;
import static seedu.address.testutil.TypicalItineraries.JAPAN_TRIP;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.itinerary.exceptions.DuplicateItineraryException;
import seedu.address.model.itinerary.exceptions.ItineraryNotFoundException;
import seedu.address.testutil.ItineraryBuilder;

public class UniqueItineraryListTest {

    private final UniqueItineraryList uniqueItineraryList = new UniqueItineraryList();

    @Test
    public void contains_nullItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.contains(null));
    }

    @Test
    public void contains_itineraryNotInList_returnsFalse() {
        assertFalse(uniqueItineraryList.contains(JAPAN_TRIP));
    }

    @Test
    public void contains_itineraryInList_returnsTrue() {
        uniqueItineraryList.add(JAPAN_TRIP);
        assertTrue(uniqueItineraryList.contains(JAPAN_TRIP));
    }

    @Test
    public void contains_itineraryWithSameIdentityFieldsInList_returnsTrue() {
        uniqueItineraryList.add(JAPAN_TRIP);
        Itinerary editedJapanTrip = new ItineraryBuilder(JAPAN_TRIP)
                .withCreatedAt(LocalDateTime.of(2025, 1, 1, 10, 0))
                .build();
        assertTrue(uniqueItineraryList.contains(editedJapanTrip));
    }

    @Test
    public void add_nullItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.add(null));
    }

    @Test
    public void add_duplicateItinerary_throwsDuplicateItineraryException() {
        uniqueItineraryList.add(JAPAN_TRIP);
        assertThrows(DuplicateItineraryException.class, () -> uniqueItineraryList.add(JAPAN_TRIP));
    }

    @Test
    public void setItinerary_nullTargetItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.setItinerary(null, JAPAN_TRIP));
    }

    @Test
    public void setItinerary_nullEditedItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.setItinerary(JAPAN_TRIP, null));
    }

    @Test
    public void setItinerary_targetItineraryNotInList_throwsItineraryNotFoundException() {
        assertThrows(ItineraryNotFoundException.class, () -> uniqueItineraryList.setItinerary(JAPAN_TRIP, JAPAN_TRIP));
    }

    @Test
    public void setItinerary_editedItineraryIsSameItinerary_success() {
        uniqueItineraryList.add(JAPAN_TRIP);
        uniqueItineraryList.setItinerary(JAPAN_TRIP, JAPAN_TRIP);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        expectedUniqueItineraryList.add(JAPAN_TRIP);
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItinerary_editedItineraryHasSameIdentity_success() {
        uniqueItineraryList.add(JAPAN_TRIP);
        Itinerary editedJapanTrip = new ItineraryBuilder(JAPAN_TRIP)
                .withCreatedAt(LocalDateTime.of(2025, 1, 1, 10, 0))
                .build();
        uniqueItineraryList.setItinerary(JAPAN_TRIP, editedJapanTrip);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        expectedUniqueItineraryList.add(editedJapanTrip);
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItinerary_editedItineraryHasDifferentIdentity_success() {
        uniqueItineraryList.add(JAPAN_TRIP);
        uniqueItineraryList.setItinerary(JAPAN_TRIP, EUROPE_TOUR);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        expectedUniqueItineraryList.add(EUROPE_TOUR);
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItinerary_editedItineraryHasNonUniqueIdentity_throwsDuplicateItineraryException() {
        uniqueItineraryList.add(JAPAN_TRIP);
        uniqueItineraryList.add(EUROPE_TOUR);
        assertThrows(DuplicateItineraryException.class, () ->
                uniqueItineraryList.setItinerary(JAPAN_TRIP, EUROPE_TOUR));
    }

    @Test
    public void remove_nullItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueItineraryList.remove(null));
    }

    @Test
    public void remove_itineraryDoesNotExist_throwsItineraryNotFoundException() {
        assertThrows(ItineraryNotFoundException.class, () -> uniqueItineraryList.remove(JAPAN_TRIP));
    }

    @Test
    public void remove_existingItinerary_removesItinerary() {
        uniqueItineraryList.add(JAPAN_TRIP);
        uniqueItineraryList.remove(JAPAN_TRIP);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItineraries_nullUniqueItineraryList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueItineraryList.setItineraries((UniqueItineraryList) null));
    }

    @Test
    public void setItineraries_uniqueItineraryList_replacesOwnListWithProvidedUniqueItineraryList() {
        uniqueItineraryList.add(JAPAN_TRIP);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        expectedUniqueItineraryList.add(EUROPE_TOUR);
        uniqueItineraryList.setItineraries(expectedUniqueItineraryList);
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItineraries_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueItineraryList.setItineraries((List<Itinerary>) null));
    }

    @Test
    public void setItineraries_list_replacesOwnListWithProvidedList() {
        uniqueItineraryList.add(JAPAN_TRIP);
        List<Itinerary> itineraryList = Collections.singletonList(EUROPE_TOUR);
        uniqueItineraryList.setItineraries(itineraryList);
        UniqueItineraryList expectedUniqueItineraryList = new UniqueItineraryList();
        expectedUniqueItineraryList.add(EUROPE_TOUR);
        assertEquals(expectedUniqueItineraryList, uniqueItineraryList);
    }

    @Test
    public void setItineraries_listWithDuplicateItineraries_throwsDuplicateItineraryException() {
        List<Itinerary> listWithDuplicateItineraries = Arrays.asList(JAPAN_TRIP, JAPAN_TRIP);
        assertThrows(DuplicateItineraryException.class, () ->
                uniqueItineraryList.setItineraries(listWithDuplicateItineraries));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueItineraryList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueItineraryList.asUnmodifiableObservableList().toString(),
                uniqueItineraryList.toString());
    }

    @Test
    public void hashCodeMethod() {
        UniqueItineraryList list1 = new UniqueItineraryList();
        list1.add(JAPAN_TRIP);
        UniqueItineraryList list2 = new UniqueItineraryList();
        list2.add(JAPAN_TRIP);
        assertEquals(list1.hashCode(), list2.hashCode());
    }

    @Test
    public void iterator_iteratesThroughList() {
        uniqueItineraryList.add(JAPAN_TRIP);
        uniqueItineraryList.add(EUROPE_TOUR);
        int count = 0;
        for (Itinerary itinerary : uniqueItineraryList) {
            count++;
        }
        assertEquals(2, count);
    }
}
