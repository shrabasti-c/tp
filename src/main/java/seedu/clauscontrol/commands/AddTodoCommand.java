//@@author GShubhan
package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.todo.Todo;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Adds a todo item to the todo list.
 */
public class AddTodoCommand extends Command {
    private static final Logger logger = Logger.getLogger(AddTodoCommand.class.getName());
    private final String arguments;
    private final ArrayList<Todo> todoList;

    public AddTodoCommand(String arguments, ArrayList<Todo> todoList) {
        this.arguments = arguments;
        this.todoList = todoList;
    }

    @Override
    public String execute() {
        try {
            if (arguments == null || arguments.trim().isEmpty()) {
                return "Format: todo d/DESCRIPTION by/YYYY-MM-DD";
            }

            int dIndex = arguments.indexOf("d/");
            int byIndex = arguments.indexOf("by/");

            if (dIndex == -1 || byIndex == -1) {
                return "Format: todo d/DESCRIPTION by/YYYY-MM-DD";
            }

            if (dIndex >= byIndex) {
                return "Format: todo d/DESCRIPTION by/YYYY-MM-DD";
            }

            String description = arguments.substring(dIndex + 2, byIndex).trim();
            String dateStr = arguments.substring(byIndex + 3).trim();

            if (description.isEmpty()) {
                return "Description cannot be empty!";
            }

            if (dateStr.isEmpty()) {
                return "Deadline cannot be empty! Please use YYYY-MM-DD";
            }

            // reject non-ASCII or unusual characters in description
            if (!description.matches("[\\x20-\\x7E]+")) {
                return "Description contains invalid characters!";
            }

            if (!dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return "Invalid date format! Please use YYYY-MM-DD e.g. 2026-04-05";
            }

            LocalDate deadline;
            try {
                deadline = LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                return "Invalid date value! \"" + dateStr + "\" is not a real date.";
            }

            if (deadline.isBefore(LocalDate.now())) {
                return "Deadline cannot be in the past!";
            }

            for (Todo existing : todoList) {
                if (existing.getDescription().equals(description)
                        && existing.getDeadline().equals(deadline)) {
                    return "Duplicate todo! A todo with the same description and deadline already exists.";
                }
            }

            todoList.add(new Todo(description, deadline));

            logger.log(Level.INFO, "Todo added: " + description);
            return "Todo added: " + description + " (due: " + deadline + ")";

        } catch (Exception e) {
            logger.log(Level.INFO, "Unexpected error in AddTodoCommand");
            return "Something went wrong! Format: todo d/DESCRIPTION by/YYYY-MM-DD";
        }
    }
}
//@@author
