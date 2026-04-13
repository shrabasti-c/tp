package seedu.clauscontrol.commands;

import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.gift.Gift;
import java.util.ArrayList;

//@@author shrabasti-c
/**
 * Views a child in the child list.
 */
public class ViewCommand extends Command{
    private final int childIndex;

    public ViewCommand(int childIndex) {
        this.childIndex = childIndex;
    }

    /**
     * Executes the view command.
     * Displays details of a child profile given an index, comprising:
     * <ul>
     *     <li> child's name (minimally) </li>
     *     <li> child's age </li>
     *     <li> child's location </li>
     *     <li> child's gifts </li>
     *     <li> child's actions and their severities </li>
     *     <li> child's status (naughty/nice) </li>
     * </ul>
     *
     * @return String comprising child profile list or error message.
     */
    @Override
    public String execute() {
        if (childIndex < 0 || childIndex >= childList.size()) {
            return "Invalid index position :(\nRefer to the " +
                    "child list for valid positions!";
        }

        Child child = childList.get(childIndex);
        
        String ageMessage = getAgeMessage(child);

        String locationMessage = getLocationMessage(child);

        StringBuilder sb= new StringBuilder("Name: " + child.getName() + " \n");
        sb.append("Age: ").append(ageMessage).append("\n");
        sb.append("Location: ").append(locationMessage).append("\n");

        ArrayList<Gift> gifts = child.getGifts();
        if (!gifts.isEmpty()) {
            sb.append("Gifts: \n");
            createGiftList(gifts, sb);
        }

        ArrayList<String> actions = child.getActions();
        ArrayList<Integer> severities = child.getSeverities();
        if (!actions.isEmpty()) {
            sb.append("Actions: \n");
            createActionList(actions, severities, sb);
        }

        String listAssignment = getListAssignment(child);

        sb.append("List Assignment: ").append(listAssignment);
        return sb.toString();
    }

    private String getListAssignment(Child child) {
        if (child.getListAssignment() != null) {
            return child.getListAssignment();
        }

        if (child.getSeverities().isEmpty() && child.getActions().isEmpty()){
            return "Yet to be evaluated/assigned.";
        }

        if (child.isNaughty()) {
            return "naughty";
        } else {
            return "nice";
        }
    }

    private String getLocationMessage(Child child) {
        String location = child.getLocation();
        if (location == null) {
            return "Not Provided";
        } else {
            return location;
        }
    }

    private String getAgeMessage(Child child) {
        int age = child.getAge();
        if (age == -1) {
            return "Not Provided";
        } else {
            return String.valueOf(age);
        }
    }

    /**
     * Builds gift list to display in profile.
     *
     */
    private static void createGiftList(ArrayList<Gift> gifts, StringBuilder sb) {
        for (int j = 0; j < gifts.size(); j++) {
            sb.append("   ")
                    .append(j + 1)
                    .append(". ")
                    .append(gifts.get(j))
                    .append("\n");
        }
    }

    /**
     * Builds actions and severities list to display in profile.
     *
     */
    private static void createActionList(ArrayList<String> actions, ArrayList<Integer> severities, StringBuilder sb) {
        for (int j = 0; j < actions.size(); j++) {
            sb.append("   ")
                    .append(j + 1)
                    .append(". ")
                    .append(actions.get(j))
                    .append(" (")
                    .append(severities.get(j))
                    .append(")")
                    .append("\n");
        }
    }
}
//@@author
