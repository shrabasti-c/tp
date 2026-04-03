//@@author Aurosky
package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.elf.Elf;
import seedu.clauscontrol.data.elf.ElfTask;
import seedu.clauscontrol.data.child.Name;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElfListCommandTest {
    
    private ArrayList<Child> childList;
    private ArrayList<Elf> elfList;
    
    @BeforeEach
    public void setUp() {
        childList = new ArrayList<>();
        elfList = new ArrayList<>();
    }
    
    @Test
    public void execute_emptyList_returnsEmptyMessage() {
        ElfListCommand cmd = new ElfListCommand();
        cmd.setData(childList, elfList, false);
        assertEquals("The elf list is empty!", cmd.execute());
    }
    
    @Test
    public void execute_oneElfNoTasks_returnsNoTasksLabel() throws Exception {
        elfList.add(new Elf(new Name("Buddy")));
        ElfListCommand cmd = new ElfListCommand();
        cmd.setData(childList, elfList, false);
        
        String expected = "Here are all elves and their tasks:\n"
                + "1. Buddy [No tasks assigned]";
        assertEquals(expected, cmd.execute());
    }
    
    @Test
    public void execute_multipleElvesNoTasks_returnsAllIndexed() throws Exception {
        elfList.add(new Elf(new Name("Buddy")));
        elfList.add(new Elf(new Name("Hermey")));
        elfList.add(new Elf(new Name("Tinsel")));
        ElfListCommand cmd = new ElfListCommand();
        cmd.setData(childList, elfList, false);
        
        String expected = "Here are all elves and their tasks:\n"
                + "1. Buddy [No tasks assigned]\n"
                + "2. Hermey [No tasks assigned]\n"
                + "3. Tinsel [No tasks assigned]";
        assertEquals(expected, cmd.execute());
    }
    
    @Test
    public void execute_oneElfWithOneTask_returnsTaskListed() throws Exception {
        Elf elf = new Elf(new Name("Buddy"));
        ElfTask task = new ElfTask("Wrap presents");
        elf.addTask(task);
        elfList.add(elf);
        
        ElfListCommand cmd = new ElfListCommand();
        cmd.setData(childList, elfList, false);
        
        String expected = "Here are all elves and their tasks:\n"
                + "1. Buddy\n"
                + "   Tasks:\n"
                + "     - 1: " + task.toString();
        assertEquals(expected, cmd.execute());
    }
    
    @Test
    public void execute_oneElfWithMultipleTasks_returnsAllTasksIndexed() throws Exception {
        Elf elf = new Elf(new Name("Buddy"));
        ElfTask task1 = new ElfTask("Wrap presents");
        ElfTask task2 = new ElfTask("Paint toys");
        ElfTask task3 = new ElfTask("Sort letters");
        elf.addTask(task1);
        elf.addTask(task2);
        elf.addTask(task3);
        elfList.add(elf);
        
        ElfListCommand cmd = new ElfListCommand();
        cmd.setData(childList, elfList, false);
        
        String expected = "Here are all elves and their tasks:\n"
                + "1. Buddy\n"
                + "   Tasks:\n"
                + "     - 1: " + task1.toString() + "\n"
                + "     - 2: " + task2.toString() + "\n"
                + "     - 3: " + task3.toString();
        assertEquals(expected, cmd.execute());
    }
    
    @Test
    public void execute_mixedElves_returnsCorrectFormat() throws Exception {
        Elf elfWithTask = new Elf(new Name("Buddy"));
        ElfTask task = new ElfTask("Wrap presents");
        elfWithTask.addTask(task);
        elfList.add(elfWithTask);
        elfList.add(new Elf(new Name("Hermey")));
        
        ElfListCommand cmd = new ElfListCommand();
        cmd.setData(childList, elfList, false);
        
        String expected = "Here are all elves and their tasks:\n"
                + "1. Buddy\n"
                + "   Tasks:\n"
                + "     - 1: " + task.toString() + "\n"
                + "2. Hermey [No tasks assigned]";
        assertEquals(expected, cmd.execute());
    }
}
//@@author
