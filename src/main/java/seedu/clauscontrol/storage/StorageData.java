package seedu.clauscontrol.storage;

import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.elf.Elf;

import java.util.List;

public class StorageData {
    public List<Child> children;
    public List<Elf> elves;

    public StorageData(List<Child> children, List<Elf> elves) {
        this.children = children;
        this.elves = elves;
    }
}