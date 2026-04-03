//@@author Aurosky
package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.elf.Elf;
import seedu.clauscontrol.data.exception.IllegalValueException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskCommandTest {
    
    private ArrayList<Child> childList;
    private ArrayList<Elf> elfList;
    
    @BeforeEach
    public void setUp() throws IllegalValueException {
        childList = new ArrayList<>();
        elfList = new ArrayList<>();
        elfList.add(new Elf(new Name("Buddy")));
        elfList.add(new Elf(new Name("Hermey")));
    }
    
    private TaskCommand buildCmd(int index, String task) {
        TaskCommand cmd = new TaskCommand(index, task);
        cmd.setData(childList, elfList, false);
        return cmd;
    }
    
    @Test
    public void execute_validIndex_returnsSuccessMessage() {
        String result = buildCmd(1, "Wrap presents").execute();
        assertEquals(String.format(TaskCommand.MESSAGE_SUCCESS, "Wrap presents", "Buddy"), result);
    }
    
    @Test
    public void execute_validIndex_taskAddedToElf() {
        buildCmd(1, "Wrap presents").execute();
        assertEquals(1, elfList.get(0).getTasks().size());
        assertEquals("Wrap presents", elfList.get(0).getTasks().get(0).toString());
    }
    
    @Test
    public void execute_lastElf_taskAddedCorrectly() {
        buildCmd(2, "Paint toys").execute();
        assertEquals(1, elfList.get(1).getTasks().size());
        assertEquals("Paint toys", elfList.get(1).getTasks().get(0).toString());
    }
    
    @Test
    public void execute_multipleTasksSameElf_allTasksAdded() {
        buildCmd(1, "Wrap presents").execute();
        buildCmd(1, "Paint toys").execute();
        assertEquals(2, elfList.get(0).getTasks().size());
    }
    
    @Test
    public void execute_taskAssignedToCorrectElf_otherElfUnchanged() {
        buildCmd(1, "Wrap presents").execute();
        assertTrue(elfList.get(1).getTasks().isEmpty());
    }
    
    @Test
    public void execute_indexZero_returnsInvalidIndexMessage() {
        String result = buildCmd(0, "Wrap presents").execute();
        assertEquals(String.format(TaskCommand.MESSAGE_INVALID_INDEX, elfList.size()), result);
    }
    
    @Test
    public void execute_indexExceedsSize_returnsInvalidIndexMessage() {
        String result = buildCmd(99, "Wrap presents").execute();
        assertEquals(String.format(TaskCommand.MESSAGE_INVALID_INDEX, elfList.size()), result);
    }
    
    @Test
    public void execute_negativeIndex_returnsInvalidIndexMessage() {
        String result = buildCmd(-1, "Wrap presents").execute();
        assertEquals(String.format(TaskCommand.MESSAGE_INVALID_INDEX, elfList.size()), result);
    }
    
    @Test
    public void execute_invalidIndex_noTaskAdded() {
        buildCmd(99, "Wrap presents").execute();
        assertTrue(elfList.get(0).getTasks().isEmpty());
        assertTrue(elfList.get(1).getTasks().isEmpty());
    }
}
//@@author
