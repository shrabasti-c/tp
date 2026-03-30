package seedu.duke.commands;

//@@author shrabasti-c
public class DeleteCommand extends Command {
    private final int childIndex;

    public DeleteCommand(int childIndex) {
        this.childIndex = childIndex;
    }

    @Override
    public String execute() {
        if (childIndex < 0 || childIndex >= childList.size()) {
            return "Invalid position :(";
        }
        childList.remove(childIndex);
        return "Child removed .. :(";
    }
}
//@@author
