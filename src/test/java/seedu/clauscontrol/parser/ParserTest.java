package seedu.clauscontrol.parser;

import org.junit.jupiter.api.Test;
import seedu.clauscontrol.commands.ChildCommand;
import seedu.clauscontrol.commands.ElfCommand;
import seedu.clauscontrol.commands.FindCommand;
import seedu.clauscontrol.commands.Command;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.exception.IllegalValueException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserTest {
    private Parser parser = new Parser(new ArrayList<>());

    //@@author shrabasti-c-reused
    //generated using ChatGPT
    @Test
    public void testInvalidDeleteCommands() {
        String[] inputs = {"delete notAnumber", "delete 8*wh12", "delete 1 2 3 4 5"};
        String expectedMessage = "Please use valid command format : delete [childindex]";  // Error message to be thrown

        for (String input : inputs) {
            try {
                parser.parseCommand(input);  // This should throw an exception
            } catch (IllegalValueException e) {
                // Assert that the exception message matches the expected message
                assertEquals(expectedMessage, e.getMessage());
            }
        }
    }
    //@@author
    
    //@@author Aurosky
    @Test
    public void parse_elfCommand_parsedCorrectly() throws Exception {
        String input = "elf n/Dobby";
        Command command = parser.parseCommand(input);
        // Assert that the command matches the expected ElfCommand
        assert command instanceof ElfCommand : "Should be an instance of ElfCommand";
    }
    
    @Test
    public void parse_findCommand_parsedCorrectly() throws Exception {
        String input = "find n/Alice";
        Command command = parser.parseCommand(input);
        assert command instanceof FindCommand : "Should be an instance of FindCommand";
    }
    //@@author

    //@@author shrabasti-c
    @Test
    public void childCommand_allParametersSingleWordNameFirst_valid() throws Exception {
        String input = "child n/Bruce l/Gotham a/24 ";
        Child child = ((ChildCommand) parser.parseCommand(input)).getChild();
        assertEquals("Bruce", child.getName().toString());
        assertEquals("Gotham", child.getLocation());
        assertEquals(24, child.getAge());
    }

    @Test
    public void childCommand_allParametersSingleWordAgeFirst_valid() throws Exception {
        String input = "child a/24 n/Bruce l/Gotham";
        Child child = ((ChildCommand) parser.parseCommand(input)).getChild();
        assertEquals("Bruce", child.getName().toString());
        assertEquals("Gotham", child.getLocation());
        assertEquals(24, child.getAge());
    }

    @Test
    public void childCommand_allParametersSingleWordLocationFirst_valid() throws Exception {
        String input = "child l/Gotham a/24 n/Bruce";
        Child child = ((ChildCommand) parser.parseCommand(input)).getChild();
        assertEquals("Bruce", child.getName().toString());
        assertEquals("Gotham", child.getLocation());
        assertEquals(24, child.getAge());
    }

    @Test
    public void childCommand_allParametersSomeMultiWordNameFirst_valid() throws Exception {
        String input = "child n/Peter Parker l/New York City a/16 ";
        Child child = ((ChildCommand) parser.parseCommand(input)).getChild();
        assertEquals("Peter Parker", child.getName().toString());
        assertEquals("New York City", child.getLocation());
        assertEquals(16, child.getAge());
    }

    @Test
    public void childCommand_allParametersSomeMultiWordAgeFirst_valid() throws Exception {
        String input = "child a/16 n/Peter Parker l/New York City ";
        Child child = ((ChildCommand) parser.parseCommand(input)).getChild();
        assertEquals("Peter Parker", child.getName().toString());
        assertEquals("New York City", child.getLocation());
        assertEquals(16, child.getAge());
    }

    @Test
    public void childCommand_allParametersSomeMultiWordLocationFirst_valid() throws Exception {
        String input = "child l/New York City a/16 n/Peter Parker  ";
        Child child = ((ChildCommand) parser.parseCommand(input)).getChild();
        assertEquals("Peter Parker", child.getName().toString());
        assertEquals("New York City", child.getLocation());
        assertEquals(16, child.getAge());
    }

    @Test
    public void childCommand_noLocation_valid() throws Exception {
        String input = "child n/Peter Parker a/16 ";
        Child child = ((ChildCommand) parser.parseCommand(input)).getChild();
        assertEquals("Peter Parker", child.getName().toString());
        assertNull(child.getLocation());
        assertEquals(16, child.getAge());
    }

    @Test
    public void childCommand_noAge_valid() throws Exception {
        String input = "child n/Peter Parker l/NYC ";
        Child child = ((ChildCommand) parser.parseCommand(input)).getChild();
        assertEquals("Peter Parker", child.getName().toString());
        assertEquals("NYC", child.getLocation());
        assertEquals(-1, child.getAge());
    }

    @Test
    public void childCommand_onlyName_valid() throws Exception {
        String input = "child n/Clark Kent ";
        Child child = ((ChildCommand) parser.parseCommand(input)).getChild();
        assertEquals("Clark Kent", child.getName().toString());
        assertNull(child.getLocation());
        assertEquals(-1, child.getAge());
    }

    @Test
    public void childCommand_multipleSpaces_valid() throws Exception {
        String input = "child n/Steve Rogers     l/New     York   a/30    ";
        Child child = ((ChildCommand) parser.parseCommand(input)).getChild();
        assertEquals("Steve Rogers", child.getName().toString());
        assertEquals("New York", child.getLocation());
        assertEquals(30, child.getAge());
    }

    @Test
    public void childCommand_duplicateParameters_exceptionThrown() throws Exception {
        String input = "child n/Steve Rogers n/Sam Wilson l/New York l/Washington a/30 a/35";
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("You have entered duplicate parameters! Please followchild n/NAME [l/LOCATION] [a/AGE]",
                thrown.getMessage());
    }

    @Test
    public void childCommand_noName_exceptionThrown() throws Exception {
        String input = "child l/London";
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("Please follow the format: child n/NAME [l/LOCATION] [a/AGE]",
                thrown.getMessage());
    }

    @Test
    public void childCommand_emptyName_exceptionThrown() throws Exception {
        String input = "child a/10";
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("Please follow the format: child n/NAME [l/LOCATION] [a/AGE]",
                thrown.getMessage());
    }

    @Test
    public void childCommand_ageNotNumeric_exceptionThrown() throws Exception {
        String input = "child n/Robin a/Robin";
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("Oops! Age must be a valid number within range",
                thrown.getMessage());
    }

    @Test
    public void childCommand_ageNegative_exceptionThrown() throws Exception {
        String input = "child n/Robin a/-32";
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("Oops! Age must be non-negative",
                thrown.getMessage());
    }

    @Test
    public void childCommand_incorrectPrefix_exceptionThrown() throws Exception {
        String input = "child n/Robin y/-32";
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("Oops! Incorrect prefix",
                thrown.getMessage());
    }

    @Test
    public void childCommand_incorrectPrefixNoParams_exceptionThrown() throws Exception {
        String input = "child y/-32";
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("Oops! Incorrect prefix",
                thrown.getMessage());
    }

    @Test
    public void childCommand_additionalParamsLeading_exceptionThrown() throws Exception {
        String input = "child random n/Bobby";
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("Oops! Incorrect prefix",
                thrown.getMessage());
    }

    @Test
    public void childCommand_spacesBetweenParams_exceptionThrown() throws Exception {
        String input = "child n/Buddy a/4 5";
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("Oops! Age must be a valid number within range",
                thrown.getMessage());
    }

    @Test
    public void childCommand_ageOverflow_valid() throws Exception {
        String input = "child n/Steve Rogers a/99999999999999999999999999999    ";
        IllegalValueException thrown = assertThrows(IllegalValueException.class, () -> {
            parser.parseCommand(input);
        });
        assertEquals("Oops! Age must be a valid number within range",
                thrown.getMessage());
    }

    //@@author
}
