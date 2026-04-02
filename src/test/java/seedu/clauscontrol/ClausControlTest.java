package seedu.clauscontrol;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import seedu.clauscontrol.data.exception.IllegalValueException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClausControlTest {
    //@@author shrabasti-c
    /* Inspired by JUnit test structure found on GeeksforGeeks
     * Link: https://www.geeksforgeeks.org/advance-java/unit-testing-of-system-out-println-with-junit/
     * with some modifications
     */

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
        new ClausControl(simulatedInput).run();
        assertTrue(outputStream.toString().contains(ClausControl.LOGO));
    }
    //@@author

    //@@author GShubhan
    @Test
    public void logo_isNotEmpty() {
        assertFalse(ClausControl.LOGO.isEmpty());
    }
    //@@author

    @Test
    public void main_containsWelcomeMessage() throws IllegalValueException {
        InputStream simulatedInput = new ByteArrayInputStream("bye\n".getBytes());
        new ClausControl(simulatedInput).run();
        String output = outputStream.toString();
        String expectedOutput="Welcome to ClausControl, Santa!";
        assertTrue(output.contains(expectedOutput));
    }
}

