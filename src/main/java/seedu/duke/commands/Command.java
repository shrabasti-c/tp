//Solution below adapted from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook
// /commands/Command.java and shrabasti-c's iP
package seedu.duke.commands;
import seedu.duke.data.child.Child;
import seedu.duke.data.elf.Elf;

import java.util.ArrayList;

public abstract class Command {
    protected ArrayList<Child> childList;
    protected ArrayList<Elf> elfList;

    protected Command() {
    }

    /**
     * Executes the command and returns the result.
     */
    public abstract String execute();

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(ArrayList<Child> childList) {
        this.childList = childList;
    }
}
