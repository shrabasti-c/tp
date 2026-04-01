//@@author Aurosky
package seedu.clauscontrol.data.elf;
// Similar to child class

public class ElfTask {
    private String task;
    // no special constrains to task name, use string is enough
    public ElfTask(String task) {
        this.task = task;
    }
    
    @Override
    public String toString() {
        return this.task;
    }
}
//@@author
