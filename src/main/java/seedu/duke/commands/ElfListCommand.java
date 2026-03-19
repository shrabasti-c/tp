//@@author Kiri
package seedu.duke.commands;

public class ElfListCommand extends Command {
    
    @Override
    public String execute() {
        
        if (elfList == null || elfList.isEmpty()) {
            return "The elf list is empty!";
        }
        StringBuilder sb = new StringBuilder("Here are all elf:\n");
        for (int i = 0; i < elfList.size(); i++) {
            sb.append((i + 1)).append(". ").append(elfList.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
    
}
//@@author
