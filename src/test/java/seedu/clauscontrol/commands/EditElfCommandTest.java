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

public class EditElfCommandTest {
    
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
    
    private EditElfCommand buildCmd(int index, String newName) {
        EditElfCommand cmd = new EditElfCommand(index, newName);
        cmd.setData(childList, elfList, false);
        return cmd;
    }
    
    @Test
    public void execute_validIndex_returnsSuccessMessage() throws IllegalValueException {
        String result = buildCmd(1, "Zippy").execute();
        assertEquals(String.format(EditElfCommand.MESSAGE_SUCCESS, "Buddy", "Zippy"), result);
    }
    
    @Test
    public void execute_validIndex_nameUpdated() throws IllegalValueException {
        buildCmd(1, "Zippy").execute();
        assertEquals("Zippy", elfList.get(0).getName().toString());
    }
    
    @Test
    public void execute_firstElf_updatesCorrectElf() throws IllegalValueException {
        buildCmd(1, "Zippy").execute();
        assertEquals("Hermey", elfList.get(1).getName().toString());
        assertEquals("Tinsel", elfList.get(2).getName().toString());
    }
    
    @Test
    public void execute_lastElf_updatesCorrectElf() throws IllegalValueException {
        buildCmd(3, "Zippy").execute();
        assertEquals("Buddy", elfList.get(0).getName().toString());
        assertEquals("Zippy", elfList.get(2).getName().toString());
    }
    
    @Test
    public void execute_middleElf_updatesCorrectElf() throws IllegalValueException {
        buildCmd(2, "Zippy").execute();
        assertEquals("Zippy", elfList.get(1).getName().toString());
    }
    
    @Test
    public void execute_indexZero_returnsErrorMessage() {
        assertEquals("Error: Invalid Elf index.", buildCmd(0, "Zippy").execute());
    }
    
    @Test
    public void execute_indexExceedsSize_returnsErrorMessage() {
        assertEquals("Error: Invalid Elf index.", buildCmd(99, "Zippy").execute());
    }
    
    @Test
    public void execute_negativeIndex_returnsErrorMessage() {
        assertEquals("Error: Invalid Elf index.", buildCmd(-1, "Zippy").execute());
    }
    
    @Test
    public void execute_invalidIndex_nameUnchanged() {
        buildCmd(99, "Zippy").execute();
        assertEquals("Buddy", elfList.get(0).getName().toString());
        assertEquals("Hermey", elfList.get(1).getName().toString());
    }
    
    @Test
    public void execute_invalidName_returnsErrorMessage() {
        String result = buildCmd(1, "").execute();
        assertTrue(result.startsWith("Error:"));
    }
    
    @Test
    public void execute_invalidName_nameUnchanged() {
        buildCmd(1, "").execute();
        assertEquals("Buddy", elfList.get(0).getName().toString());
    }
}
//@@author
