package seedu.clauscontrol.commands;

//@@author shrabasti-c
/**
 * Deletes a child from the child list.
 */
public class DeleteCommand extends Command {
    private final int childIndex;

    public DeleteCommand(int childIndex) {
        this.childIndex = childIndex;
    }

    @Override
    public String execute() {
        String name = childList.get(childIndex).getName().toString();
        childList.remove(childIndex);
        return "Child" + name+ " removed .. :(";
    }
}
//@@author
