//@@author GShubhan
package seedu.clauscontrol.data.todo;

import seedu.clauscontrol.data.exception.IllegalValueException;

import java.time.LocalDate;

/**
 * Represents a todo item with a description and deadline.
 */
public class Todo {
    private String description;
    private LocalDate deadline;

    public Todo(String description, LocalDate deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    //@@author shrabasti-c
    public void setDeadline(LocalDate deadline) throws IllegalValueException {
        this.deadline = deadline;
    }

    public void setDescription(String description) throws IllegalValueException {
        this.description = description;
    }
    //@@author

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public long getDaysUntilDeadline() {
        return LocalDate.now().until(deadline).getDays();
    }

    public boolean isUpcoming() {
        LocalDate today = LocalDate.now();
        return !deadline.isBefore(today) && !deadline.isAfter(today.plusDays(7));
    }

    @Override
    public String toString() {
        return description + " (due: " + deadline + ")";
    }
}
//@@author
