package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.exception.IllegalValueException;

//@@author shrabasti-c
/**
 * Edits a child in the child list.
 */
public class EditCommand extends Command {
    private final int childIndex;
    private final String newName;
    private final String newLocation;
    private final int newAge;

    public EditCommand(int childIndex, String newName, String newLocation, int newAge) {
        this.childIndex = childIndex;
        this.newName = newName;
        this.newLocation = newLocation;
        this.newAge = newAge;
    }

    @Override
    public String execute() {
        if (childIndex < 0 || childIndex >= childList.size()) {
            return "Invalid position :(";
        }

        Child child = childList.get(childIndex);

        try {
            if (newName != null) {
                child.setName(newName);
            }

            if (newLocation != null) {
                child.setLocation(newLocation);
            }

            if (newAge != -1) {
                child.setAge(newAge);
            }
        } catch (IllegalValueException e) {
            throw new RuntimeException(e);
        }
        return "Details changed!";
    }
}
//@@author
