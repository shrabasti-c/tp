//@@author prerana-r11
package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.gift.Gift;

/**
 * Marks a specific gift as prepared for delivery.
 * <p>
 * This command updates the state of a gift belonging to a child.
 * It ensures that the child and gift indices are valid
 * The gift has not already been delivered.
 * <p>
 * Delivered gifts cannot be prepared again.
 */
public class PrepareGiftCommand extends Command {
    private int childIndex;
    private int giftIndex;

    /**
     * Creates a command to mark a gift as prepared.
     *
     * @param childIndex the index of the target child
     * @param giftIndex  the index of the target gift
     */
    public PrepareGiftCommand(int childIndex, int giftIndex) {
        this.childIndex = childIndex;
        this.giftIndex = giftIndex;
    }

    /**
     * Executes the prepare gift operation.
     * <p>
     * Validates indices and updates the gift state to prepared if allowed.
     *
     * @return a  message if the operation is successful,or an error message if validation fails
     */
    @Override
    public String execute() {
        if (!isFinalized) {
            return "Please finalise the lists before preparing gifts!";
        }
        if (!isValidChildIndex()) {
            return "Invalid child index";
        }
        Child child = childList.get(childIndex-1);

        if (!isValidGiftIndex(child)) {
            return "Invalid gift index";
        }
        Gift gift = child.getGifts().get(giftIndex-1);


        if (gift.isDelivered()) {
            return "Cannot prepare a delivered gift, try another command!";
        }
        if(gift.getState()== Gift.State.PREPARED){
            return "Gift is already prepared !";
        }
        gift.markPrepared();
        return "Gift marked as prepared! " + gift;
    }

    private boolean isValidGiftIndex(Child child) {
        return giftIndex >= 1 && giftIndex <= child.getGifts().size();
    }

    private boolean isValidChildIndex() {
        return childIndex >= 1 && childIndex <= childList.size();
    }
}
//@@author
