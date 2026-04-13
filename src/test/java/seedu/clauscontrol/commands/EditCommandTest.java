package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.exception.IllegalValueException;
import seedu.clauscontrol.parser.Parser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author shrabasti-c
/* Inspired by AddCommandTest Class of AB2 application
 * Link: https://github.com/se-edu/addressbook-level2/blob/master/test/java/seedu/addressbook/commands
 * /AddCommandTest.java
 */
public class EditCommandTest {
    private static Parser parser;
    private static ArrayList<Child> childList;

    @BeforeEach
    public void setup() throws IllegalValueException {
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
        assertEquals(-1, child.getAge());
    }

    @Test
    public void editCommand_changeLocation_valid() throws Exception {
        String input = "edit 1 l/Chicago";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, false);
        command.execute();
        Child child = childList.get(0);
        assertEquals("Bruce Banner", child.getName().toString());
        assertNotNull(child.getLocation());
        assertEquals("Chicago", child.getLocation());
    }

    @Test
    public void editCommand_changeAge_valid() throws Exception {
        String input = "edit 1 a/13";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, false);
        command.execute();
        Child child = childList.get(0);
        assertEquals("Bruce Banner", child.getName().toString());
        assertNull(child.getLocation());
        assertEquals(13, child.getAge());
    }

    @Test
    public void editCommand_negativeAge_throwsException() throws Exception {
        String input = "edit 1 a/-20";
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("Oops! Age must be non-negative",
                thrown.getMessage());
    }

    @Test
    public void editCommand_ageOverflow_throwsException() throws Exception {
        String input = "edit 1 a/99999999999999999999999";
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("Oops! Age must be a valid number within range",
                thrown.getMessage());
    }

    @Test
    public void editCommand_duplicateParams_throwsException() throws Exception {
        String input = "edit 1 a/9 a/10";
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("You have entered duplicate parameters! " +
                        "Please follow edit INDEX [n/NAME] [l/LOCATION] [a/AGE]",
                thrown.getMessage());
    }

    @Test
    public void editCommand_noIndex_throwsException() throws Exception {
        String input = "edit a/10";
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("First argument must be the child index",
                thrown.getMessage());
    }
}
