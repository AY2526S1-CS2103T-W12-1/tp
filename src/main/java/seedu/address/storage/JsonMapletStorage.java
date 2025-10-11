package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyMaplet;

/**
 * A class to access Maplet data stored as a json file on the hard disk.
 */
public class JsonMapletStorage implements MapletStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMapletStorage.class);

    private Path filePath;

    public JsonMapletStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMapletFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMaplet> readMaplet() throws DataLoadingException {
        return readMaplet(filePath);
    }

    /**
     * Similar to {@link #readMaplet()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyMaplet> readMaplet(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableMaplet> jsonMaplet = JsonUtil.readJsonFile(
                filePath, JsonSerializableMaplet.class);
        if (!jsonMaplet.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMaplet.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveMaplet(ReadOnlyMaplet maplet) throws IOException {
        saveMaplet(maplet, filePath);
    }

    /**
     * Similar to {@link #saveMaplet(ReadOnlyMaplet)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMaplet(ReadOnlyMaplet maplet, Path filePath) throws IOException {
        requireNonNull(maplet);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMaplet(maplet), filePath);
    }

}
