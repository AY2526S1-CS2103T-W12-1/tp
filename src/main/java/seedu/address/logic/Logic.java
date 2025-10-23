package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyMaplet;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.location.Location;

/**
 * API of the Logic component
 */
public interface Logic {

    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the Maplet.
     *
     * @see seedu.address.model.Model#getMaplet()
     */
    ReadOnlyMaplet getMaplet();

    /**
     * Returns an unmodifiable view of the filtered list of attractions
     */
    ObservableList<Attraction> getFilteredAttractionList();

    /** Returns an unmodifiable view of the locations list */
    ObservableList<Location> getLocationList();

    /**
     * Returns an unmodifiable view of the filtered list of itineraries
     */
    ObservableList<Itinerary> getFilteredItineraryList();

    /**
     *  * Returns the user prefs' Maplet file path.
     */
    Path getMapletFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
