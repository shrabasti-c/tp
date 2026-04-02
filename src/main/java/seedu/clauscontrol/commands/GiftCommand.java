//@@author prerana-r11
package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.gift.Gift;
import seedu.clauscontrol.data.child.Child;

import java.util.ArrayList;

/**
 * Adds one or more gifts to a specified child.
 * <p>
 * This command assigns new  Gift objects to a child identified by index.
 * It can only be executed after the lists have been finalised.
 * On successful execution, all provided gifts are added to the child.
 */
public class GiftCommand extends Command {
    private int childIndex;
    private ArrayList<String> giftNames;

    /**
     * Creates a GiftCommand to assign gifts to a child.
     *
     * @param childIndex the index of the target child
     * @param giftNames  the list of gift names to be assigned
     */
    public GiftCommand(int childIndex, ArrayList<String> giftNames) {
        this.childIndex = childIndex;
        this.giftNames = giftNames;
    }

    /**
     * Executes the gift assignment.
     * Adds each gift in the provided list to the specified child if all validations pass.
     *
     * @return a  message listing the added gifts, or an error message if validation fails
     */
    @Override
    public String execute() {
        //@@author GShubhan
        if (!isFinalized) {
            return "Please finalise the lists before allocating gifts!";
        }
        //@@author
        if (childIndex < 0 || childIndex >= childList.size()) {
            return "Invalid child index!";
        }
        if (giftNames == null || giftNames.isEmpty()) {
            return "Please enter at least one gift name";
        }
        Child child = childList.get(childIndex);

        for (String name : giftNames) {
            if (isInvalidGiftName(name)) {
                return "Gift names cannot be empty";
            }
        }

        for (String name : giftNames) {
            child.addGift(new Gift(name));
        }
        return "Added gifts to child " + (childIndex + 1) + ": " + giftNames;
    }

    private boolean isInvalidGiftName(String name) {
        return name == null || name.trim().isEmpty();
    }
}
//@@author
