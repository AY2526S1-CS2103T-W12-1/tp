package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ATTRACTIONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.ALICE;
import static seedu.address.testutil.TypicalAttractions.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.attraction.NameContainsKeywordsPredicate;
import seedu.address.testutil.MapletBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Maplet(), new Maplet(modelManager.getMaplet()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setMapletFilePath(Paths.get("maplet/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setMapletFilePath(Paths.get("new/maplet/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setMapletFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setMapletFilePath(null));
    }

    @Test
    public void setMapletFilePath_validPath_setsMapletFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setMapletFilePath(path);
        assertEquals(path, modelManager.getMapletFilePath());
    }

    @Test
    public void hasAttraction_nullAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasAttraction(null));
    }

    @Test
    public void hasAttraction_attractionNotInMaplet_returnsFalse() {
        assertFalse(modelManager.hasAttraction(ALICE));
    }

    @Test
    public void hasAttraction_attractionInMaplet_returnsTrue() {
        modelManager.addAttraction(ALICE);
        assertTrue(modelManager.hasAttraction(ALICE));
    }

    @Test
    public void getFilteredAttractionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredAttractionList().remove(0));
    }

    @Test
    public void equals() {
        Maplet maplet = new MapletBuilder().withAttraction(ALICE).withAttraction(BENSON).build();
        Maplet differentMaplet = new Maplet();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(maplet, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(maplet, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different maplet -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentMaplet, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredAttractionList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(maplet, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredAttractionList(PREDICATE_SHOW_ALL_ATTRACTIONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setMapletFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(maplet, differentUserPrefs)));
    }
}
