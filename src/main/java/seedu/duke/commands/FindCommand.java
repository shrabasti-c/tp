package seedu.duke.commands;

import seedu.duke.data.child.Child;

public class FindCommand extends Command{
    private final String targetName;
    
    public FindCommand(String targetName) {
        this.targetName = targetName.trim().toLowerCase();
    }
    
    @Override
    public String execute() {
        if (childList == null || childList.isEmpty()) {
            return "The child list is empty!";
        }
        
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < childList.size(); i++) {
            Child child = childList.get(i);
            if (child.getName().toString().toLowerCase().equals(targetName)) {
                sb.append((i + 1)).append(" ").append(child.getName()).append("\n");
                count++;
            }
        }
        
        return (count == 0) ? "No match found." : "Found " + count + " matches:\n" + sb.toString().trim();
    }
}
