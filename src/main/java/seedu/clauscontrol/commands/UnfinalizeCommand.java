//@@author GShubhan
package seedu.clauscontrol.commands;

/**
 * Reverses the finalize state, re-enabling actions and reassignments.
 * Gift operations remain blocked until finalize is called again.
 */
public class UnfinalizeCommand extends Command {

    @Override
    public String execute() {
        if (!isFinalized) {
            return "The lists have not been finalised yet — nothing to undo.";
        }
        return "Lists have been unfinalized! You can now add actions and reassign children again.\n"
                + "Note: Gift operations are blocked until you finalize again.";
    }
}
//@@author