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


public class GiftCommandTest {
    private static Parser parser;
    private static ArrayList<Child> childList;

    @BeforeEach
    public void setup() throws IllegalValueException {
        parser = new Parser(new ArrayList<>());
        childList = new ArrayList<>();
        childList.add(new Child(new Name("John Doe")));
    }

    @Test
    public void giftCommand_addGift_valid() throws Exception {
        String input = "gift 1 g/chocolate";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, true);
        command.execute();

        Child child = childList.get(0);

        assertEquals(1, child.getGifts().size());
        assertEquals("chocolate", child.getGifts().get(0).getGiftName());
    }

    @Test
    public void giftCommand_notFinalized_returnsError() throws Exception {
        String input = "gift 1 g/chocolate";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, false);
        String result = command.execute();

        assertEquals("Please finalise the lists before allocating gifts!", result);
    }
    //invalid child index
    @Test
    public void giftCommand_invalidChildIndex_returnsError() throws Exception {
        String input = "gift 2 g/toy";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, true);
        String result = command.execute();
        assertEquals("Invalid child index!", result);
    }
    //no gift
    @Test
    public void giftCommand_noGift_returnsError() {
        Exception e = assertThrows(Exception.class, () -> {
            parser.parseCommand("gift 1");
        });

        assertTrue(e.getMessage().contains("gift"));
    }
    //multiple gifts
    @Test
    public void giftCommand_multipleGifts_success() throws Exception {
        String input = "gift 1 g/Toy g/Ball g/Car";
        Command command = parser.parseCommand(input);
        command.setData(childList, null, true);
        command.execute();

        assertEquals(3, childList.get(0).getGifts().size());
    }
}
//@@author
