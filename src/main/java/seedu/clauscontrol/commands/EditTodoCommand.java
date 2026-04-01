package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.exception.IllegalValueException;
import seedu.clauscontrol.data.todo.Todo;
import java.time.LocalDate;
import java.util.ArrayList;

//@@author shrabasti-c
/**
 * Edits a task in the todolist.
 */
public class EditTodoCommand extends Command {
    private final ArrayList<Todo> todoList;
    private final int index;
    private final String newDescription;
    private final LocalDate newDeadline;

    public EditTodoCommand(ArrayList<Todo> todoList, int index, String newDescription, LocalDate newDeadline) {
        this.todoList = todoList;
        this.index = index;
        this.newDescription = newDescription;
        this.newDeadline = newDeadline;
    }

    @Override
    public String execute() {
        if (index < 0 || index >= todoList.size()) {
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
            throw new RuntimeException(e);
        }
        return "Todo Details changed!";
    }
}
//@@author

