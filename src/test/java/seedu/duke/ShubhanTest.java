package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.commands.ActionCommand;
import seedu.duke.commands.NiceCommand;
import seedu.duke.commands.NaughtyCommand;
import seedu.duke.commands.ReassignCommand;
import seedu.duke.data.child.Child;
import seedu.duke.data.child.Name;
import seedu.duke.data.exception.IllegalValueException;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShubhanTest {

    private ArrayList<Child> childList;

    @BeforeEach
    public void setUp() throws IllegalValueException {
        childList = new ArrayList<>();
        childList.add(new Child(new Name("Tom")));
        childList.add(new Child(new Name("Lucy")));
    }

    @Test
    public void actionCommand_validIndex_addsAction() {
        ActionCommand cmd = new ActionCommand(1, "helped grandma", 2);
        cmd.setData(childList, new ArrayList<>());
        cmd.execute();
        assertEquals(2, childList.get(0).getTotalScore());
    }

    @Test
    public void actionCommand_invalidIndex_returnsError() {
        ActionCommand cmd = new ActionCommand(99, "helped grandma", 2);
        cmd.setData(childList, new ArrayList<>());
        String result = cmd.execute();
        assertEquals("Enter a valid child index", result);
    }

    @Test
    public void niceCommand_returnsNiceChildren() {
        childList.get(0).addAction("helped grandma", 2);
        childList.get(1).addAction("broke window", -3);
        NiceCommand cmd = new NiceCommand();
        cmd.setData(childList, new ArrayList<>());
        String result = cmd.execute();
        assertTrue(result.contains("Tom"));
        assertTrue(!result.contains("Lucy"));
    }

    @Test
    public void naughtyCommand_returnsNaughtyChildren() {
        childList.get(1).addAction("broke window", -3);
        NaughtyCommand cmd = new NaughtyCommand();
        cmd.setData(childList, new ArrayList<>());
        String result = cmd.execute();
        assertTrue(result.contains("Lucy"));
    }

    @Test
    public void reassignCommand_overridesListAssignment() {
        childList.get(0).addAction("broke window", -3);
        ReassignCommand cmd = new ReassignCommand(1, "nice");
        cmd.setData(childList, new ArrayList<>());
        cmd.execute();
        assertTrue(childList.get(0).isNice());
    }
}
