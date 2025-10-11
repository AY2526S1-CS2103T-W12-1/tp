package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyMaplet;

/**
 * Represents a storage for {@link seedu.address.model.Maplet}.
 */
public interface MapletStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMapletFilePath();

    /**
     * Returns Maplet data as a {@link ReadOnlyMaplet}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyMaplet> readMaplet() throws DataLoadingException;

    /**
     * @see #getMapletFilePath()
     */
    Optional<ReadOnlyMaplet> readMaplet(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyMaplet} to the storage.
     * @param maplet cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMaplet(ReadOnlyMaplet maplet) throws IOException;

    /**
     * @see #saveMaplet(ReadOnlyMaplet)
     */
    void saveMaplet(ReadOnlyMaplet maplet, Path filePath) throws IOException;

}
