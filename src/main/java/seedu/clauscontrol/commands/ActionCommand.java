//@@author GShubhan
package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.child.Child;

/**
 * Adds an action with severity to a child.
 */

public class ActionCommand extends Command {
    public static final String SUCCESS = "Action added for %s: \"%s\" (severity: %d)";
    public static final String INVALID_INDEX = "Enter a valid child index";

    private int childIndex;
    private String action;
    private int severity;

    public ActionCommand(int childIndex, String action, int severity) {
        this.childIndex = childIndex;
        this.action = action;
        this.severity = severity;
    }


    @Override
    public String execute() {
        //@@author GShubhan
        if (isFinalized) {
            return "Cannot add actions after the lists have been finalised!";
        }
        //@@author
        if (childIndex < 1 || childIndex > childList.size()) {
            return INVALID_INDEX;
        }
        Child child = childList.get(childIndex - 1);
        assert child != null : "Child should not be null";
        child.addAction(action, severity);
        return String.format(SUCCESS, child.getName(), action, severity);
    }
}

//@@author
