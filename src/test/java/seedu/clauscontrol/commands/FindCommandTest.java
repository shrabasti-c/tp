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

public class FindCommandTest {
    
    private ArrayList<Child> childList;
    private ArrayList<Elf> elfList;
    
    @BeforeEach
    public void setUp() throws IllegalValueException {
        childList = new ArrayList<>();
        elfList = new ArrayList<>();
    }
    
    private void setCmd(FindCommand cmd) {
        cmd.setData(childList, elfList, false);
    }
    
    @Test
    public void execute_emptyList_returnsEmptyMessage() {
        FindCommand cmd = new FindCommand("Tom", FindCommand.SearchType.NAME);
        setCmd(cmd);
        assertEquals("The child list is empty!", cmd.execute());
    }
    
    @Test
    public void execute_nameExactMatch_returnsMatch() throws IllegalValueException {
        childList.add(new Child(new Name("Tom")));
        FindCommand cmd = new FindCommand("Tom", FindCommand.SearchType.NAME);
        setCmd(cmd);
        assertTrue(cmd.execute().contains("Tom"));
    }
    
    @Test
    public void execute_namePartialMatch_returnsMatch() throws IllegalValueException {
        childList.add(new Child(new Name("Tommy")));
        FindCommand cmd = new FindCommand("tom", FindCommand.SearchType.NAME);
        setCmd(cmd);
        assertTrue(cmd.execute().contains("Tommy"));
    }
    
    @Test
    public void execute_nameCaseInsensitive_returnsMatch() throws IllegalValueException {
        childList.add(new Child(new Name("Alice")));
        FindCommand cmd = new FindCommand("ALICE", FindCommand.SearchType.NAME);
        setCmd(cmd);
        assertTrue(cmd.execute().contains("Alice"));
    }
    
    @Test
    public void execute_nameNoMatch_returnsNoMatchMessage() throws IllegalValueException {
        childList.add(new Child(new Name("Bob")));
        FindCommand cmd = new FindCommand("xyz", FindCommand.SearchType.NAME);
        setCmd(cmd);
        assertEquals("No match found for name: xyz", cmd.execute());
    }
    
    @Test
    public void execute_multipleNameMatches_returnsCorrectCount() throws IllegalValueException {
        childList.add(new Child(new Name("Tom")));
        childList.add(new Child(new Name("Tommy")));
        childList.add(new Child(new Name("Jerry")));
        FindCommand cmd = new FindCommand("tom", FindCommand.SearchType.NAME);
        setCmd(cmd);
        String result = cmd.execute();
        assertTrue(result.startsWith("Found 2 matches:"));
    }
    
    @Test
    public void execute_ageMatch_returnsMatch() throws IllegalValueException {
        Child child = new Child(new Name("Tom"));
        child.setAge(10);
        childList.add(child);
        FindCommand cmd = new FindCommand("10", FindCommand.SearchType.AGE);
        setCmd(cmd);
        assertTrue(cmd.execute().contains("Tom"));
    }
    
    @Test
    public void execute_ageNoMatch_returnsNoMatchMessage() throws IllegalValueException {
        Child child = new Child(new Name("Tom"));
        child.setAge(10);
        childList.add(child);
        FindCommand cmd = new FindCommand("99", FindCommand.SearchType.AGE);
        setCmd(cmd);
        assertEquals("No match found for age: 99", cmd.execute());
    }
    
    @Test
    public void execute_ageSearchChildHasNoAge_returnsNoMatch() throws IllegalValueException {
        childList.add(new Child(new Name("Tom")));
        FindCommand cmd = new FindCommand("10", FindCommand.SearchType.AGE);
        setCmd(cmd);
        assertEquals("No match found for age: 10", cmd.execute());
    }
    
    @Test
    public void execute_locationMatch_returnsMatch() throws IllegalValueException {
        Child child = new Child(new Name("Tom"));
        child.setLocation("North Pole");
        childList.add(child);
        FindCommand cmd = new FindCommand("north", FindCommand.SearchType.LOCATION);
        setCmd(cmd);
        assertTrue(cmd.execute().contains("Tom"));
    }
    
    @Test
    public void execute_locationNoMatch_returnsNoMatchMessage() throws IllegalValueException {
        Child child = new Child(new Name("Tom"));
        child.setLocation("North Pole");
        childList.add(child);
        FindCommand cmd = new FindCommand("south", FindCommand.SearchType.LOCATION);
        setCmd(cmd);
        assertEquals("No match found for location: south", cmd.execute());
    }
    
    @Test
    public void execute_locationSearchChildHasNoLocation_returnsNoMatch() throws IllegalValueException {
        childList.add(new Child(new Name("Tom")));
        FindCommand cmd = new FindCommand("north", FindCommand.SearchType.LOCATION);
        setCmd(cmd);
        assertEquals("No match found for location: north", cmd.execute());
    }
    
    @Test
    public void execute_resultContainsIndex_returnsCorrectIndex() throws IllegalValueException {
        childList.add(new Child(new Name("Alice")));
        childList.add(new Child(new Name("Bob")));
        FindCommand cmd = new FindCommand("bob", FindCommand.SearchType.NAME);
        setCmd(cmd);
        assertTrue(cmd.execute().contains("[2]"));
    }
}
//@@author
