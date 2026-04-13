package seedu.clauscontrol;

import seedu.clauscontrol.commands.Command;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.elf.Elf;
import seedu.clauscontrol.data.exception.IllegalValueException;
import seedu.clauscontrol.data.todo.Todo;
import seedu.clauscontrol.parser.Parser;
import seedu.clauscontrol.storage.TodoStorage;
import seedu.clauscontrol.ui.TextUi;
import seedu.clauscontrol.storage.Storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.clauscontrol.commands.FinalizeCommand;
import seedu.clauscontrol.commands.ResetCommand;
import seedu.clauscontrol.storage.StorageData;

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

    public static final String DIVIDER = "_.:*~*:._.:*~*:._.:*~*:._.:*~*:._.:*~*:._.:*~*:._.:*~*:._";
    private static final Logger logger = Logger.getLogger(ClausControl.class.getName());
    private final TextUi ui;
    private final Parser parser;
    private ArrayList<Child> childList;
    private final ArrayList<Elf> elfList;
    private final Storage storage = new Storage("data.txt");

    //@@author GShubhan
    private boolean isFinalized = false;
    private ArrayList<Todo> todoList = new ArrayList<>();
    private TodoStorage todoStorage = new TodoStorage("todos.txt");

    //@@author
    public ClausControl() {
        this(System.in);
    }

    //@@author prerana-r11
    public ClausControl(InputStream inputStream) {
        this.ui = new TextUi(inputStream);
        this.elfList = new ArrayList<>();
        try {
            StorageData data = storage.load();
            this.childList = new ArrayList<>(data.children);
            this.elfList.addAll(data.elves);
            this.isFinalized = data.isFinalized;

        } catch (IOException e) {
            this.childList = new ArrayList<>();
        }
        //@@author GShubhan
        try {
            this.todoList = new ArrayList<>(todoStorage.load());
        } catch (IOException e) {
            this.todoList = new ArrayList<>();
        }
        this.parser = new Parser(todoList);  // initialize AFTER loading todos
        this.parser.setChildList(childList);
        //@@author
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
                //@@author GShubhan
                String result = command.execute();
                if (command instanceof FinalizeCommand) {
                    isFinalized = true;
                } else if (command instanceof ResetCommand) {
                    isFinalized = false;
                }
                try {
                    storage.save(childList, elfList, isFinalized);  // now saves the correct value
                    todoStorage.save(todoList);

                } catch (IOException e) {
                    logger.warning("Error saving: " + e.getMessage());
                }

                displayWithDividers(result);
                //@@author
            } catch (IllegalValueException e) {
                displayWithDividers(e.getMessage());
                logger.log(Level.WARNING, "processing error or delete command triggered.");
            } catch (Exception e) {
                return;
            }
        } while (true);
    }


    //@@author shrabasti-c
    private void displayWithDividers(String message) {
        System.out.println("\n" + DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER + "\n");
    }
    //@@author

    /**
     * Displays application logo and starts command loop.
     *
     * @throws IllegalValueException If an incorrectly formatted command is encountered.
     */
    public void run() throws IllegalValueException {
        System.out.println(LOGO);
        showUpcomingTodos();
        runCommandLoopUntilExitCommand();
    }

    //@@author GShubhan
    private void showUpcomingTodos() {
        StringBuilder sb = new StringBuilder();
        for (Todo todo : todoList) {
            if (todo.isUpcoming()) {
                sb.append("- ").append(todo.getDescription())
                        .append(" (due: ").append(todo.getDeadline()).append(")\n");
            }
        }
        if (sb.length() > 0) {
            System.out.println("Upcoming reminders this week:");
            System.out.println(sb.toString().trim());
            System.out.println();
        }
    }
    //@@author

    /**
     * Main entry-point for the ClausControl application.
     *
     * @throws IllegalValueException If an incorrectly formatted command is encountered.
     */

    public static void main(String[] args) throws IllegalValueException {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.OFF);
        for (java.util.logging.Handler h : rootLogger.getHandlers()) {
            h.setLevel(Level.OFF);
        }
        new ClausControl().run();
    }
}
//@@author

