package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.attraction.Attraction;

/**
 * Represents the in-memory model of the Maplet data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Maplet maplet;
    private final UserPrefs userPrefs;
    private final FilteredList<Attraction> filteredAttractions;

    /**
     * Initializes a ModelManager with the given Maplet and userPrefs.
     */
    public ModelManager(ReadOnlyMaplet maplet, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(maplet, userPrefs);

        logger.fine("Initializing with Maplet: " + maplet + " and user prefs " + userPrefs);

        this.maplet = new Maplet(maplet);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredAttractions = new FilteredList<>(this.maplet.getAttractionList());
    }

    public ModelManager() {
        this(new Maplet(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getMapletFilePath() {
        return userPrefs.getMapletFilePath();
    }

    @Override
    public void setMapletFilePath(Path mapletFilePath) {
        requireNonNull(mapletFilePath);
        userPrefs.setMapletFilePath(mapletFilePath);
    }

    //=========== Maplet ================================================================================

    @Override
    public void setMaplet(ReadOnlyMaplet maplet) {
        this.maplet.resetData(maplet);
    }

    @Override
    public ReadOnlyMaplet getMaplet() {
        return maplet;
    }

    @Override
    public boolean hasAttraction(Attraction attraction) {
        requireNonNull(attraction);
        return maplet.hasAttraction(attraction);
    }

    @Override
    public void deleteAttraction(Attraction target) {
        maplet.removeAttraction(target);
    }

    @Override
    public void addAttraction(Attraction attraction) {
        maplet.addAttraction(attraction);
        updateFilteredAttractionList(PREDICATE_SHOW_ALL_ATTRACTIONS);
    }

    @Override
    public void setAttraction(Attraction target, Attraction editedAttraction) {
        requireAllNonNull(target, editedAttraction);

        maplet.setAttraction(target, editedAttraction);
    }

    //=========== Filtered Attraction List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Attraction} backed by the internal list of
     * {@code versionedMaplet}
     */
    @Override
    public ObservableList<Attraction> getFilteredAttractionList() {
        return filteredAttractions;
    }

    @Override
    public void updateFilteredAttractionList(Predicate<Attraction> predicate) {
        requireNonNull(predicate);
        filteredAttractions.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return maplet.equals(otherModelManager.maplet)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredAttractions.equals(otherModelManager.filteredAttractions);
    }

}
