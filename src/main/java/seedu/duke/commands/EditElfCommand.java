//@@author Kiri
package seedu.duke.commands;

import seedu.duke.data.child.Name;
import seedu.duke.data.elf.Elf;
import seedu.duke.data.exception.IllegalValueException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EditElfCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Elf name updated! From [%1$s] to [%2$s]";
    private static final Logger logger = Logger.getLogger(EditElfCommand.class.getName());
    
    private final int elfIndex;
    private final String newName;
    
    public EditElfCommand(int elfIndex, String newName) {
        this.elfIndex = elfIndex;
        this.newName = newName;
        logger.log(Level.FINE, "EditElfCommand initialized. Index: " + elfIndex + ", New Name: " + newName);
    }
    
    @Override
    public String execute() {
        assert elfList != null : "elfList must be initialized before executing EditElfCommand";
        
        if (elfIndex < 1 || elfIndex > elfList.size()) {
            logger.log(Level.WARNING, "EditElf failed: Invalid index " + elfIndex);
            return "Error: Invalid Elf index.";
        }
        
        Elf targetElf = elfList.get(elfIndex - 1);
        assert targetElf != null : "Target Elf should not be null";
        
        String oldName = targetElf.getName().toString();
        
        try {
            logger.log(Level.INFO, "Updating Elf name at index " + elfIndex);
            targetElf.setName(new Name(newName));
            
            logger.log(Level.INFO, "Elf name changed successfully.");
            return String.format(MESSAGE_SUCCESS, oldName, newName);
            
        } catch (IllegalValueException e) {
            logger.log(Level.WARNING, "Failed to create Name object: " + newName);
            return "Error: " + e.getMessage();
        }
    }
}
//@@author
