package seedu.clauscontrol.commands;
import seedu.clauscontrol.data.exception.IllegalValueException;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.child.Child;

//@@author shrabasti-c
/* Adapted from AddCommand Class of AB2 application and author's iP.
 * Link: https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/commands/AddCommand.java
 * Link: https://github.com/shrabasti-c/ip
 * with some modifications
 */
/**
 * Adds a child to the child list.
 */
public class ChildCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Ho ho ho! New child added: %1$s";
    private final Child toAdd;

    public ChildCommand(String name, String location, int age) throws IllegalValueException {
        this.toAdd = new Child(new Name(name), age, location);
        assert toAdd != null : "Child should not be null";
    }

    public Child getChild() {
        return toAdd;
    }

    @Override
    public String execute() {
        childList.add(toAdd);
        return String.format(MESSAGE_SUCCESS, toAdd);
    }
}
//@@author
