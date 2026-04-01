//@@author prerana-r11
package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.exception.IllegalValueException;
import seedu.clauscontrol.parser.Parser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class PrepareGiftCommandTest {
    private static Parser parser;
    private static ArrayList<Child> childList;

    @BeforeEach
    public void setup() throws IllegalValueException {
        parser = new Parser(new ArrayList<>());
        childList = new ArrayList<>();
        Child child = new Child(new Name("John Doe"));
        child.addGift(new seedu.clauscontrol.data.gift.Gift("chocolate"));
        childList.add(child);
    }

    @Test
    public void prepareGift_valid_success() throws Exception {
        String input = "prepared 1 1";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, true);
        command.execute();
        assertEquals("PREPARED",
                childList.get(0).getGifts().get(0).getState().toString());
    }

    @Test
    public void prepareGift_alreadyDelivered_returnsError() throws Exception {
        childList.get(0).getGifts().get(0).markDelivered();
        String input = "prepared 1 1";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, true);
        String result = command.execute();
        assertEquals("Cannot prepare a delivered gift, try another command!", result);
    }
    //invalid gift index
    @Test
    public void prepareGift_invalidGiftIndex_returnsError() throws Exception {
        String input = "prepared 1 2"; // only 1 gift exists
        Command command = parser.parseCommand(input);
        command.setData(childList, null, true);
        String result = command.execute();
        assertTrue(result.contains("Invalid gift index"));
    }

    @Test
    public void prepareGift_invalidChildIndex_returnsError() throws Exception {
        String input = "prepared 2 1";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, true);
        String result = command.execute();
        assertTrue(result.contains("Invalid child index"));
    }
}
//@@author
