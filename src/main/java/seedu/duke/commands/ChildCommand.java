//@@author shrabasti-c
//Solution below adapted from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook
// /commands/AddCommand.java and shrabasti-c's iP
package seedu.duke.commands;


import seedu.duke.data.exception.IllegalValueException;
import seedu.duke.data.child.Name;
import seedu.duke.data.child.Child;

/**
 * Adds a child to the child list.
 */
public class ChildCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Ho ho ho! New child added: %1$s";
    private final Child toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public ChildCommand(String name) throws IllegalValueException {
        this.toAdd = new Child(new Name(name));
        assert toAdd != null : "Child should not be null";
    }

    @Override
    public String execute() {
        childList.add(toAdd);
        return String.format(MESSAGE_SUCCESS, toAdd);
    }

}
//@@author