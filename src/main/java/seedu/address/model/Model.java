package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.attraction.Attraction;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Attraction> PREDICATE_SHOW_ALL_ATTRACTIONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if an attraction with the same identity as {@code attraction} exists in the address book.
     */
    boolean hasAttraction(Attraction attraction);

    /**
     * Deletes the given attraction.
     * The attraction must exist in the address book.
     */
    void deleteAttraction(Attraction target);

    /**
     * Adds the given attraction.
     * {@code attraction} must not already exist in the address book.
     */
    void addAttraction(Attraction attraction);

    /**
     * Replaces the given attraction {@code target} with {@code editedAttraction}.
     * {@code target} must exist in the address book.
     * The attraction identity of {@code editedAttraction} must not be the same as another existing attraction in the
     * address book.
     */
    void setAttraction(Attraction target, Attraction editedAttraction);

    /** Returns an unmodifiable view of the filtered attraction list */
    ObservableList<Attraction> getFilteredAttractionList();

    /**
     * Updates the filter of the filtered attraction list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAttractionList(Predicate<Attraction> predicate);
}
