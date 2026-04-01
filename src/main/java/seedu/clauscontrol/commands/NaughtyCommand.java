//@@author GShubhan
package seedu.clauscontrol.commands;

/**
 * Lists all children on the naughty list.
 */
public class NaughtyCommand extends Command {

    @Override
    public String execute() {
        if (childList.isEmpty()) {
            return "The child list is empty!";
        }
        StringBuilder sb = new StringBuilder("Here are the children on the naughty list:\n");
        int count = 1;
        for (int i = 0; i < childList.size(); i++) {
            if (childList.get(i).isNaughty()) {
                sb.append(count).append(". ")
                        .append(childList.get(i).getName())
                        .append(" (Score: ").append(childList.get(i).getTotalScore()).append(")\n");
                count++;
            }
        }
        if (count == 1) {
            return "No children on the naughty list!";
        }
        return sb.toString().trim();
    }
}
//@@author
