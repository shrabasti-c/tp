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
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RmElfCommandTest {
    
    private ArrayList<Child> childList;
    private ArrayList<Elf> elfList;
    
    @BeforeEach
    public void setUp() throws IllegalValueException {
        childList = new ArrayList<>();
        elfList = new ArrayList<>();
        elfList.add(new Elf(new Name("Buddy")));
        elfList.add(new Elf(new Name("Hermey")));
        elfList.add(new Elf(new Name("Tinsel")));
    }
    
    private RmElfCommand buildCmd(int index) {
        RmElfCommand cmd = new RmElfCommand(index);
        cmd.setData(childList, elfList, false);
        return cmd;
    }
    
    @Test
    public void execute_validIndex_returnsSuccessMessage() {
        String result = buildCmd(1).execute();
        assertEquals(String.format(RmElfCommand.MESSAGE_SUCCESS, "Buddy"), result);
    }
    
    @Test
    public void execute_validIndex_elfRemovedFromList() {
        buildCmd(1).execute();
        assertEquals(2, elfList.size());
        assertFalse(elfList.stream().anyMatch(e -> e.getName().toString().equals("Buddy")));
    }
    
    @Test
    public void execute_firstElf_removesCorrectElf() {
        buildCmd(1).execute();
        assertEquals("Hermey", elfList.get(0).getName().toString());
    }
    
    @Test
    public void execute_lastElf_removesCorrectElf() {
        buildCmd(3).execute();
        assertEquals(2, elfList.size());
        assertFalse(elfList.stream().anyMatch(e -> e.getName().toString().equals("Tinsel")));
    }
    
    @Test
    public void execute_middleElf_removesCorrectElf() {
        buildCmd(2).execute();
        assertEquals(2, elfList.size());
        assertFalse(elfList.stream().anyMatch(e -> e.getName().toString().equals("Hermey")));
    }
    
    @Test
    public void execute_indexZero_returnsErrorMessage() {
        assertEquals("Error: Invalid Elf index.", buildCmd(0).execute());
    }
    
    @Test
    public void execute_indexExceedsSize_returnsErrorMessage() {
        assertEquals("Error: Invalid Elf index.", buildCmd(99).execute());
    }
    
    @Test
    public void execute_negativeIndex_returnsErrorMessage() {
        assertEquals("Error: Invalid Elf index.", buildCmd(-1).execute());
    }
    
    @Test
    public void execute_invalidIndex_listUnchanged() {
        buildCmd(99).execute();
        assertEquals(3, elfList.size());
    }
}
//@@author
