// ChatGPT was used to generate the parseCommand and prepareAdd functions with reference from https://github.com/
// se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java and supervision from the author
package seedu.duke.parser;

import seedu.duke.commands.ChildCommand;
import seedu.duke.commands.ChildListCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.ElfListCommand;
import seedu.duke.commands.FindCommand;
import seedu.duke.data.exception.IllegalValueException;

public class Parser {
    public Command parseCommand(String userInput) throws IllegalValueException {
        String[] parts = userInput.trim().split(" ", 2);

        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";
        
        switch (commandWord) {
        
        case "child":
            return prepareAdd(arguments);
        
        case "childlist":
            return new ChildListCommand();
        
        case "elflist":
            return new ElfListCommand();
            
        case "find":
            return new FindCommand(arguments);
            
        default:
            throw new IllegalValueException("Unknown command. Did you mean 'child' or 'childlist'?");
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
}
