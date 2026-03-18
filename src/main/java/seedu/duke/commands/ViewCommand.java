//@@author shrabasti-c
package seedu.duke.commands;

import seedu.duke.data.child.Child;

public class ViewCommand extends Command{
    private final int childIndex;

    public ViewCommand(int childIndex) {
        this.childIndex = childIndex;
    }

    @Override
    public String execute() {
        if (childIndex < 0 || childIndex >= childList.size()) {
            return "Invalid position :(";
        }

        Child child = childList.get(childIndex);

        return child.toString();
    }
}
//@@author
