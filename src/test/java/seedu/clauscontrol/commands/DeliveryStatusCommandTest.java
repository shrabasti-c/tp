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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;



public class DeliveryStatusCommandTest {
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
    public void deliveryStatus_markDelivered_success() throws Exception {
        String input = "delivery_status 1 1 d/delivered";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, true);
        command.execute();
        assertTrue(childList.get(0).getGifts().get(0).isDelivered());
    }

    @Test
    public void deliveryStatus_alreadyDelivered_returnsError() throws Exception {
        childList.get(0).getGifts().get(0).markDelivered();

        String input = "delivery_status 1 1 d/delivered";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, true);
        String result = command.execute();
        assertEquals("Gift is already delivered!", result);
    }

    @Test
    public void deliveryStatus_markUndelivered_success() throws Exception {
        String input = "delivery_status 1 1 d/undelivered";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, true);
        command.execute();
        assertFalse(childList.get(0).getGifts().get(0).isDelivered());
    }

    @Test
    public void deliveryStatus_alreadyUndelivered_returnsError() throws Exception {
        String input = "delivery_status 1 1 d/undelivered";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, true);
        String result = command.execute();
        assertEquals("Gift is already undelivered!", result);
    }

    @Test
    public void deliveryStatus_invalidFormat_throwsException() {
        Exception e = assertThrows(Exception.class, () -> {
            assertThrows(IllegalValueException.class, () -> {
                parser.parseCommand("delivery_status 1 1 wrong");
            });
        });
        assertTrue(e.getMessage().contains("Format"));
    }
}
//@@author
