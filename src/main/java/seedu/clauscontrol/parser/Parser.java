package seedu.clauscontrol.parser;

import seedu.clauscontrol.commands.Command;
import seedu.clauscontrol.commands.DeGiftCommand;
import seedu.clauscontrol.commands.DeliveryStatusCommand;
import seedu.clauscontrol.commands.DetaskCommand;
import seedu.clauscontrol.commands.GiftCommand;
import seedu.clauscontrol.commands.PrepareGiftCommand;
import seedu.clauscontrol.commands.ActionCommand;
import seedu.clauscontrol.commands.ChildCommand;
import seedu.clauscontrol.commands.ChildListCommand;
import seedu.clauscontrol.commands.EditCommand;
import seedu.clauscontrol.commands.EditElfCommand;
import seedu.clauscontrol.commands.ElfCommand;
import seedu.clauscontrol.commands.ElfListCommand;
import seedu.clauscontrol.commands.FinalizeCommand;
import seedu.clauscontrol.commands.FindCommand;
import seedu.clauscontrol.commands.GiftListCommand;
import seedu.clauscontrol.commands.NaughtyCommand;
import seedu.clauscontrol.commands.NiceCommand;
import seedu.clauscontrol.commands.ReassignCommand;
import seedu.clauscontrol.commands.ResetCommand;
import seedu.clauscontrol.commands.RmElfCommand;
import seedu.clauscontrol.commands.TaskCommand;
import seedu.clauscontrol.commands.ViewCommand;
import seedu.clauscontrol.commands.DeleteCommand;
import seedu.clauscontrol.commands.AddTodoCommand;
import seedu.clauscontrol.commands.TodoListCommand;
import seedu.clauscontrol.commands.EditTodoCommand;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.todo.Todo;
import seedu.clauscontrol.commands.RemoveTodoCommand;

import seedu.clauscontrol.data.exception.IllegalValueException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


/**
 * Parses user input.
 */
public class Parser {
    private static Command pendingCommand = null;
    //@@author GShubhan

    private static final String GIFT_PREFIX = "g/";
    private static final String DELIVERED = "d/delivered";
    private static final String UNDELIVERED = "d/undelivered";

    private final ArrayList<Todo> todoList;
    private ArrayList<Child> childList = new ArrayList<>();

    public Parser(ArrayList<Todo> todoList) {
        this.todoList = todoList;
    }
    public void setChildList(ArrayList<Child> childList) {
        this.childList = childList;
    }
    //@@author

