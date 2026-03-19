//@@author Kiri
package seedu.duke.commands;

import seedu.duke.data.child.Name;
import seedu.duke.data.elf.Elf;
import seedu.duke.data.exception.IllegalValueException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ElfCommand extends Command {
    private static final Logger logger = Logger.getLogger(ElfCommand.class.getName());
    
    public static final String MESSAGE_SUCCESS = "Ho ho ho! New elf added: %1$s";
    private final Elf toAdd;
    
    public ElfCommand(String name) throws IllegalValueException {
        logger.log(Level.FINE, "Creating ElfCommand for: " + name);
        this.toAdd = new Elf(new Name(name));
    }
    
    @Override
    public String execute() {
        logger.log(Level.INFO, "Adding a new elf to the list: " + toAdd.getName());
        elfList.add(toAdd);
        logger.log(Level.INFO, "Elf added successfully.");
        return String.format(MESSAGE_SUCCESS, toAdd);
    }
}
//@@author
