//@@author Aurosky
package seedu.clauscontrol.commands;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.clauscontrol.data.elf.Elf;
import seedu.clauscontrol.data.elf.ElfTask;

public class ElfListCommand extends Command {
    private static final Logger logger = Logger.getLogger(ElfListCommand.class.getName());
    
    @Override
    public String execute() {
        assert elfList != null : "elfList should be initialized before executing ElfListCommand";
        
        logger.log(Level.INFO, "Executing ElfListCommand: displaying all elves and tasks.");
        
        if (elfList.isEmpty()) {
            logger.log(Level.INFO, "Elf list is empty, returning empty message.");
            return "The elf list is empty!";
        }
        
        StringBuilder sb = new StringBuilder("Here are all elves and their tasks:\n");
        
        for (int i = 0; i < elfList.size(); i++) {
            Elf elf = elfList.get(i);
            
            assert elf != null : "Elf at index " + i + " should not be null";
            assert elf.getName() != null : "Elf name at index " + i + " should not be null";
            
            sb.append((i + 1)).append(". ").append(elf.getName());
            
            ArrayList<ElfTask> tasks = elf.getTasks();
            
            assert tasks != null : "Task list for elf " + elf.getName() + " should not be null";
            
            if (tasks.isEmpty()) {
                sb.append(" [No tasks assigned]");
            } else {
                sb.append("\n   Tasks:");
                for (int j = 0; j < tasks.size(); j++) {
                    ElfTask task = tasks.get(j);
                    
                    assert task != null : "Task at index " + j + " for elf " + elf.getName() + " is null";
                    
                    sb.append("\n     - ").append(j + 1).append(": ").append(task.toString());
                }
            }
            sb.append("\n");
        }
        
        String result = sb.toString().trim();
        logger.log(Level.FINE, "Successfully generated elf list string.");
        
        return result;
    }
}
//@@author
