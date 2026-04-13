//@@author Aurosky
package seedu.clauscontrol.commands;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.clauscontrol.data.todo.Todo;

public class ResetCommand extends Command {
    private static final Logger logger = Logger.getLogger(ResetCommand.class.getName());
    private final ArrayList<Todo> todoList;                // ← 加这个
    
    public ResetCommand(ArrayList<Todo> todoList) {        // ← 加构造函数
        this.todoList = todoList;
    }
    
    @Override
    public String execute() {
        logger.log(Level.INFO, "Initiating full system reset...");
        isFinalized = false;
        
        if (childList != null) {
            childList.clear();
        }
        if (elfList != null) {
            elfList.clear();
        }
        if (todoList != null) {                            // ← 加这个
            todoList.clear();
        }
        
        logger.log(Level.INFO, "Reset complete: all children, gifts, elves, tasks and todos removed.");
        
        return "Ho ho ho! The system has been fully reset. " +
                "All children (with gifts), elves (with tasks) and todos have been cleared.";
    }
}
//@@author
