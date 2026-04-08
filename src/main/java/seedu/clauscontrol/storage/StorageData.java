//@@author prerana-r11
package seedu.clauscontrol.storage;

import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.elf.Elf;

import java.util.List;

public class StorageData {
    public List<Child> children;
    public List<Elf> elves;
    public boolean isFinalized;

    public StorageData(List<Child> children, List<Elf> elves, boolean isFinalized) {
        this.children = children;
        this.elves = elves;
        this.isFinalized = isFinalized;

    }
}
//@@author
