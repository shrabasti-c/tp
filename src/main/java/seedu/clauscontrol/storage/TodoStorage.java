//@@author GShubhan
package seedu.clauscontrol.storage;

import seedu.clauscontrol.data.todo.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles saving and loading of todo items.
 */
public class TodoStorage {
    private final String filePath;

    public TodoStorage(String filePath) {
        this.filePath = filePath;
    }

    public void save(List<Todo> todos) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Todo todo : todos) {
            writer.write(todo.getDescription() + "|" + todo.getDeadline());
            writer.newLine();
        }
        writer.close();
    }

    public List<Todo> load() throws IOException {
        List<Todo> todos = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length == 2) {
                String description = parts[0];
                LocalDate deadline = LocalDate.parse(parts[1]);
                todos.add(new Todo(description, deadline));
            }
        }
        reader.close();
        return todos;
    }
}
//@@author
