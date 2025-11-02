package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ATTRACTIONS;

import seedu.address.commons.core.ActiveTab;
import seedu.address.model.Model;

/**
 * Lists all attractions in the Maplet to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all attractions";
    public static final String MESSAGE_NON_ATTRACTION_TAB = "All itineraries/locations are listed by default";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.getActiveTab() != ActiveTab.ATTRACTIONS) {
            return new CommandResult(MESSAGE_NON_ATTRACTION_TAB);
        }
        model.updateFilteredAttractionList(PREDICATE_SHOW_ALL_ATTRACTIONS);
        model.updateSortedAttractionList(null);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
