//@@author prerana-r11
package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.gift.Gift;

/**
 * Updates the delivery status of a specific gift for a given child.
 * <p>
 * This command allows marking a gift as delivered or undelivered.
 * It validates the child and gift indices before updating the status.
 */
public class DeliveryStatusCommand extends Command {
    private int childIndex;
    private int giftIndex;
    private boolean delivered;

    /**
     * Creates a command to update a gift's delivery status.
     *
     * @param childIndex the index of the target child
     * @param giftIndex  the index of the target gift
     * @param delivered  true to mark as delivered, false to mark as undelivered
     */
    public DeliveryStatusCommand(int childIndex, int giftIndex, boolean delivered) {
        this.childIndex = childIndex;
        this.giftIndex = giftIndex;
        this.delivered = delivered;
    }

    /**
     * Executes the delivery status update.
     * Validates indices and updates the gift's status
     *
     * @return a  message with updated gift details, or an error message if validation fails
     */
    @Override
    public String execute() {
        if (!isValidChildIndex()) {
            return "Please enter valid index value";
        }
        Child child = childList.get(childIndex-1);

        if (!isValidGiftIndex(child)) {
            return "Please enter valid index value";
        }
        Gift gift = child.getGifts().get(giftIndex-1);

        if (delivered && gift.isDelivered()) {
            return "Gift is already delivered!";
        }
        if (!delivered && !gift.isDelivered()) {
            return "Gift is already undelivered!";
        }
        if (delivered) {
            gift.markDelivered();
        } else {
            gift.markUndelivered();
        }
        return "Gift status updated! " + gift;
    }

    private boolean isValidGiftIndex(Child child) {
        return giftIndex >= 1 && giftIndex <= child.getGifts().size();

    }

    private boolean isValidChildIndex() {
        return childIndex>= 1 && childIndex <= childList.size();
    }
}
//@@author
