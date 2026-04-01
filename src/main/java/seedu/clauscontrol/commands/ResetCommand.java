//@@author Aurosky
package seedu.clauscontrol.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ResetCommand extends Command {
    private static final Logger logger = Logger.getLogger(ResetCommand.class.getName());
    
    @Override
    public String execute() {
        logger.log(Level.INFO, "Initiating full system reset...");
        
        if (childList != null) {
            childList.clear();
        }

        if (elfList != null) {
            elfList.clear();
        }
        
        logger.log(Level.INFO, "Reset complete: all children, gifts, elves, and tasks removed.");
        
        return "Ho ho ho! The system has been fully reset. " +
                "All children (with gifts) and elves (with tasks) have been cleared.";
    }
}
//@@author
