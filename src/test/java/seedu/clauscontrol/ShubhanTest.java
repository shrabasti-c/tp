package seedu.clauscontrol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.commands.ActionCommand;
import seedu.clauscontrol.commands.NiceCommand;
import seedu.clauscontrol.commands.NaughtyCommand;
import seedu.clauscontrol.commands.ReassignCommand;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.exception.IllegalValueException;

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
        cmd.setData(childList, new ArrayList<>(), false);
        cmd.execute();
        assertEquals(2, childList.get(0).getTotalScore());
    }

    @Test
    public void actionCommand_invalidIndex_returnsError() {
        ActionCommand cmd = new ActionCommand(99, "helped grandma", 2);
        cmd.setData(childList, new ArrayList<>(), false);
        String result = cmd.execute();
        assertEquals("Enter a valid child index", result);
    }

    @Test
    public void niceCommand_returnsNiceChildren() {
        childList.get(0).addAction("helped grandma", 2);
        childList.get(1).addAction("broke window", -3);
        NiceCommand cmd = new NiceCommand();
        cmd.setData(childList, new ArrayList<>(), false);
        String result = cmd.execute();
        assertTrue(result.contains("Tom"));
        assertTrue(!result.contains("Lucy"));
    }

    @Test
    public void naughtyCommand_returnsNaughtyChildren() {
        childList.get(1).addAction("broke window", -3);
        NaughtyCommand cmd = new NaughtyCommand();
        cmd.setData(childList, new ArrayList<>(), false);
        String result = cmd.execute();
        assertTrue(result.contains("Lucy"));
    }

    @Test
    public void reassignCommand_overridesListAssignment() {
        childList.get(0).addAction("broke window", -3);
        ReassignCommand cmd = new ReassignCommand(1, "nice");
        cmd.setData(childList, new ArrayList<>(), false);
        cmd.execute();
        assertTrue(childList.get(0).isNice());
    }
}
