//@@author prerana-r11
package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.exception.IllegalValueException;
import seedu.clauscontrol.data.gift.Gift;
import seedu.clauscontrol.parser.Parser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeGiftCommandTest {

    private static Parser parser;
    private static ArrayList<Child> childList;

    @BeforeEach
    public void setup() throws IllegalValueException {
        parser = new Parser(new ArrayList<>());
        childList = new ArrayList<>();
        Child child = new Child(new Name("John Doe"));
        child.addGift(new Gift("chocolate"));
        childList.add(child);
    }


    @Test
    public void degift_valid_removesGift() throws Exception {
        Command command = null;
        try {
            parser.parseCommand("degift 1 1");
        } catch (IllegalValueException e) {}
        command = parser.parseCommand("confirm");
        command.setData(childList, null, true);
        command.execute();
        assertEquals(0, childList.get(0).getGifts().size());
    }

    @Test
    public void degift_invalidChildIndex_returnsError() throws Exception {
        Command command = null;
        try {
            parser.parseCommand("degift 2 1");
        } catch (IllegalValueException e) {}
        command = parser.parseCommand("confirm");
        command.setData(childList, null, true);
        String result = command.execute();
        assertEquals("Please enter valid child index", result);
    }

    @Test
    public void degift_invalidGiftIndex_returnsError() throws Exception {
        Command command = null;
        try {
            parser.parseCommand("degift 1 5");
        } catch (IllegalValueException e) {}
        command = parser.parseCommand("confirm");
        command.setData(childList, null, true);
        String result = command.execute();
        assertEquals("Please enter valid gift index", result);
    }

    @Test
    public void degift_deliveredGift_returnsError() throws Exception {
        childList.get(0).getGifts().get(0).markDelivered();
        Command command = null;
        try {
            parser.parseCommand("degift 1 1");
        } catch (IllegalValueException e) {
            // expected warning
        }
        command = parser.parseCommand("confirm");
        command.setData(childList, null, true);
        String result = command.execute();
        assertTrue(result.contains("delivered"));
    }
}
//@@author
