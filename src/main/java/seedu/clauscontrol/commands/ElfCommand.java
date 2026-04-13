//@@author Aurosky
package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.elf.Elf;
import seedu.clauscontrol.data.exception.IllegalValueException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ElfCommand extends Command {
    
    public static final String MESSAGE_SUCCESS = "Ho ho ho! New elf added: %1$s";
    
    private static final Logger logger = Logger.getLogger(ElfCommand.class.getName());
    
    private final Elf toAdd;
    
    public ElfCommand(String name) throws IllegalValueException {
        logger.log(Level.FINE, "Creating ElfCommand for: " + name);
        this.toAdd = new Elf(new Name(name));
        
        assert toAdd != null : "Elf object should be successfully created in constructor";
    }
    
    @Override
    public String execute() {
        assert elfList != null : "elfList must be initialized before executing ElfCommand";
        assert toAdd != null : "toAdd should not be null when executing add operation";
        
        for (Elf elf : elfList) {
            if (elf.getName().toString().equalsIgnoreCase(toAdd.getName().toString())) {
                return "An elf with the name '" + toAdd.getName() + "' already exists!";
            }
        }
        
        elfList.add(toAdd);
        logger.log(Level.INFO, "Elf added successfully.");
        return String.format(MESSAGE_SUCCESS, toAdd.getName());
    }
}
//@@author
