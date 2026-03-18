// ChatGPT was used to generate the parseCommand and prepareAdd functions with reference from https://github.com/
// se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java and supervision from the author
package seedu.duke.parser;

import seedu.duke.commands.ActionCommand;
import seedu.duke.commands.ChildCommand;
import seedu.duke.commands.ChildListCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeliverGiftCommand;
import seedu.duke.commands.ElfListCommand;
import seedu.duke.commands.FindCommand;
import seedu.duke.commands.GiftCommand;
import seedu.duke.commands.GiftListCommand;
import seedu.duke.commands.NiceCommand;
import seedu.duke.commands.DeGiftCommand;
import seedu.duke.commands.ViewCommand;

import seedu.duke.data.exception.IllegalValueException;

import java.util.ArrayList;

public class Parser {
    public Command parseCommand(String userInput) throws IllegalValueException {
        String[] parts = userInput.trim().split(" ", 2);

        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "child":
            return prepareAdd(arguments);

        case "view":
            return prepareView(arguments);

        case "childlist":
            return new ChildListCommand();

        case "elflist":
            return new ElfListCommand();

        case "find":
            return new FindCommand(arguments);

        case "action":
            return prepareAction(arguments);
        //@@author

        //@@author GShubhan
        case "nice":
            return new NiceCommand();
        //@@author
            

        case "gift":
            return prepareGiftAction(arguments);
        case "degift":
            return prepareDeGiftAction(arguments);
        case "deliver":
            return prepareDeliverAction(arguments);
        case "giftlist":
            return new GiftListCommand();

        default:
            throw new IllegalValueException(
                    "Unknown command. Did you mean 'child' or 'childlist'?");
        }

    }

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

    private Command prepareView(String args) throws IllegalValueException {
        try {
            int childIndex= Integer.parseInt(args.trim()) - 1;
            return new ViewCommand(childIndex);
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Please use valid command format : view [childindex]");
        }
    }

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
            String status=parts[2].toLowerCase();

            boolean delivered;
            if(status.equals("delivered")){
                delivered=true;
            } else if(status.equals("undelivered")){
                delivered=false;
            } else{
                throw new IllegalValueException("Status must be 'delivered' or 'undelivered'");
            }

            return new DeliverGiftCommand(childIndex,giftIndex,delivered);

        } catch (NumberFormatException e) {
            throw new IllegalValueException("Please use valid command format :" +
                    " deliver [childindex] [giftindex] [delivered/undelivered]");
        }


    }
}
