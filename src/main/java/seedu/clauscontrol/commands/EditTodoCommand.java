package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.exception.IllegalValueException;
import seedu.clauscontrol.data.todo.Todo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author shrabasti-c
/**
 * Edits a task in the todolist.
 */
public class EditTodoCommand extends Command {
    private static final Logger logger = Logger.getLogger("Foo");
    private final ArrayList<Todo> todoList;
    private final int index;
    private final String newDescription;
    private final LocalDate newDeadline;

    public EditTodoCommand(ArrayList<Todo> todoList, int index, String newDescription, LocalDate newDeadline) {
        this.todoList = todoList;
        this.index = index;
        this.newDescription = newDescription;
        this.newDeadline = newDeadline;
        logger.log(Level.INFO, "successful edit todo instantiation");
    }

    @Override
    public String execute() {
        if (index < 0 || index >= todoList.size()) {
            logger.log(Level.WARNING, "unsuccessful edit todo execution");
            return "Invalid position :(";
        }

        Todo todo = todoList.get(index);

        try {
            if (newDeadline != null) {
                todo.setDeadline(newDeadline);
            }

            if (newDescription != null) {
                todo.setDescription(newDescription);
            }

        } catch (IllegalValueException e) {
            logger.log(Level.INFO, "unsuccessful edit todo execution");
            throw new RuntimeException(e);
        }
        logger.log(Level.INFO, "successful edit todo execution");
        return "Todo Details changed!";
    }
}
//@@author

