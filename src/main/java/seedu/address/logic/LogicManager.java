package seedu.address.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.ActiveTab;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MapletParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyMaplet;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.location.Location;
import seedu.address.storage.Storage;




/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {

    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";
    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final MapletParser mapletParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and
     * {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        mapletParser = new MapletParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = mapletParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveMaplet(model.getMaplet());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyMaplet getMaplet() {
        return model.getMaplet();
    }

    @Override
    public ObservableList<Attraction> getFilteredAttractionList() {
        return model.getFilteredAttractionList();
    }

    @Override
    public ObservableList<Itinerary> getFilteredItineraryList() {
        return model.getFilteredItineraryList();
    }

    @Override
    public ObservableList<Location> getLocationList() {
        return model.getLocationList();
    }

    @Override
    public Path getMapletFilePath() {
        return model.getMapletFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public void setActiveTab(ActiveTab activeTab) {
        model.setActiveTab(activeTab);
    }
}
