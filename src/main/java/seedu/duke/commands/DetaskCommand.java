//@@author Aurosky
package seedu.duke.commands;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.data.elf.Elf;
import seedu.duke.data.elf.ElfTask;

public class DetaskCommand extends Command {
    private static final Logger logger = Logger.getLogger(DetaskCommand.class.getName());
    
    private final int elfIndex;
    private final int taskIndex;
    
    public DetaskCommand(int elfIndex, int taskIndex) {
        this.elfIndex = elfIndex;
        this.taskIndex = taskIndex;
        
        logger.log(Level.FINE, "DetaskCommand initialized. ElfIndex: " + elfIndex + ", TaskIndex: " + taskIndex);
    }
    
    @Override
    public String execute() {
        assert elfList != null : "elfList should not be null during execution";
        
        logger.log(Level.INFO, "Attempting to remove task at index " + taskIndex + " from Elf at index " + elfIndex);
        
        if (elfIndex < 1 || elfIndex > elfList.size()) {
            logger.log(Level.WARNING, "Detask failed: Invalid Elf index " + elfIndex);
            return "Error: Invalid Elf index.";
        }
        
        Elf targetElf = elfList.get(elfIndex - 1);
        assert targetElf != null : "The target Elf should not be null";
        ArrayList<ElfTask> tasks = targetElf.getTasks();
        assert tasks != null : "The task list for Elf " + targetElf.getName() + " should not be null";
        
        if (tasks.isEmpty()) {
            logger.log(Level.INFO, "Detask aborted: Elf " + targetElf.getName() + " has no tasks.");
            return "Error: Elf " + targetElf.getName() + " has no tasks to delete.";
        }
        
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            logger.log(Level.WARNING, "Detask failed: Invalid Task index "
                    + taskIndex + " for Elf " + targetElf.getName());
            return "Error: Invalid Task index. This elf has " + tasks.size() + " tasks.";
        }
       
        String removedTaskDesc = tasks.get(taskIndex - 1).toString();
        assert tasks.get(taskIndex - 1) != null : "The task to be removed should not be null";
        targetElf.deleteTask(taskIndex - 1);
        logger.log(Level.INFO, "Successfully removed task [" + removedTaskDesc + "] from Elf: " + targetElf.getName());
        
        return "Success! Removed task [" + removedTaskDesc + "] from " + targetElf.getName();
    }
}
//@@author
