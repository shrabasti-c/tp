//@@author Aurosky

package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.exception.IllegalValueException;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChildListCommandTest {
    
    private ArrayList<Child> childList;
    
    @BeforeEach
    public void setUp() {
        childList = new ArrayList<>();
    }
    
    @Test
    public void execute_emptyList_returnsEmptyMessage() {
        ChildListCommand cmd = new ChildListCommand();
        cmd.setData(childList, new ArrayList<>(), false);
        assertEquals("The child list is empty!", cmd.execute());
    }
    
    @Test
    public void execute_oneChild_returnsFormattedList() throws IllegalValueException {
        childList.add(new Child(new Name("Tom")));
        ChildListCommand cmd = new ChildListCommand();
        cmd.setData(childList, new ArrayList<>(), false);
        String result = cmd.execute();
        assertEquals("Here are all children:\n1. " + childList.get(0).toString(), result);
    }
    
    @Test
    public void execute_multipleChildren_returnsAllIndexed() throws IllegalValueException {
        childList.add(new Child(new Name("Tom")));
        childList.add(new Child(new Name("Jerry")));
        childList.add(new Child(new Name("Spike")));
        ChildListCommand cmd = new ChildListCommand();
        cmd.setData(childList, new ArrayList<>(), false);
        String result = cmd.execute();
        assertEquals("Here are all children:\n1. " + childList.get(0)
                + "\n2. " + childList.get(1)
                + "\n3. " + childList.get(2), result);
    }
}
//@@author
