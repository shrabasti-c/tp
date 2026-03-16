package seedu.duke.data.elf;
// similar to child class,

public class ElfTask {
    private String task;
    // no special constrains to task name, use string is enough
    public ElfTask(String task) {
        this.task = task;
    }
    
    public String getTask() {
        return task;
    }
    
    public void editTask(String newtask) {
        this.task = newtask;
    }
    
}
