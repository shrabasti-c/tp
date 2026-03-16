package seedu.duke.commands;

public class ChildListCommand extends Command {
    
    @Override
    public String execute() {

        if (childList.isEmpty()) {
            return "The child list is empty!";
        }
        StringBuilder sb = new StringBuilder("Here are all children:\n");
        for (int i = 0; i < childList.size(); i++) {
            sb.append((i + 1)).append(". ").append(childList.get(i)).append("\n");
        }
        return sb.toString();
    }
    
}
