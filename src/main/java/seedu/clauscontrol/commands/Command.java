package seedu.clauscontrol.commands;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.elf.Elf;

import java.util.ArrayList;

//@@author shrabasti-c
/* Adapted from AddCommand Class of AB2 application and author's iP.
 * Link: https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/commands/Command.java
 * Link: https://github.com/shrabasti-c/ip
 * with some modifications
 */
/**
 * Serves as a baseline *Command template.
 */
public abstract class Command {
    protected ArrayList<Child> childList;
    protected ArrayList<Elf> elfList;
    protected boolean isFinalized = false;

    protected Command() {
    }

    public abstract String execute();

    public void setData(ArrayList<Child> childList, ArrayList<Elf> elfList, boolean isFinalized) {
        this.childList = childList;
        this.elfList = elfList;
        this.isFinalized = isFinalized;
    }
}
//@@author