    public Command parseCommand(String userInput) throws IllegalValueException {

        //@@author Aurosky
        String trimmedInput = userInput.trim();
        if (trimmedInput.equalsIgnoreCase("confirm")) {
            if (pendingCommand == null) {
                throw new IllegalValueException("There is no pending command to confirm.");
            }
            Command toExecute = pendingCommand;
            pendingCommand = null;
            return toExecute;
        }
        
        if (trimmedInput.equalsIgnoreCase("cancel")) {
            if (pendingCommand == null) {
                throw new IllegalValueException("There is no pending command to cancel.");
            }
            pendingCommand = null;
            throw new IllegalValueException("Pending command has been cancelled.");
        }
        //@@author

        //@@author shrabasti-c-reused
        // Reused from ChatGPT
        String[] parts = userInput.trim().split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";
        //@@author

        //@@author shrabasti-c
        switch (commandWord) {
        case "child":
            return prepareAdd(arguments);

        case "view":
            return prepareView(arguments);

        case "edit":
            return prepareEdit(arguments);

        case "edittodo":
            return prepareEditTodo(arguments);

        //@@author

        //@@author Aurosky
        case "delete":
            checkNoPendingCommand("delete");
            pendingCommand = prepareDelete(arguments);
            throw new IllegalValueException("WARNING: You are about to delete a child. Type 'confirm' to proceed.");

        case "childlist":
            if (!arguments.trim().isEmpty()) {
                throw new IllegalValueException("Unexpected trailing arguments for 'childlist' command. " +
                        "Try using 'childlist' without any extra text.");
            }
            return new ChildListCommand();

        case "elflist":
            if (!arguments.trim().isEmpty()) {
                throw new IllegalValueException("Unexpected trailing arguments for 'elflist' command. " +
                        "Try using 'elflist' without any extra text.");
            }
            return new ElfListCommand();

        case "find":
            return prepareFind(arguments);

        case "elf":
            return prepareElf(arguments);

        case "task":
            return prepareTaskAction(arguments);

        case "detask":
            checkNoPendingCommand("detask");
            pendingCommand = prepareDetask(arguments);
            throw new IllegalValueException("WARNING: You are about to remove a task. Type 'confirm' to proceed.");

        case "editelf":
            return prepareEditElf(arguments);

        case "rmelf":
            checkNoPendingCommand("rmelf");
            pendingCommand = prepareRmElf(arguments);
            throw new IllegalValueException("WARNING: You are about to remove an Elf. Type 'confirm' to proceed.");

        case "reset":
            checkNoPendingCommand("reset");
            pendingCommand = new ResetCommand(todoList);
            throw new IllegalValueException("WARNING: This will wipe ALL data and reset to initial state. " +
                    "Type 'confirm' to proceed.");
        //@@author

        //@@author GShubhan
        case "action":
            return prepareAction(arguments);
        case "nice":
            return new NiceCommand();
        case "finalize":
            // fall through
        case "finalise":
            return new FinalizeCommand();
        case "naughty":
            return new NaughtyCommand();
        case "reassign":
            try {
                if (arguments.trim().isEmpty()) {
                    throw new IllegalValueException("Format: reassign CHILD_INDEX l/LIST");
                }
                String[] reassignParts = arguments.split(" ");
                if (reassignParts.length < 2) {
                    throw new IllegalValueException("Format: reassign CHILD_INDEX l/LIST");
                }
                if (!reassignParts[1].startsWith("l/")) {
                    throw new IllegalValueException("Format: reassign CHILD_INDEX l/LIST");
                }
                int reassignIndex = Integer.parseInt(reassignParts[0]);
                String reassignList = reassignParts[1].substring(2);
                if (reassignList.isEmpty()) {
                    throw new IllegalValueException("List cannot be empty! Use nice or naughty");
                }
                return new ReassignCommand(reassignIndex, reassignList);
            } catch (NumberFormatException e) {
                throw new IllegalValueException("Child index must be a number!");
            }
        case "todo":
            return new AddTodoCommand(arguments, todoList);

        case "todolist":
            return new TodoListCommand(todoList);
        case "removetodo":
            checkNoPendingCommand("removetodo");
            try {
                return new RemoveTodoCommand(Integer.parseInt(arguments.trim()), todoList);
            } catch (NumberFormatException e) {
                throw new IllegalValueException("Index must be a number! Format: removetodo INDEX");
            }

        //@@author

        //@@author prerana-r11
        case "gift":
            return prepareGiftAction(arguments);
        case "degift":
            checkNoPendingCommand("degift");
            DeGiftCommand temp = prepareDeGiftAction(arguments);
            int degiftChildIndex = temp.getChildIndex();
            int degiftGiftIndex = temp.getGiftIndex();
            if (degiftChildIndex >= 1 && degiftChildIndex <= childList.size()) {
                Child dgChild = childList.get(degiftChildIndex - 1);
                if (degiftGiftIndex >= 1 && degiftGiftIndex <= dgChild.getGifts().size()) {
                    if (dgChild.getGifts().get(degiftGiftIndex - 1).isDelivered()) {
                        throw new IllegalValueException("Cannot remove a delivered gift!");
                    }
                }
            }
            pendingCommand = temp;
            throw new IllegalValueException("WARNING: You are about to remove a gift. Type 'confirm' to proceed.");
        case "delivery_status":
            return prepareDeliverAction(arguments);
        case "giftlist":
            return new GiftListCommand();
        case "prepared":
            return preparePreparedAction(arguments);
        default:
            throw new IllegalValueException(
                    "Unknown command:( Please enter valid command");
        }
        //@@author
    }

    //@@author shrabasti-c-reused
    // Reused from ChatGPT with modifications

