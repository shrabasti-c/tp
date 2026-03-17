package seedu.duke.commands;
import seedu.duke.data.gift.Gift;
import seedu.duke.data.child.Child;

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
        if(childIndex<0 || childIndex>childList.size()){
            return "Please enter valid child index";
        }
        Child child= childList.get(childIndex);
        for(String name :giftNames){
            Gift gift=new Gift(name);
            child.addGift(gift);
        }
        return "Added gifts to child " + childIndex+1 + ": " + giftNames;
    }
}
