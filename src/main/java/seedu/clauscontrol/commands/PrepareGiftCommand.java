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
    public String execute(){
        Child child=childList.get(childIndex-1);
        Gift gift=child.getGifts().get(giftIndex-1);

        try{
            gift.markPrepared();
        } catch(Exception e){
            return e.getMessage();
        }
        return "Gift marked as prepared!!!" + gift;
    }
}
//@@author
