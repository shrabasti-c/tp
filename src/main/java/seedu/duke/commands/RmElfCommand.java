//@@author Aurosky
package seedu.duke.commands;

import seedu.duke.data.elf.Elf;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RmElfCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Elf removed: %1$s";
    private static final Logger logger = Logger.getLogger(RmElfCommand.class.getName());
    
    private final int elfIndex;
    
    public RmElfCommand(int elfIndex) {
        this.elfIndex = elfIndex;
        logger.log(Level.FINE, "RmElfCommand initialized for index: " + elfIndex);
    }
    
    @Override
    public String execute() {
        assert elfList != null : "elfList must be initialized before executing RmElfCommand";
        
        if (elfIndex < 1 || elfIndex > elfList.size()) {
            logger.log(Level.WARNING, "RmElf failed: Invalid index " + elfIndex);
            return "Error: Invalid Elf index.";
        }
        
        Elf removedElf = elfList.get(elfIndex - 1);
        assert removedElf != null : "Elf to be removed should exist";
        
        logger.log(Level.INFO, "Removing Elf: " + removedElf.getName());
        
        elfList.remove(elfIndex - 1);
        
        logger.log(Level.INFO, "Elf removed successfully.");
        return String.format(MESSAGE_SUCCESS, removedElf.getName());
    }
}
//@@author
