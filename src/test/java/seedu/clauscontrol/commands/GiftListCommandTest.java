//@@author prerana-r11
package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.exception.IllegalValueException;
import seedu.clauscontrol.data.gift.Gift;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class GiftListCommandTest {

    private ArrayList<Child> childList;
    private GiftListCommand command;

    @BeforeEach
    public void setup() throws IllegalValueException {
        childList = new ArrayList<>();
        command = new GiftListCommand();
    }

    @Test
    public void execute_noChildren_returnsMessage() {
        command.setData(childList, null, true);
        String result = command.execute();

        assertEquals("No children added", result);
    }


    @Test
    public void execute_noGifts_returnsMessage() throws Exception {
        childList.add(new Child(new Name("John")));

        command.setData(childList, null, true);
        String result = command.execute();

        assertEquals("No gifts assigned :(", result);
    }


    @Test
    public void execute_singleChildWithGifts_success() throws Exception {
        Child child = new Child(new Name("John"));
        child.addGift(new Gift("toy"));
        child.addGift(new Gift("car"));

        childList.add(child);

        command.setData(childList, null, true);
        String result = command.execute();

        assertTrue(result.contains("John"));
        assertTrue(result.contains("toy"));
        assertTrue(result.contains("car"));
    }

    @Test
    public void execute_multipleChildrenWithGifts_success() throws Exception {
        Child child1 = new Child(new Name("John"));
        child1.addGift(new Gift("toy"));

        Child child2 = new Child(new Name("Doe"));
        child2.addGift(new Gift("doll"));

        childList.add(child1);
        childList.add(child2);

        command.setData(childList, null, true);
        String result = command.execute();

        assertTrue(result.contains("John"));
        assertTrue(result.contains("toy"));
        assertTrue(result.contains("Doe"));
        assertTrue(result.contains("doll"));
    }

    @Test
    public void execute_someChildrenNoGifts_onlyShowsThoseWithGifts() throws Exception {
        Child child1 = new Child(new Name("John"));
        child1.addGift(new Gift("toy"));

        Child child2 = new Child(new Name("Doe"));

        childList.add(child1);
        childList.add(child2);

        command.setData(childList, null, true);
        String result = command.execute();

        assertTrue(result.contains("John"));
        assertTrue(result.contains("toy"));
        assertFalse(result.contains("Doe"));
    }
}
//@@author
