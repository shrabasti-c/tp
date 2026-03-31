//@@author Aurosky
package seedu.duke.data.elf;
import java.util.ArrayList;

import seedu.duke.data.child.Name;
// reuse child class code

public class Elf implements ReadOnlyElf {
    private Name name;
    private ArrayList<ElfTask> tasks; // 改为列表
    
    public Elf(Name name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }
    
    public void addTask(ElfTask task) {
        this.tasks.add(task);
    }
    
    public ArrayList<ElfTask> getTasks() {
        return tasks;
    }
    
    public void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.remove(taskIndex);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name.toString());
        if (tasks.isEmpty()) {
            sb.append(" (No tasks)");
        } else {
            sb.append(" (Tasks: ");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i));
                if (i < tasks.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }
    
    @Override
    public Name getName() {
        return name;
    }
    
    @Override
    public void setName(Name name) {
        assert name != null : "New name cannot be null";
        this.name = name;
    }
    
}
//@@author
