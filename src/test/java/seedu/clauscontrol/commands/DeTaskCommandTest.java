//@@author Aurosky
package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.elf.Elf;
import seedu.clauscontrol.data.elf.ElfTask;
import seedu.clauscontrol.data.exception.IllegalValueException;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeTaskCommandTest {
    
    private ArrayList<Elf> elfList;
    private Elf dobby;
    
    @BeforeEach
    public void setUp() throws IllegalValueException {
        elfList = new ArrayList<>();
        dobby = new Elf(new Name("Dobby"));
        dobby.addTask(new ElfTask("wrap gifts"));
        dobby.addTask(new ElfTask("polish sleigh"));
        elfList.add(dobby);
    }
    
    @Test
    public void execute_validIndices_removesTask() {
        DetaskCommand cmd = new DetaskCommand(1, 1);
        cmd.setData(new ArrayList<>(), elfList, false);
        String result = cmd.execute();
        assertEquals("Success! Removed task [wrap gifts] from Dobby", result);
        assertEquals(1, dobby.getTasks().size());
        assertEquals("polish sleigh", dobby.getTasks().get(0).toString());
    }
    
    @Test
    public void execute_invalidElfIndex_returnsError() {
        DetaskCommand cmd = new DetaskCommand(99, 1);
        cmd.setData(new ArrayList<>(), elfList, false);
        assertEquals("Error: Invalid Elf index.", cmd.execute());
    }
    
    @Test
    public void execute_invalidTaskIndex_returnsError() {
        DetaskCommand cmd = new DetaskCommand(1, 99);
        cmd.setData(new ArrayList<>(), elfList, false);
        assertEquals("Error: Invalid Task index. This elf has 2 tasks.", cmd.execute());
    }
    
    @Test
    public void execute_elfHasNoTasks_returnsError() throws IllegalValueException {
        Elf emptyElf = new Elf(new Name("Tinsel"));
        elfList.add(emptyElf);
        DetaskCommand cmd = new DetaskCommand(2, 1);
        cmd.setData(new ArrayList<>(), elfList, false);
        assertEquals("Error: Elf Tinsel has no tasks to delete.", cmd.execute());
    }
    
    @Test
    public void execute_lastTask_elfBecomesEmpty() {
        dobby.getTasks().clear();
        dobby.addTask(new ElfTask("sort letters"));
        DetaskCommand cmd = new DetaskCommand(1, 1);
        cmd.setData(new ArrayList<>(), elfList, false);
        cmd.execute();
        assertTrue(dobby.getTasks().isEmpty());
    }
}
//@@author
