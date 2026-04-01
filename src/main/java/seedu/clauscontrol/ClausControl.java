package seedu.clauscontrol;

import seedu.clauscontrol.commands.Command;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.elf.Elf;
import seedu.clauscontrol.data.exception.IllegalValueException;
import seedu.clauscontrol.data.todo.Todo;
import seedu.clauscontrol.parser.Parser;
import seedu.clauscontrol.ui.TextUi;
import seedu.clauscontrol.storage.Storage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import seedu.clauscontrol.commands.FinalizeCommand;

//@@author shrabasti-c
/* Adapted from Main Class of AB2 application
 * Link: https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java
 * with some modifications
 */
/**
 * Starts and runs the ClausControl application until termination.
 */
public class ClausControl {
    public static final String LOGO = "  .-\"\"-.\n" +
            " /,..___\\\n" +
            "() {_____}\n" +
            "  (/-@-@-\\)\n" +
            "  {`-=^=-'}\n" +
            "  {  `-'  } Welcome to ClausControl, Santa!\n" +
            "   {     }\n" +
            "    `---'\n";

    private static final Logger logger = Logger.getLogger("Foo");
    private final TextUi ui;
    private final Parser parser;
    private ArrayList<Child> childList;
    private final ArrayList<Elf> elfList;
    private final Storage storage= new Storage("data.txt");

    //@@author GShubhan
    private boolean isFinalized = false;
    private ArrayList<Todo> todoList = new ArrayList<>();
    //@@author

    public ClausControl() {
        this(System.in);
    }

    //@@author prerana-r11
    public ClausControl(InputStream inputStream) {
        this.ui = new TextUi(inputStream);
        this.parser = new Parser(todoList);
        this.elfList = new ArrayList<>();
        try {
            this.childList = new ArrayList<>(storage.load());
        } catch (IOException e) {
            this.childList = new ArrayList<>();
        }
    }
    //@@author

    /**
     * Loops through application execution.
     * If the exit command "bye" is encountered, the program terminates.
     *
     * @throws IllegalValueException If an incorrectly formatted command is encountered.
     */
    private void runCommandLoopUntilExitCommand() throws IllegalValueException {
        Command command;
        do {
            try {
                String userCommandText = ui.getUserCommand();
                if (userCommandText.equals("bye")) {
                    logger.log(Level.INFO, "end of processing");
                    return;
                }
                command = parser.parseCommand(userCommandText);
                command.setData(childList, elfList, isFinalized);
                executeCommand(command);
            } catch (IllegalValueException e) {
                System.out.println(e.getMessage());
                logger.log(Level.INFO, "processing error");
            } catch(Exception e){
                return;
            }
        } while (true);
    }

    //@@author GShubhan
    private void executeCommand(Command command) {
        String result = command.execute();
        try {
            storage.save(childList);
        } catch (IOException e) {
            logger.warning("Error saving: " + e.getMessage());
        }
        if (command instanceof FinalizeCommand) {
            isFinalized = true;
        }
        System.out.println(result);
    }
    //@@author

    /**
     * Displays application logo and starts command loop.
     *
     * @throws IllegalValueException If an incorrectly formatted command is encountered.
     */
    public void run() throws IllegalValueException {
        System.out.println(LOGO);
        runCommandLoopUntilExitCommand();
    }

    /**
     * Main entry-point for the ClausControl application.
     *
     * @throws IllegalValueException If an incorrectly formatted command is encountered.
     */
    public static void main(String[] args) throws IllegalValueException {
        Logger.getLogger("seedu.clauscontrol").setLevel(Level.WARNING);
        new ClausControl().run();
    }
}
//@@author

