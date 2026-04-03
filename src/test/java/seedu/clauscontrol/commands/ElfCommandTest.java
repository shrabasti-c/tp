//@@author Aurosky
package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.elf.Elf;
import seedu.clauscontrol.data.exception.IllegalValueException;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ElfCommandTest {
    
    private ArrayList<Elf> elfList;
    
    @BeforeEach
    public void setUp() {
        elfList = new ArrayList<>();
    }
    
    @Test
    public void execute_validName_addsElf() throws IllegalValueException {
        ElfCommand cmd = new ElfCommand("Dobby");
        cmd.setData(new ArrayList<>(), elfList, false);
        String result = cmd.execute();
        assertEquals(1, elfList.size());
        assertEquals("Ho ho ho! New elf added: Dobby", result);
    }
    
    @Test
    public void execute_multipleElves_allAdded() throws IllegalValueException {
        ElfCommand cmd1 = new ElfCommand("Dobby");
        ElfCommand cmd2 = new ElfCommand("Tinsel");
        cmd1.setData(new ArrayList<>(), elfList, false);
        cmd2.setData(new ArrayList<>(), elfList, false);
        cmd1.execute();
        cmd2.execute();
        assertEquals(2, elfList.size());
        assertEquals("Dobby", elfList.get(0).getName().toString());
        assertEquals("Tinsel", elfList.get(1).getName().toString());
    }
    
    @Test
    public void constructor_invalidName_throwsIllegalValueException() {
        assertThrows(IllegalValueException.class, () -> new ElfCommand(""));
    }
}
//@@author
