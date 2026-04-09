//@@author prerana-r11
package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.gift.Gift;
/**
 * Removes a gift from the giftlist for a child.
 * <p>
 * This command allows the user to remove a particular gift for a particular child.
 * It validates the child and gift indices before updating the status.
 */
public class DeGiftCommand extends Command {
    private int childIndex;
    private int giftIndex;

    /**
     * @param childIndex the index of the target child
     * @param giftIndex  the index of the target gift
     */
    public DeGiftCommand(int childIndex, int giftIndex) {
        this.childIndex = childIndex;
        this.giftIndex = giftIndex;
    }
    public int getChildIndex() {
        return childIndex;
    }
    public int getGiftIndex() {
        return giftIndex;
    }

    /**
     * Executes the degift command.
     * Validates indices of child and gift.
     *
     * @return a  message or an error message if validation fails
     */
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
        try {
            if (gift.isDelivered()) {
                return "Cannot remove a delivered gift!";
            }
            child.getGifts().remove(giftIndex - 1);
        } catch (IllegalStateException e) {
            return e.getMessage();
        }

        return " gift removed " + giftIndex + " for child " + child.getName();

    }
}
//@@author
