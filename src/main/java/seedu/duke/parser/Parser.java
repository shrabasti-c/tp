package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.DeGiftCommand;
import seedu.duke.commands.DeliverGiftCommand;
import seedu.duke.commands.GiftCommand;
import seedu.duke.commands.PrepareGiftCommand;
import seedu.duke.commands.ActionCommand;
import seedu.duke.commands.ChildCommand;
import seedu.duke.commands.ChildListCommand;
import seedu.duke.commands.EditCommand;
import seedu.duke.commands.ElfCommand;
import seedu.duke.commands.ElfListCommand;
import seedu.duke.commands.FinalizeCommand;
import seedu.duke.commands.FindCommand;
import seedu.duke.commands.GiftListCommand;
import seedu.duke.commands.NaughtyCommand;
import seedu.duke.commands.NiceCommand;
import seedu.duke.commands.ReassignCommand;
import seedu.duke.commands.TaskCommand;
import seedu.duke.commands.ViewCommand;
import seedu.duke.commands.DeleteCommand;

import seedu.duke.data.exception.IllegalValueException;

import java.util.ArrayList;


public class Parser {

    //@@author shrabasti-c-reused
    // ChatGPT was used to generate the boilerplate of parseCommand function with reference from https://github.com/
    // se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java and supervision from the author
    public Command parseCommand(String userInput) throws IllegalValueException {
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
            return prepareDelete(arguments);
        //@@author

        //@@author Kiri
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
            return prepareDeGiftAction(arguments);
        case "deliver":
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
    // ChatGPT was used to generate the prepareAdd function with reference from https://github.com/
    // se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java and supervision from the author
    private Command prepareAdd(String args) throws IllegalValueException {
        String name = null;

        String[] tokens = args.split(" ");

        for (String token : tokens) {
            if (token.startsWith("n/")) {
                name = token.substring(2);
            }
        }

        if (name == null || name.isEmpty()) {
            throw new IllegalValueException("Format: child n/NAME");
        }

        return new ChildCommand(name);
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
    private DeliverGiftCommand prepareDeliverAction(String args) throws IllegalValueException {
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

            return new DeliverGiftCommand(childIndex,giftIndex,delivered);

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
        String name = null;
        String[] tokens = args.split(" ");
        
        for (String token : tokens) {
            if (token.startsWith("n/")) {
                name = token.substring(2).trim();
                break;
            }
        }
        
        if (name == null || name.isEmpty()) {
            throw new IllegalValueException("Format: find n/NAME");
        }
        
        return new FindCommand(name);
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
    // @@author
}

