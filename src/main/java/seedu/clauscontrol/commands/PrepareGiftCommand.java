//@@author prerana-r11
package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.gift.Gift;

public class PrepareGiftCommand extends Command{
    private int childIndex;
    private int giftIndex;

    public PrepareGiftCommand(int childIndex,int giftIndex){
        this.childIndex=childIndex;
        this.giftIndex=giftIndex;
    }

    @Override
    public String execute() {
        if (childIndex < 1 || childIndex > childList.size()) {
            return "Please enter valid child index";
        }
        Child child = childList.get(childIndex - 1);

        if (giftIndex < 1 || giftIndex > child.getGifts().size()) {
            return "Please enter valid gift index";
        }
        Gift gift = child.getGifts().get(giftIndex - 1);


        if (gift.isDelivered()) {
            return "Cannot prepare a delivered gift, try another command!";
        }
        gift.markPrepared();
        return "Gift marked as prepared!!!" + gift;
    }
}
//@@author
