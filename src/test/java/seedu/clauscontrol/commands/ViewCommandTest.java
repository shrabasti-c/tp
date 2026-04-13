package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.exception.IllegalValueException;
import seedu.clauscontrol.data.gift.Gift;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author shrabasti-c
public class ViewCommandTest {

    private static ArrayList<Child> childList;
    private static ViewCommand command;

    @BeforeEach
    public void setup() throws IllegalValueException {
        childList = new ArrayList<>();
        childList.add(new Child(new Name("Eddie")));
        command = new ViewCommand(0);
    }

    @Test
    public void execute_noChildren_valid() {
        command.setData(new ArrayList<>(), null, true);
        String result = command.execute();
        assertEquals("Invalid index position :(\nRefer to the child list for valid positions!", result);
    }

    @Test
    public void execute_onlyName_valid() throws Exception {
        command.setData(childList, null, true);
        String result = command.execute();
        assertTrue(result.contains("Name: Eddie"));
        assertTrue(result.contains("Age: Not Provided"));
        assertTrue(result.contains("Location: Not Provided"));
        assertFalse(result.contains("Gifts: "));
        assertFalse(result.contains("Actions: "));
        assertTrue(result.contains("List Assignment: Yet to be evaluated/assigned."));
    }

    @Test
    public void execute_onlyNameAndAge_valid() throws Exception {
        childList.get(0).setAge(0);
        command.setData(childList, null, true);
        String result = command.execute();
        assertTrue(result.contains("Name: Eddie"));
        assertTrue(result.contains("Age: 0"));
        assertTrue(result.contains("Location: Not Provided"));
        assertFalse(result.contains("Gifts: "));
        assertFalse(result.contains("Actions: "));
        assertTrue(result.contains("List Assignment: Yet to be evaluated/assigned."));
    }

    @Test
    public void execute_allParamsWithAction_valid() throws Exception {
        childList.get(0).setAge(0);
        childList.get(0).setLocation("Malibu");
        childList.get(0).addAction("did homework", 5);
        command.setData(childList, null, true);
        String result = command.execute();
        System.out.println(result);
        assertTrue(result.contains("Name: Eddie"));
        assertTrue(result.contains("Age: 0"));
        assertTrue(result.contains("Location: Malibu"));
        assertFalse(result.contains("Gifts: "));
        assertTrue(result.contains("Actions: "));
        assertTrue(result.contains("List Assignment: nice"));
    }

    @Test
    public void execute_allParamsWithActionWithGift_valid() throws Exception {
        childList.get(0).setAge(0);
        childList.get(0).setLocation("Malibu");
        childList.get(0).addAction("didn't do homework", -5);
        childList.get(0).addGift(new Gift("book"));
        command.setData(childList, null, true);
        String result = command.execute();
        System.out.println(result);
        assertTrue(result.contains("Name: Eddie"));
        assertTrue(result.contains("Age: 0"));
        assertTrue(result.contains("Location: Malibu"));
        assertTrue(result.contains("Gifts: "));
        assertTrue(result.contains("Actions: "));
        assertTrue(result.contains("List Assignment: naughty"));
    }
}
//@@author
