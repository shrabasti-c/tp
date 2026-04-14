package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.child.Name;
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
        if (isFinalized) {
            return "Cannot edit child details after the lists have been finalised!";
        }

        if (childIndex < 0 || childIndex >= childList.size()) {
            return "Invalid index position :(";
        }

        Child child = childList.get(childIndex);

        String nameChanged = "Child "+(childIndex + 1) + "'s name changed to ";
        String locationChanged = "Child "+(childIndex + 1) + "'s location changed to ";
        String ageChanged = "Child "+(childIndex + 1) + "'s age changed to ";
        StringBuilder finalString = new StringBuilder("Ho ho ho! The following have changed.\n");

        try {
            if (newName != null) {
                child.setName(new Name(newName));
                finalString.append(nameChanged).append(newName).append("\n");
            }

            if (newLocation != null) {
                child.setLocation(newLocation);
                finalString.append(locationChanged).append(newLocation).append("\n");
            }

            if (newAge != -1) {
                child.setAge(newAge);
                finalString.append(ageChanged).append(newAge);
            }
            else{
                throw new IllegalValueException("Please use correct format: edit CHILD_INDEX " +
                        "[n/name] [l/location] [a/action]");
            }
        } catch (IllegalValueException e) {
            return e.getMessage();
        }
        return String.valueOf(finalString);
    }
}
//@@author
