//@@author Kiri
package seedu.duke.commands;

import seedu.duke.data.elf.Elf;
import seedu.duke.data.elf.ElfTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Ho ho ho! Task [%1$s] assigned to Elf: %2$s";
    public static final String MESSAGE_INVALID_INDEX = "Error: Invalid Elf index. You have %1$d elves.";
    
    private static final Logger logger = Logger.getLogger(TaskCommand.class.getName());
    
    private final int targetIndex;
    private final String taskContent;
    
    public TaskCommand(int targetIndex, String taskContent) {
        assert taskContent != null : "Task content should not be null";
        assert !taskContent.trim().isEmpty() : "Task content should not be empty";
        
        this.targetIndex = targetIndex;
        this.taskContent = taskContent;
        
        logger.log(Level.FINE, "TaskCommand initialized for Elf index: " + targetIndex);
    }
    
    @Override
    public String execute() {
        
        assert elfList != null : "elfList must be initialized before execution";
        logger.log(Level.INFO, "Attempting to assign task to Elf at index " + targetIndex);
        
        if (targetIndex < 1 || targetIndex > elfList.size()) {
            logger.log(Level.WARNING, "Task assignment failed: Invalid index " + targetIndex);
            return String.format(MESSAGE_INVALID_INDEX, elfList.size());
        }
        Elf targetElf = elfList.get(targetIndex - 1);
        assert targetElf != null : "Target Elf at index " + (targetIndex - 1) + " should not be null";
        ElfTask newTask = new ElfTask(taskContent);
        targetElf.addTask(newTask);
        logger.log(Level.INFO, "Successfully assigned task [" + taskContent + "] to Elf: " + targetElf.getName());
        return String.format(MESSAGE_SUCCESS, taskContent, targetElf.getName());
    }
}
//@@author
