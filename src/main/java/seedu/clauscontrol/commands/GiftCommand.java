//@@author prerana-r11
package seedu.clauscontrol.commands;
import seedu.clauscontrol.data.gift.Gift;
import seedu.clauscontrol.data.child.Child;

import java.util.ArrayList;

public class GiftCommand extends Command{
    private int childIndex;
    private ArrayList<String> giftNames;

    public GiftCommand(int childIndex, ArrayList<String> giftNames){
        this.childIndex=childIndex;
        this.giftNames=giftNames;
    }

    @Override
    public String execute(){
        //@@author GShubhan
        if (!isFinalized) {
            return "Please finalise the lists before allocating gifts!";
        }
        //@@author
        if(childIndex<1 || childIndex>childList.size()){
            return "Please enter valid child index";
        }
        Child child= childList.get(childIndex-1);
        for(String name :giftNames){
            if(name==null || name.trim().isEmpty()){
                return "Please enter a gift name";
            }
            Gift gift=new Gift(name);
            child.addGift(gift);
        }
        return "Added gifts to child " + (childIndex+1) + ": " + giftNames;
    }
}
//@@author
