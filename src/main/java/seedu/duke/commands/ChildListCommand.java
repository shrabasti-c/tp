//@@author Aurosky
package seedu.duke.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ChildListCommand extends Command {
    private static final Logger logger = Logger.getLogger(ChildListCommand.class.getName());
    
    @Override
    public String execute() {

        assert childList != null : "childList should be initialized before execution";
        
        logger.log(Level.INFO, "Executing ChildListCommand...");
        
        if (childList.isEmpty()) {
            logger.log(Level.WARNING, "Child list is empty.");
            return "The child list is empty!";
        }
        
        logger.log(Level.INFO, "Displaying " + childList.size() + " children.");
        
        StringBuilder sb = new StringBuilder("Here are all children:\n");
        
        for (int i = 0; i < childList.size(); i++) {
            assert childList.get(i) != null : "Child object at index " + i + " should not be null";
            sb.append((i + 1)).append(". ").append(childList.get(i)).append("\n");
        }
        
        return sb.toString().trim();
    }
}
//@@author
