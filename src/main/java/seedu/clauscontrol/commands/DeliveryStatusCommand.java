//@@author prerana-r11
package seedu.clauscontrol.commands;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.gift.Gift;

public class DeliveryStatusCommand extends Command{
    private int childIndex;
    private int giftIndex;
    private boolean delivered;

    public DeliveryStatusCommand(int childIndex, int giftIndex, boolean delivered){
        this.childIndex=childIndex;
        this.giftIndex=giftIndex;
        this.delivered=delivered;
    }

    @Override
    public String execute(){
        if(childIndex<1 || childIndex>childList.size()){
            return "Please enter valid index value";
        }
        Child child=childList.get(childIndex-1);

        if(giftIndex<1 || giftIndex>child.getGifts().size()){
            return "Please enter valid index value";
        }

        Gift gift=child.getGifts().get(giftIndex-1);

        try{
            if(delivered){
                gift.markDelivered();
            } else {
                gift.markUndelivered();
            }
        } catch(Exception e){
            return e.getMessage();
        }
        return "Gift status updated, check giftlist!" + gift;

    }
}
//@@author
