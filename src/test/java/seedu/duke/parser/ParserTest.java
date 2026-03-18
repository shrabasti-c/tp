package seedu.duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

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
}
