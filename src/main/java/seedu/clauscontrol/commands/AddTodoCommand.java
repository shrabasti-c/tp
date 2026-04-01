//@@author GShubhan
package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.todo.Todo;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Adds a todo item to the todo list.
 */
public class AddTodoCommand extends Command {
    private final String arguments;
    private final ArrayList<Todo> todoList;

    public AddTodoCommand(String arguments, ArrayList<Todo> todoList) {
        this.arguments = arguments;
        this.todoList = todoList;
    }

    @Override
    public String execute() {
        try {
            int dIndex = arguments.indexOf("d/");
            int byIndex = arguments.indexOf("by/");

            if (dIndex == -1 || byIndex == -1) {
                return "Format: todo d/DESCRIPTION by/YYYY-MM-DD";
            }

            String description = arguments.substring(dIndex + 2, byIndex).trim();
            String dateStr = arguments.substring(byIndex + 3).trim();
            LocalDate deadline = LocalDate.parse(dateStr);

            todoList.add(new Todo(description, deadline));
            return "Todo added: " + description + " (due: " + deadline + ")";
        } catch (DateTimeParseException e) {
            return "Invalid date format! Please use YYYY-MM-DD e.g. 2026-04-05";
        }
    }
}
//@@author
