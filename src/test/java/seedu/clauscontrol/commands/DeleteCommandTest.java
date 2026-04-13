package seedu.clauscontrol.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.child.Name;
import seedu.clauscontrol.data.exception.IllegalValueException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author shrabasti-c
public class DeleteCommandTest {
    private static ArrayList<Child> childList;
    private static DeleteCommand command;

    @BeforeAll
    public static void setup() throws IllegalValueException {
        childList = new ArrayList<>();
        childList.add(new Child(new Name("Bruce Banner")));
    }

    @Test
    public void deleteCommand_validIndex_valid() throws Exception {
        command = new DeleteCommand(0);
        command.setData(childList, null, false);
        command.execute();
        assertTrue(childList.isEmpty());
    }
}
//@@author
