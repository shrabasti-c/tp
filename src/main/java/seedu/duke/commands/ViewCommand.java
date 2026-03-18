//@@author shrabasti-c
package seedu.duke.commands;

import seedu.duke.data.child.Child;
import seedu.duke.data.gift.Gift;

import java.util.ArrayList;

public class ViewCommand extends Command{
    private final int childIndex;

    public ViewCommand(int childIndex) {
        this.childIndex = childIndex;
    }

    @Override
    public String execute() {
        if (childIndex < 0 || childIndex >= childList.size()) {
            return "Invalid position :(";
        }

        Child child = childList.get(childIndex);

        StringBuilder sb= new StringBuilder("Name: " + child.getName()+" \n");

        ArrayList<Gift> gifts = child.getGifts();
            if (!gifts.isEmpty()) {
                sb.append("Gifts: \n");
                createList(gifts, sb);
            }
        sb.append("Current Status: ").append((child.getTotalScore() >= 0) ? "Nice" : "Naughty").append(" \n");
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