    /**
     * Returns an Add command from user input arguments.
     * Extracts name, location, and age from the input string.
     *
     * @param args the input arguments
     * @return a {@link ChildCommand} initialized with the extracted parameters
     * @throws IllegalValueException if input format is invalid, contains duplicates, or invalid values
     */
    private Command prepareAdd(String args) throws IllegalValueException {
        String name = null;
        String location = null;
        String ageString = null;
        int age = -1;

        boolean duplicateNameErrorAdded = false;
        boolean duplicateAgeErrorAdded = false;
        boolean duplicateLocationErrorAdded = false;

        args = args.trim().replaceAll("\\s+", " ");

        if (args.isEmpty()) {
            throw new IllegalValueException("Oops! Please provide fields after command.");
        }

        String[] tokens = args.split("\\s+(?=\\w/)");
        List<String> errors = new ArrayList<>();

        for (String token : tokens) {
            if (!token.matches("[nla]/.*")) {
                throw new IllegalValueException("Oops! Invalid prefix entered.\n" +
                        "Please follow the format: child n/NAME [l/LOCATION] [a/AGE]");
            }

            if (token.startsWith("n/")) {
                if (name != null) {
                    if (!duplicateNameErrorAdded) {
                        errors.add("Oops! Duplicate name fields entered. Please stick to one.");
                        duplicateNameErrorAdded = true;
                    }
                    continue;
                }

                name = token.substring(2);
                String err = checkValidity(name, "Name");
                if (err != null) {
                    errors.add(err);
                }

            } else if (token.startsWith("l/")) {
                if (location != null) {
                    if (!duplicateLocationErrorAdded) {
                        errors.add("Oops! Duplicate location fields entered. Please stick to one.");
                        duplicateLocationErrorAdded = true;
                    }
                    continue;
                }

                location = token.substring(2);
                String err = checkValidity(location, "Location");

                if (err != null) {
                    errors.add(err);
                }

            } else if (token.startsWith("a/")) {
                if (ageString != null) {
                    if (!duplicateAgeErrorAdded) {
                        errors.add("Oops! Duplicate age fields entered. Please stick to one.");
                        duplicateAgeErrorAdded = true;
                    }
                    continue;
                }

                ageString = token.substring(2);
                String err = checkValidity(ageString, "Age");

                if (err != null) {
                    errors.add(err);
                }
            }
        }

        if (name == null) {
            throw new IllegalValueException("Oops! Name must be provided." +
                    "\nPlease follow the format: child n/NAME [l/LOCATION] [a/AGE]"
            );
        }

        if (!errors.isEmpty()) {
            throw new IllegalValueException(
                    String.join("\n", errors) +
                            "\nPlease follow the format: child n/NAME [l/LOCATION] [a/AGE]"
            );
        }

        age = assignAge(ageString, age);

        return new ChildCommand(name, location, age);
    }

    /**
     * Converts the age string to an integer and validates it.
     *
     * @param ageString the string representation of age
     * @param age the default age value
     * @return the validated age as an integer
     * @throws IllegalValueException if ageString is not a valid non-negative integer
     */
    private int assignAge(String ageString, int age) throws IllegalValueException {
        if (ageString != null) {
            try {
                age = Integer.parseInt(ageString);
                if (age < 0) {
                    throw new IllegalValueException("Oops! Age must be non-negative");
                }
                if (age > 18) {
                    throw new IllegalValueException("Oops! Age must be an integer between 0 to 18");
                }
            } catch (NumberFormatException e) {
                throw new IllegalValueException("Oops! Age must be a valid number within range");
            }
        }
        return age;
    }

    /**
     * Checks whether a required token is valid.
     *
     * @param token the string to validate
     * @return an error message if applicable
     */
    private static String checkValidity(String token, String paramType) throws IllegalValueException {
        if (token == null || token.isEmpty()) {
            return(paramType + " must be provided.");
        }
        return null;
    }

