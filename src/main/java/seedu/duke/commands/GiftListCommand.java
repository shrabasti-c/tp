//@@author prerana-r11
package seedu.duke.commands;
import seedu.duke.data.gift.Gift;
import seedu.duke.data.child.Child;

import java.util.ArrayList;

public class GiftListCommand extends Command{

    @Override
    public String execute(){
        if(childList==null || childList.isEmpty()){
            return "No children added";
        }

        StringBuilder sb= new StringBuilder("Here are all the gifts: \n ");
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

