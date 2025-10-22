package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Maplet;
import seedu.address.testutil.TypicalAttractions;

public class JsonSerializableMapletTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableMapletTest");
    private static final Path TYPICAL_ATTRACTIONS_FILE = TEST_DATA_FOLDER.resolve("typicalAttractionsMaplet.json");
    private static final Path INVALID_ATTRACTION_FILE = TEST_DATA_FOLDER.resolve("invalidAttractionMaplet.json");
    private static final Path DUPLICATE_ATTRACTION_FILE = TEST_DATA_FOLDER.resolve("duplicateAttractionMaplet.json");
    private static final Path INVALID_ITINERARY_FILE = TEST_DATA_FOLDER.resolve("invalidItineraryMaplet.json");
    private static final Path DUPLICATE_ITINERARY_FILE = TEST_DATA_FOLDER.resolve("duplicateItineraryMaplet.json");

    @Test
    public void toModelType_typicalAttractionsFile_success() throws Exception {
        JsonSerializableMaplet dataFromFile = JsonUtil.readJsonFile(TYPICAL_ATTRACTIONS_FILE,
                JsonSerializableMaplet.class).get();
        Maplet mapletFromFile = dataFromFile.toModelType();
        Maplet typicalAttractionsMaplet = TypicalAttractions.getTypicalMaplet();
        assertEquals(mapletFromFile, typicalAttractionsMaplet);
    }

    @Test
    public void toModelType_invalidAttractionFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMaplet dataFromFile = JsonUtil.readJsonFile(INVALID_ATTRACTION_FILE,
                JsonSerializableMaplet.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateAttractions_throwsIllegalValueException() throws Exception {
        JsonSerializableMaplet dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ATTRACTION_FILE,
                JsonSerializableMaplet.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMaplet.MESSAGE_DUPLICATE_ATTRACTION,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_invalidItineraryFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMaplet dataFromFile = JsonUtil.readJsonFile(INVALID_ITINERARY_FILE,
                JsonSerializableMaplet.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateItineraries_throwsIllegalValueException() throws Exception {
        JsonSerializableMaplet dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ITINERARY_FILE,
                JsonSerializableMaplet.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMaplet.MESSAGE_DUPLICATE_ITINERARY,
                dataFromFile::toModelType);
    }

}