    /**
     * Prepares an Edit command from user input arguments.
     * Parses the index of the child to edit and the new values for name, location, and age.
     *
     * @param args the input arguments
     * @return an {@link EditCommand} initialized with the parsed index and new values
     * @throws IllegalValueException if input format is invalid, index is missing, or values are invalid
     */
    private Command prepareEdit(String args) throws IllegalValueException {
        String newName = null;
        String newLocation = null;
        String ageString = null;
        int newAge = -1;
        int index;
        boolean nameSet = false;
        boolean locationSet = false;
        boolean ageSet = false;

        String[] parts = args.trim().split(" ", 2);
        try {
            index = Integer.parseInt(parts[0]) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalValueException("First argument must be the child index");
        }

        if (parts.length < 2) {
            throw new IllegalValueException("Nothing to edit! Provide n/, l/ or a/");
        }
        String remaining = parts[1];

        String[] tokens = remaining.split("\\s+(?=[nla]/)");

        for (String token : tokens) {
            if (token.startsWith("n/")) {
                if (nameSet) {
                    throw new IllegalValueException("You have entered duplicate parameters! Please follow " +
                            "edit INDEX [n/NAME] [l/LOCATION] [a/AGE]");
                }
                newName = token.substring(2).trim();
                nameSet = true;
            } else if (token.startsWith("l/")) {
                if (locationSet) {
                    throw new IllegalValueException("You have entered duplicate parameters! Please follow " +
                            "edit INDEX [n/NAME] [l/LOCATION] [a/AGE]");
                }
                newLocation = token.substring(2).trim();
                locationSet = true;
            } else if (token.startsWith("a/")) {
                if (ageSet) {
                    throw new IllegalValueException("You have entered duplicate parameters! Please follow " +
                            "edit INDEX [n/NAME] [l/LOCATION] [a/AGE]");
                }
                ageString = token.substring(2).trim();
                ageSet = true;
            }
        }

        newAge = assignAge(ageString, newAge);

        return new EditCommand(index, newName, newLocation, newAge);
    }
    //@@author

    //@@author shrabasti-c

    /**
     * Prepares a View command from user input arguments.
     * Parses the index of the child to view.
     *
     * @param args the input arguments
     * @return an {@link EditCommand} initialized with the parsed index
     * @throws IllegalValueException if input format or index is invalid,
     *                               or index is missing
     */
    private Command prepareView(String args) throws IllegalValueException {
        try {
            int childIndex = Integer.parseInt(args.trim()) - 1;
            return new ViewCommand(childIndex);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Please use valid command format : view [childindex]");
        }
    }

    /**
     * Prepares a Delete command from user input arguments.
     * Parses the index of the child to view.
     *
     * @param args the input arguments
     * @return an {@link EditCommand} initialized with the parsed index
     * @throws IllegalValueException if input format or index is invalid,
     *                               or index is missing
     */
    private Command prepareDelete(String args) throws IllegalValueException {
        try {
            int childIndex = Integer.parseInt(args.trim()) - 1;
            if (childIndex < 0 || childIndex >= childList.size()) {
                throw new IllegalValueException("Invalid index position :(\nRefer to the " +
                        "child list for valid positions!");
            }
            return new DeleteCommand(childIndex);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Please use valid command format : delete [childindex]");
        }
    }

    /**
     * Prepares an "EditTodo" command from user input arguments.
     * Parses the index of the task to edit and the new values for description and deadline.
     *
     * @param args the input arguments
     * @return an {@link EditTodoCommand} initialized with the parsed index and new values.
     * @throws IllegalValueException if input format is invalid,
     *                               index is missing, or values are invalid
     */
    private Command prepareEditTodo(String args) throws IllegalValueException {
        String newDescription = null;
        LocalDate newDeadline = null;
        int index;

        String[] parts = args.trim().split(" ", 2);
        try {
            index = Integer.parseInt(parts[0]) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalValueException("First argument must be the todo task index");
        }

        if (parts.length < 2) {
            throw new IllegalValueException("Nothing to edit! Provide d/ or by/");
        }
        String remaining = parts[1];

        String[] tokens = remaining.split("(?=\\b(d/|by/))");

        for (String token : tokens) {
            if (token.startsWith("d/")) {
                newDescription = token.substring(2).trim();
            } else if (token.startsWith("by/")) {
                String newDeadlineString = token.substring(3).trim();
                try {
                    newDeadline = LocalDate.parse(newDeadlineString);
                    if (newDeadline.isBefore(LocalDate.now())) {
                        throw new IllegalValueException("Deadline cannot be in the past!");
                    }
                } catch (DateTimeParseException e) {
                    throw new IllegalValueException("Invalid date format! Please use YYYY-MM-DD e.g. 2026-04-05");
                }

            }
        }

        return new EditTodoCommand(todoList, index, newDescription, newDeadline);
    }
    //@@author

