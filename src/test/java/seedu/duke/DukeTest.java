package seedu.duke;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import seedu.duke.data.exception.IllegalValueException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DukeTest {
    //@@author shrabasti-c-reused
    //Inspired from https://www.geeksforgeeks.org/advance-java/unit-testing-of-system-out-println-with-junit/
    // with modifications

    //Claude was used to debug and modify tests 1 and 3 after implementation of the Main (Duke)

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    public void main_displaysWelcome() throws IllegalValueException {
        InputStream simulatedInput = new ByteArrayInputStream("bye\n".getBytes());
        new Duke(simulatedInput).run();
        assertTrue(outputStream.toString().contains(Duke.LOGO));
    }
    //@@author

    @Test
    public void logo_isNotEmpty() {
        assertFalse(Duke.LOGO.isEmpty());
    }

    @Test
    public void main_containsWelcomeMessage() throws IllegalValueException {
        InputStream simulatedInput = new ByteArrayInputStream("bye\n".getBytes());
        new Duke(simulatedInput).run();
        String output = outputStream.toString();
        String expectedOutput="Welcome to ClausControl, Santa!";
        assertTrue(output.contains(expectedOutput));
    }
}

