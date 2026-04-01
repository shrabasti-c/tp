package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.exception.IllegalValueException;
import seedu.clauscontrol.parser.Parser;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@@author shrabasti-c
/* Inspired by AddCommandTest Class of AB2 application
 * Link: https://github.com/se-edu/addressbook-level2/blob/master/test/java/seedu/addressbook
 * /commands/AddCommandTest.java
 */
public class EditCommandTest {
    private static Parser parser;
    private static ArrayList<Child> childList;

    @BeforeAll
    public static void setup() throws IllegalValueException {
        parser = new Parser(new ArrayList<>());
        childList = new ArrayList<>();
        childList.add(new Child(new Name("Bruce Banner")));
    }

    @Test
    public void editCommand_changeName_valid() throws Exception {
        String input = "edit 1 n/Bruce Wayne";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, false);
        command.execute();
        Child child = childList.get(0);
        assertEquals("Bruce Wayne", child.getName().toString());
        assertNull(child.getLocation());
        assertEquals(20, child.getAge());
    }

    @Test
    public void editCommand_changeLocation_valid() throws Exception {
        String input = "edit 1 l/Chicago";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, false);
        command.execute();
        Child child = childList.get(0);
        assertEquals("Bruce Wayne", child.getName().toString());
        assertNotNull(child.getLocation());
        assertEquals("Chicago", child.getLocation());
    }

    @Test
    public void editCommand_changeAge_valid() throws Exception {
        String input = "edit 1 a/20";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, false);
        command.execute();
        Child child = childList.get(0);
        assertEquals("Bruce Banner", child.getName().toString());
        assertNull(child.getLocation());
        assertEquals(20, child.getAge());
    }
}
