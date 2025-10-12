package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.testutil.TypicalAttractions;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_ATTRACTIONS_FILE = TEST_DATA_FOLDER.resolve("typicalAttractionsAddressBook.json");
    private static final Path INVALID_ATTRACTION_FILE = TEST_DATA_FOLDER.resolve("invalidAttractionAddressBook.json");
    private static final Path DUPLICATE_ATTRACTION_FILE = TEST_DATA_FOLDER.resolve("duplicateAttractionAddressBook.json");

    @Test
    public void toModelType_typicalAttractionsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_ATTRACTIONS_FILE,
                JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalAttractionsAddressBook = TypicalAttractions.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalAttractionsAddressBook);
    }

    @Test
    public void toModelType_invalidAttractionFile_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(INVALID_ATTRACTION_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAttractions_throwsIllegalValueException() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ATTRACTION_FILE,
                JsonSerializableAddressBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook.MESSAGE_DUPLICATE_ATTRACTION,
                dataFromFile::toModelType);
    }

}
