//@@author Aurosky
package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.elf.Elf;
import seedu.clauscontrol.data.elf.ElfTask;
import seedu.clauscontrol.data.exception.IllegalValueException;
import seedu.clauscontrol.data.todo.Todo;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResetCommandTest {
    
    private ArrayList<Child> childList;
    private ArrayList<Elf> elfList;
    private ArrayList<Todo> todoList;
    
    @BeforeEach
    public void setUp() {
        childList = new ArrayList<>();
        elfList = new ArrayList<>();
        todoList = new ArrayList<>();
    }
    
    private ResetCommand buildCmd(boolean isFinalized) {
        ResetCommand cmd = new ResetCommand(todoList);
        cmd.setData(childList, elfList, isFinalized);
        return cmd;
    }
    
    @Test
    public void execute_returnsCorrectMessage() {
        ResetCommand cmd = buildCmd(false);
        assertEquals("Ho ho ho! The system has been fully reset. " +
                "All children (with gifts), elves (with tasks) and todos have been cleared.", cmd.execute());
    }
    
    @Test
    public void execute_emptyLists_returnsSuccessMessage() {
        ResetCommand cmd = buildCmd(false);
        String result = cmd.execute();
        assertTrue(result.contains("reset"));
        assertTrue(childList.isEmpty());
        assertTrue(elfList.isEmpty());
        assertTrue(todoList.isEmpty());
    }
    
    @Test
    public void execute_childListCleared() throws IllegalValueException {
        childList.add(new Child(new Name("Tom")));
        childList.add(new Child(new Name("Jerry")));
        buildCmd(false).execute();
        assertTrue(childList.isEmpty());
    }
    
    @Test
    public void execute_elfListCleared() throws IllegalValueException {
        elfList.add(new Elf(new Name("Buddy")));
        elfList.add(new Elf(new Name("Hermey")));
        buildCmd(false).execute();
        assertTrue(elfList.isEmpty());
    }
    
    @Test
    public void execute_elfWithTasksCleared() throws IllegalValueException {
        Elf elf = new Elf(new Name("Buddy"));
        elf.addTask(new ElfTask("Wrap presents"));
        elfList.add(elf);
        buildCmd(false).execute();
        assertTrue(elfList.isEmpty());
    }
    
    @Test
    public void execute_bothListsCleared() throws IllegalValueException {
        childList.add(new Child(new Name("Tom")));
        elfList.add(new Elf(new Name("Buddy")));
        buildCmd(false).execute();
        assertTrue(childList.isEmpty());
        assertTrue(elfList.isEmpty());
    }
    
    @Test
    public void execute_todoListCleared() {
        todoList.add(new Todo("Buy wrapping paper", java.time.LocalDate.now().plusDays(1)));
        todoList.add(new Todo("Check naughty list", java.time.LocalDate.now().plusDays(2)));
        buildCmd(false).execute();
        assertTrue(todoList.isEmpty());
    }
    
    @Test
    public void execute_isFinalizedReset() throws IllegalValueException {
        ResetCommand cmd = buildCmd(true);
        cmd.execute();
        assertEquals(false, cmd.isFinalized);
    }
}
//@@author