    private Command prepareAction(String args) throws IllegalValueException {
        try {
            int aIndex = args.indexOf("a/");
            int sIndex = args.indexOf("s/");

            if (aIndex == -1 || sIndex == -1) {
                throw new IllegalValueException("Format: action CHILD_INDEX a/ACTION s/SEVERITY");
            }

            int index = Integer.parseInt(args.trim().split(" ")[0]);
            String action = args.substring(aIndex + 2, sIndex).trim();
            int severity = Integer.parseInt(args.substring(sIndex + 2).trim());

            if (severity < -5 || severity > 5) {
                throw new IllegalValueException("Severity must be between -5 and 5!");
            }

            return new ActionCommand(index, action, severity);
        } catch (IllegalValueException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalValueException("Format: action CHILD_INDEX a/ACTION s/SEVERITY");
        }
    }

    //@@author prerana-r11

    /**
     * Parses input arguments to create a GiftCommand
     * Expected format: {gift CHILD_INDEX g/GIFT_NAME...}
     * Extracts the target child index and one or more gift names prefixed with g/.
     *
     * @param args the user input arguments
     * @return a GiftCommand containing the parsed child index and gift names
     * @throws IllegalValueException if the input format is invalid,
     *                               index is not a number or no gift names are provided.
     */
    private GiftCommand prepareGiftAction(String args) throws IllegalValueException {
        try {
            String[] parts = args.trim().split(" ");
            int childIndex = Integer.parseInt(parts[0]) - 1;
            ArrayList<String> giftNames = new ArrayList<>();

            addGifts(parts, giftNames);

            if (giftNames.isEmpty()) {
                throw new IllegalValueException("No gift/gifts provided,please enter gift/gifts:)");
            }
            return new GiftCommand(childIndex, giftNames);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Please use valid command format :" +
                    "gift [CHILD_INDEX] g/[GIFT_NAME]");
        }

    }

    private static void addGifts(String[] parts, ArrayList<String> giftNames) {
        StringBuilder gift = null;
        for (int i = 1; i < parts.length; i++) {
            if (parts[i].startsWith(GIFT_PREFIX)) {
                if (gift != null) {
                    giftNames.add(gift.toString().trim());
                }
                gift = new StringBuilder(parts[i].substring(2));
            } else {
                if (gift != null) {
                    gift.append(" ").append(parts[i]);
                }
            }
        }
        if (gift != null) {
            giftNames.add(gift.toString().trim());
        }
    }
    /**
     * Parses input arguments to create a DeGiftCommand.
     * Expected format: degift CHILD_INDEX GIFT_INDEX
     * Removes a specific gift from a child based on the given indices.
     *
     * @param args the user input arguments
     * @return a DeGiftCommand with the specified child and gift indices
     * @throws IllegalValueException if the format is invalid or indices are not valid integers
     */
    private DeGiftCommand prepareDeGiftAction(String args) throws IllegalValueException {
        try {
            String[] parts = args.trim().split(" ");
            if (parts.length < 2) {
                throw new IllegalValueException(
                        "Please use valid command format:" +
                                " degift CHILD_INDEX GIFT_INDEX"
                );
            }
            int childIndex = Integer.parseInt(parts[0]);
            int giftIndex = Integer.parseInt(parts[1]);

            return new DeGiftCommand(childIndex, giftIndex);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Please use valid command format :" +
                    "degift CHILD_INDEX GIFT_INDEX");
        }
    }


    /**
     * Parses input arguments to create a DeliveryStatusCommand.
     * <p>
     * Expected format:delivery_status CHILD_INDEX GIFT_INDEX d/delivered|d/undelivered
     * Updates the delivery status of a specific gift for a child.
     *
     * @param args the user input arguments
     * @return a DeliveryStatusCommand with the parsed indices and delivery status
     * @throws IllegalValueException if the format is invalid, indices are not valid, or status flag is incorrect
     */
    private DeliveryStatusCommand prepareDeliverAction(String args) throws IllegalValueException {
        try {
            String[] parts = args.trim().split(" ");

            if(parts.length<3 || parts[2].trim().isEmpty()){
                throw new IllegalValueException("Please use valid command format: " +
                                "delivery_status CHILD_INDEX GIFT_INDEX d/delivered (or) d/undelivered"
                );
            }
            int childIndex = Integer.parseInt(parts[0]);
            int giftIndex = Integer.parseInt(parts[1]);
            String status = parts[2];
            boolean delivered;

            delivered = isDelivered(status);

            return new DeliveryStatusCommand(childIndex, giftIndex, delivered);

        } catch (NumberFormatException e) {
            throw new IllegalValueException("Please input valid numbers for child index and gift index");
        }
    }

    private static boolean isDelivered(String status) throws IllegalValueException {
        boolean delivered;
        if (status.equals(DELIVERED)) {
            delivered = true;
        } else if (status.equals(UNDELIVERED)) {
            delivered = false;
        } else {
            throw new IllegalValueException("Please use valid command format:"
                    + "delivery_status CHILD_INDEX GIFT_INDEX" +
                    " d/delivered (or) d/undelivered");
        }
        return delivered;
    }

    /**
     * Parses input arguments to create a PrepareGiftCommand
     * Expected format:prepared CHILD_INDEX GIFT_INDEX
     * Marks a specific gift as prepared for delivery.
     *
     * @param args the user input arguments
     * @return a PrepareGiftCommand with the specified child and gift indices
     * @throws IllegalValueException if the format is invalid or indices are not valid integers
     */
    private Command preparePreparedAction(String args) throws IllegalValueException {
        try {
            String[] parts = args.trim().split(" ");
            int childIndex = Integer.parseInt(parts[0]);
            int giftIndex = Integer.parseInt(parts[1]);

            return new PrepareGiftCommand(childIndex, giftIndex);
        } catch (Exception e) {
            throw new IllegalValueException("Format: " +
                    "prepared [childindex] [giftindex]");
        }
    }
    //@@author


    // @@author Aurosky
    private Command prepareFind(String args) throws IllegalValueException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new IllegalValueException("Format: find n/NAME or find a/AGE or find l/LOCATION");
        }

        String query = null;
        FindCommand.SearchType searchType = null;
        
        if (trimmedArgs.startsWith("n/")) {
            query = trimmedArgs.substring(2).trim();
            searchType = FindCommand.SearchType.NAME;
        } else if (trimmedArgs.startsWith("a/")) {
            query = trimmedArgs.substring(2).trim();
            searchType = FindCommand.SearchType.AGE;
            if (!query.isEmpty()) {
                try {
                    int age = Integer.parseInt(query);
                    if (age < 0) {
                        throw new IllegalValueException("Age must be a non-negative integer!");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalValueException("Age must be a valid integer!");
                }
            }
        } else if (trimmedArgs.startsWith("l/")) {
            query = trimmedArgs.substring(2).trim();
            searchType = FindCommand.SearchType.LOCATION;
        }
        
        if (searchType == null || query == null ||
                (query.isEmpty() && searchType == FindCommand.SearchType.NAME)) {
            throw new IllegalValueException("Invalid find format! \n" +
                    "Usage: find n/NAME or find a/AGE or find l/LOCATION");
        }

        return new FindCommand(query, searchType);
    }
    
    private Command prepareElf(String args) throws IllegalValueException {
        String name = null;

        String[] tokens = args.split(" ");

        for (String token : tokens) {
            if (token.startsWith("n/")) {
                name = token.substring(2);
            }
        }

        if (name == null || name.isEmpty()) {
            throw new IllegalValueException("Format: elf n/NAME");
        }

        return new ElfCommand(name);
    }

    private Command prepareTaskAction(String args) throws IllegalValueException {
        try {
            String trimmedArgs = args.trim();

            int tIndex = trimmedArgs.indexOf("t/");

            if (tIndex == -1) {
                throw new IllegalValueException("Invalid format! Format: task ELF_INDEX t/TASK_DESCRIPTION");
            }

            String indexPart = trimmedArgs.substring(0, tIndex).trim();
            if (indexPart.isEmpty()) {
                throw new IllegalValueException("Please provide an Elf index." +
                        " Format: task ELF_INDEX t/TASK_DESCRIPTION");
            }

            int elfIndex = Integer.parseInt(indexPart);

            String taskDescription = trimmedArgs.substring(tIndex + 2).trim();
            if (taskDescription.isEmpty()) {
                throw new IllegalValueException("Task description cannot be empty!");
            }

            return new TaskCommand(elfIndex, taskDescription);

        } catch (NumberFormatException e) {
            throw new IllegalValueException("The Elf index must be a valid integer.");
        } catch (Exception e) {
            throw new IllegalValueException("Format: task ELF_INDEX t/TASK_DESCRIPTION");
        }
    }

    private Command prepareDetask(String args) throws IllegalValueException {
        try {
            String trimmedArgs = args.trim();

            int ePos = trimmedArgs.indexOf("e/");
            int tPos = trimmedArgs.indexOf("t/");

            if (ePos == -1 || tPos == -1) {
                throw new IllegalValueException("Format: detask e/ELF_INDEX t/TASK_INDEX");
            }

            String elfPart = (ePos < tPos)
                    ? trimmedArgs.substring(ePos + 2, tPos).trim()
                    : trimmedArgs.substring(ePos + 2).trim();

            String taskPart = (tPos < ePos)
                    ? trimmedArgs.substring(tPos + 2, ePos).trim()
                    : trimmedArgs.substring(tPos + 2).trim();

            int elfIndex = Integer.parseInt(elfPart);
            int taskIndex = Integer.parseInt(taskPart);

            return new DetaskCommand(elfIndex, taskIndex);

        } catch (NumberFormatException e) {
            throw new IllegalValueException("Indexes must be valid integers.");
        } catch (Exception e) {
            throw new IllegalValueException("Correct format: detask e/ELF_INDEX t/TASK_INDEX");
        }
    }

    private Command prepareEditElf(String args) throws IllegalValueException {
        String trimmedArgs = args.trim();
        int ePos = trimmedArgs.indexOf("e/");
        int nPos = trimmedArgs.indexOf("n/");

        if (ePos == -1 || nPos == -1) {
            throw new IllegalValueException("Invalid format! Expected: editelf e/ELF_INDEX n/NEW_NAME");
        }

        try {
            String elfPart;
            String namePart;

            if (ePos < nPos) {
                elfPart = trimmedArgs.substring(ePos + 2, nPos).trim();
                namePart = trimmedArgs.substring(nPos + 2).trim();
            } else {
                namePart = trimmedArgs.substring(nPos + 2, ePos).trim();
                elfPart = trimmedArgs.substring(ePos + 2).trim();
            }

            if (elfPart.isEmpty()) {
                throw new IllegalValueException("Elf index cannot be empty! Format: e/INDEX");
            }
            if (namePart.isEmpty()) {
                throw new IllegalValueException("New name cannot be empty! Format: n/NAME");
            }

            int elfIndex = Integer.parseInt(elfPart);
            return new EditElfCommand(elfIndex, namePart);

        } catch (NumberFormatException e) {
            throw new IllegalValueException("The Elf index must be a valid integer.");
        }
    }

    private Command prepareRmElf(String args) throws IllegalValueException {
        String trimmedArgs = args.trim();
        int ePos = trimmedArgs.indexOf("e/");

        if (ePos == -1) {
            throw new IllegalValueException("Invalid format! Expected: rmelf e/ELF_INDEX");
        }

        try {
            String indexPart = trimmedArgs.substring(ePos + 2).trim();
            if (indexPart.isEmpty()) {
                throw new IllegalValueException("Please provide an Elf index after 'e/'.");
            }

            int elfIndex = Integer.parseInt(indexPart);
            return new RmElfCommand(elfIndex);

        } catch (NumberFormatException e) {
            throw new IllegalValueException("The Elf index must be a valid integer.");
        }
    }
    
    private void checkNoPendingCommand(String newCommandName) throws IllegalValueException {
        if (pendingCommand != null) {
            throw new IllegalValueException(
                    "Cannot execute '" + newCommandName + "' yet. \n" +
                            "You have a pending '"
                            + pendingCommand.getClass().getSimpleName().replace("Command", "").toLowerCase()
                            + "' waiting for confirmation.\n" +
                            "  - Type 'confirm' to execute the pending command first.\n" +
                            "  - Type 'cancel' to discard it, then retry '" + newCommandName + "'."
            );
        }
    }
    // @@author
}
