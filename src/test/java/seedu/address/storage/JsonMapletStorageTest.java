package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalMaplet;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.Maplet;
import seedu.address.model.ReadOnlyMaplet;

public class JsonMapletStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMapletStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readMaplet_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMaplet(null));
    }

    private java.util.Optional<ReadOnlyMaplet> readMaplet(String filePath) throws Exception {
        return new JsonMapletStorage(Paths.get(filePath)).readMaplet(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readMaplet("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readMaplet("notJsonFormatMaplet.json"));
    }

    @Test
    public void readMaplet_invalidPersonMaplet_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readMaplet("invalidPersonMaplet.json"));
    }

    @Test
    public void readMaplet_invalidAndValidPersonMaplet_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readMaplet("invalidAndValidPersonMaplet.json"));
    }

    @Test
    public void readAndSaveMaplet_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempMaplet.json");
        Maplet original = getTypicalMaplet();
        JsonMapletStorage jsonMapletStorage = new JsonMapletStorage(filePath);

        // Save in new file and read back
        jsonMapletStorage.saveMaplet(original, filePath);
        ReadOnlyMaplet readBack = jsonMapletStorage.readMaplet(filePath).get();
        assertEquals(original, new Maplet(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonMapletStorage.saveMaplet(original, filePath);
        readBack = jsonMapletStorage.readMaplet(filePath).get();
        assertEquals(original, new Maplet(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonMapletStorage.saveMaplet(original); // file path not specified
        readBack = jsonMapletStorage.readMaplet().get(); // file path not specified
        assertEquals(original, new Maplet(readBack));

    }

    @Test
    public void saveMaplet_nullMaplet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMaplet(null, "SomeFile.json"));
    }

    /**
     * Saves {@code maplet} at the specified {@code filePath}.
     */
    private void saveMaplet(ReadOnlyMaplet maplet, String filePath) {
        try {
            new JsonMapletStorage(Paths.get(filePath))
                    .saveMaplet(maplet, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveMaplet_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMaplet(new Maplet(), null));
    }
}
