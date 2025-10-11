package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyMaplet;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Maplet data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private MapletStorage mapletStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code MapletStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(MapletStorage mapletStorage, UserPrefsStorage userPrefsStorage) {
        this.mapletStorage = mapletStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Maplet methods ==============================

    @Override
    public Path getMapletFilePath() {
        return mapletStorage.getMapletFilePath();
    }

    @Override
    public Optional<ReadOnlyMaplet> readMaplet() throws DataLoadingException {
        return readMaplet(mapletStorage.getMapletFilePath());
    }

    @Override
    public Optional<ReadOnlyMaplet> readMaplet(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return mapletStorage.readMaplet(filePath);
    }

    @Override
    public void saveMaplet(ReadOnlyMaplet maplet) throws IOException {
        saveMaplet(maplet, mapletStorage.getMapletFilePath());
    }

    @Override
    public void saveMaplet(ReadOnlyMaplet maplet, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        mapletStorage.saveMaplet(maplet, filePath);
    }

}
