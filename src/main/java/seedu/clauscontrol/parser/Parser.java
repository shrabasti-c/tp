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

import seedu.clauscontrol.data.exception.IllegalValueException;

import java.util.ArrayList;


public class Parser {
    private static Command pendingCommand = null;
    

    //@@author shrabasti-c-reused
    // ChatGPT was used to generate the boilerplate of parseCommand function with reference from https://github.com/
    // se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java and supervision from the author
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
        pendingCommand = null;
        //@@author
        
        String[] parts = userInput.trim().split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "child":
            return prepareAdd(arguments);

        case "view":
            return prepareView(arguments);

        case "edit":
            return prepareEdit(arguments);

        case "delete":
            pendingCommand = prepareDelete(arguments);
            throw new IllegalValueException("WARNING: You are about to delete a child. Type 'confirm' to proceed.");
        
        //@@author

        //@@author Aurosky
        case "childlist":
            return new ChildListCommand();

        case "elflist":
            return new ElfListCommand();
        
        case "find":
            return prepareFind(arguments);
        
        case "elf":
            return prepareElf(arguments);
        
        case "task":
            return prepareTaskAction(arguments);
            
        case "detask":
            pendingCommand = prepareDetask(arguments);
            throw new IllegalValueException("WARNING: You are about to remove a task. Type 'confirm' to proceed.");
            
        case "editelf":
            return prepareEditElf(arguments);
        
        case "rmelf":
            pendingCommand = prepareRmElf(arguments);
            throw new IllegalValueException("WARNING: You are about to remove an Elf. Type 'confirm' to proceed.");
        
        case "reset":
            pendingCommand = new ResetCommand();
            throw new IllegalValueException("WARNING: This will wipe ALL data and reset to initial state. " +
                    "Type 'confirm' to proceed.");
        //@@author

        case "action":
            return prepareAction(arguments);

        //@@author

        //@@author GShubhan
        case "nice":
            return new NiceCommand();
        case "finalize":
            // fall through
        case "finalise":
            return new FinalizeCommand();
        //@@author
        //@@author GShubhan
        case "naughty":
            return new NaughtyCommand();
        //@@author
        case "reassign":
            int index = Integer.parseInt(arguments.split(" ")[0]);
            String list = arguments.split(" ")[1].substring(2);
            return new ReassignCommand(index, list);
        //@@author


        //@@author prerana-r11
        case "gift":
            return prepareGiftAction(arguments);
        case "degift":
            pendingCommand = prepareDeGiftAction(arguments);
            throw new IllegalValueException("WARNING: You are about to remove a gift. Type 'confirm' to proceed.");
        case "delivery_status":
            return prepareDeliverAction(arguments);
        case "giftlist":
            return new GiftListCommand();
        case "prepared":
            return preparePreparedAction(arguments);
        //@@author

        default:
            throw new IllegalValueException(
                    "Unknown command. Did you mean 'child' or 'childlist'?");
        }

    }

    //@@author shrabasti-c-reused
    // ChatGPT was used to generate the boilerplate of the prepareAdd function with reference from https://github.com/
    // se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java and supervision from the author
    private Command prepareAdd(String args) throws IllegalValueException {
        String name = null;
        String location = null;
        String ageString = null;
        int age = 0;

        String[] tokens = args.split(" (?=[nla]/)");

        for (String token : tokens) {
            if (token.startsWith("n/")) {
                name = token.substring(2);
            }
            if (token.startsWith("l/")) {
                location = token.substring(2);
            }
            if (token.startsWith("a/")) {
                ageString = token.substring(2);
            }
        }

        checkValidity(name);
        checkValidity(location);
        checkValidity(ageString);

        age = Integer.parseInt(ageString);
        
        return new ChildCommand(name, location, age);
    }

    private static void checkValidity(String ageString) throws IllegalValueException {
        if (ageString == null || ageString.isEmpty()) {
            throw new IllegalValueException("Format: age a/AGE");
        }
    }
    //@@author

    //@@author shrabasti-c
    private Command prepareView(String args) throws IllegalValueException {
        try {
            int childIndex= Integer.parseInt(args.trim()) - 1;
            return new ViewCommand(childIndex);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Please use valid command format : view [childindex]");
        }
    }

    private Command prepareDelete(String args) throws IllegalValueException {
        try {
            int childIndex= Integer.parseInt(args.trim()) - 1;
            return new DeleteCommand(childIndex);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Please use valid command format : delete [childindex]");
        }
    }

    private Command prepareEdit(String args) throws IllegalValueException {
        try {
            int nIndex = args.indexOf("n/");

            if (nIndex == -1) {
                throw new IllegalValueException("Format: edit CHILD_INDEX n/NAME");
            }

            int index = Integer.parseInt(args.trim().split(" ")[0]) - 1;
            String newName = args.substring(nIndex + 2).trim();
            return new EditCommand(index, newName);
        } catch (IllegalValueException e) {
            throw e;
        } catch (Exception e) {
            throw new IllegalValueException("Format: edit CHILD_INDEX n/NAME");
        }
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
    private GiftCommand prepareGiftAction(String args) throws IllegalValueException {
        try {
            String[] parts=args.trim().split(" ");
            int childIndex= Integer.parseInt(parts[0])-1;
            ArrayList<String> giftNames= new ArrayList<>();

            for(int i=1;i<parts.length;i++){
                if(parts[i].startsWith("g/")){
                    giftNames.add(parts[i].substring(2));
                }
            }

            if(giftNames.isEmpty()){
                throw new IllegalValueException("Please enter gift names:)");
            }
            return new GiftCommand(childIndex, giftNames);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Please use valid command format : g/");
        }

    }
    private DeGiftCommand prepareDeGiftAction(String args) throws IllegalValueException {
        try {
            String[] parts=args.trim().split(" ");
            int childIndex= Integer.parseInt(parts[0]);
            int giftIndex= Integer.parseInt(parts[1]);

            return new DeGiftCommand(childIndex, giftIndex);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Please use valid command format : degift [childindex] [giftindex]");
        }
    }
    private DeliveryStatusCommand prepareDeliverAction(String args) throws IllegalValueException {
        try {
            String[] parts=args.trim().split(" ");
            int childIndex= Integer.parseInt(parts[0]);
            int giftIndex= Integer.parseInt(parts[1]);
            String status=parts[2];
            boolean delivered;

            if(status.equals("d/delivered")){
                delivered=true;
            } else if(status.equals("d/undelivered")){
                delivered=false;
            } else{
                throw new IllegalValueException("Use d/delivered or d/undelivered please");
            }

            return new DeliveryStatusCommand(childIndex,giftIndex,delivered);

        } catch (NumberFormatException e) {
            throw new IllegalValueException("input numbers for child index and gift index");
        }
    }
    private Command preparePreparedAction(String args) throws IllegalValueException{
        try{
            String[] parts=args.trim().split(" ");
            int childIndex= Integer.parseInt(parts[0]);
            int giftIndex= Integer.parseInt(parts[1]);

            return new PrepareGiftCommand(childIndex,giftIndex);
        } catch(Exception e){
            throw new IllegalValueException("Format: prepared [childindex] [giftindex]");
        }
    }
    //@@author

    
    // @@author Kiri
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
            
            try {
                Integer.parseInt(query);
            } catch (NumberFormatException e) {
                throw new IllegalValueException("Age must be a valid integer!");
            }
        } else if (trimmedArgs.startsWith("l/")) {
            query = trimmedArgs.substring(2).trim();
            searchType = FindCommand.SearchType.LOCATION;
        }
        
        if (searchType == null || query == null || query.isEmpty()) {
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
    // @@author
}
