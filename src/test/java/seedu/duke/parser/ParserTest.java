package seedu.duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.ElfCommand;
import seedu.duke.commands.FindCommand;
import seedu.duke.commands.Command;
import seedu.duke.data.exception.IllegalValueException;

//generated using ChatGPT
public class ParserTest {

    private Parser parser = new Parser();  // Assuming the Parser object is created here

    /**
     * Test to check multiple invalid delete commands and assert the error message
     */
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
    
    //@@author Kiri
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
}
