//@@author Aurosky
package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.child.Child;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FindCommand extends Command {
    private static final Logger logger = Logger.getLogger(FindCommand.class.getName());
    
    public enum SearchType {
        NAME, AGE, LOCATION
    }
    
    private final String query;
    private final SearchType type;
    
    public FindCommand(String query, SearchType type) {
        assert query != null : "Search query should not be null";
        assert type != null : "Search type should not be null";
        
        this.query = query.trim().toLowerCase();
        this.type = type;
        
        logger.log(Level.FINE, "FindCommand initialized. Type: " + type + ", Query: [" + this.query + "]");
    }
    
    @Override
    public String execute() {
        assert childList != null : "childList should be initialized before execution";
        
        logger.log(Level.INFO, "Starting search. Type: " + type + ", Query: " + query);
        
        if (childList.isEmpty()) {
            logger.log(Level.INFO, "Search aborted: childList is empty.");
            return "The child list is empty!";
        }
        
        StringBuilder sb = new StringBuilder();
        int count = 0;
        
        for (int i = 0; i < childList.size(); i++) {
            Child child = childList.get(i);
            
            assert child != null : "Child at index " + i + " should not be null";
            assert child.getName() != null : "Child name should not be null";
            
            boolean isMatch = false;
            
            switch (type) {
            case NAME:
                isMatch = child.getName().toString().toLowerCase().contains(query);
                break;
            
            case AGE:
                if (child.hasAge()) {
                    isMatch = String.valueOf(child.getAge()).equals(query);
                }
                break;
            
            case LOCATION:
                if (child.hasLocation()) {
                    assert child.getLocation() != null : "Child location should not be null if hasLocation is true";
                    isMatch = child.getLocation().toLowerCase().contains(query);
                }
                break;
            default:
                assert false : "Unhandled SearchType: " + type;
                break;
            }
            
            if (isMatch) {
                String ageInfo = child.hasAge() ? ", Age: " + child.getAge() : "";
                String locInfo = child.hasLocation() ? ", Loc: " + child.getLocation() : "";
                
                sb.append(String.format("[%d] %s%s%s\n",
                        i + 1, child.getName(), ageInfo, locInfo));
                count++;
            }
        }
        
        if (count == 0) {
            logger.log(Level.INFO, "Search completed: No matches found for " + type + " [" + query + "]");
            return "No match found for " + type.toString().toLowerCase() + ": " + query;
        } else {
            logger.log(Level.INFO, "Search completed: Found " + count + " match(es).");
            return "Found " + count + " matches:\n" + sb.toString().trim();
        }
    }
}
//@@author
