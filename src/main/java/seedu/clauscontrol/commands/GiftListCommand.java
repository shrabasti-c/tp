//@@author prerana-r11
package seedu.clauscontrol.commands;
import seedu.clauscontrol.data.gift.Gift;
import seedu.clauscontrol.data.child.Child;

import java.util.ArrayList;

/**
 * Displays all gifts assigned to each child.
 *
 * This command iterates through all children and lists their associated gifts.
 * Only children with assigned gifts are displayed.
 *
 * If no children exist or no gifts are assigned, an appropriate message is returned.
 */
public class GiftListCommand extends Command{

    /**
     * Executes the gift listing operation.
     *
     * @return a formatted string showing all children with their assigned gifts,
     *                            or a message if no children or gifts exist
     */
    @Override
    public String execute(){
        if(childList==null || childList.isEmpty()){
            return "No children added";
        }

        StringBuilder sb= new StringBuilder(
                "Here are all the gifts:\n "
        );
        boolean hasGifts=false;

        for(int i=0;i<childList.size();i++) {
            Child child = childList.get(i);
            ArrayList<Gift> gifts = child.getGifts();
            if (!gifts.isEmpty()) {
                hasGifts=true;
                sb.append((i + 1)).append(". ")
                        .append(child.getName()).append(":\n");

                createList(gifts, sb);
            }
        }
        if(!hasGifts){
            return "No gifts assigned :(";
        }
        return sb.toString();
    }

    private static void createList(ArrayList<Gift> gifts, StringBuilder sb) {
        for (int j = 0; j < gifts.size(); j++) {
            sb.append("   ")
                    .append(j + 1)
                    .append(". ")
                    .append(gifts.get(j))
                    .append("\n");
        }
    }
}
//@@author

