//@@author prerana-r11
package seedu.clauscontrol.storage;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.exception.IllegalValueException;
import seedu.clauscontrol.data.gift.Gift;
import seedu.clauscontrol.data.child.Name;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final Logger logger = Logger.getLogger(Storage.class.getName());
    private String filePath;

    public Storage(String filePath){
        this.filePath=filePath;
    }

    public void save(List<Child> children) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (Child child : children) {
            //@@author shrabasti-c
            writer.write("CHILD|" + child.getName() + "|" +
                    ((child.getLocation() == null) ? "" : child.getLocation()) + "|" + child.getAge());
            //@@author
            writer.newLine();

            for (Gift gift : child.getGifts()) {
                writer.write("GIFT|"
                        + gift.getGiftName() + "|"
                        + gift.getState());
                writer.newLine();
            }
        }

        writer.close();
    }

    //this function was written with the aid of ChatGPT
    public List<Child> load() throws IOException {
        List<Child> children = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        Child currentChild = null;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");

            if (parts[0].equals("CHILD")) {
                try {
                    String name = parts[1];
                    int age = -1;
                    String location = null;

                    if (parts.length > 2 && !parts[2].isEmpty()) {
                        location = parts[2];
                    }

                    if (parts.length > 3 && !parts[3].isEmpty()) {
                        age = Integer.parseInt(parts[3]);
                    }

                    currentChild = new Child(new Name(name), age, location);

                    children.add(currentChild);
                } catch (IllegalValueException e) {
                    logger.warning("Invalid child name in file: " + parts[1]);
                }

            } else if (parts[0].equals("GIFT")) {
                if (currentChild == null) {
                    throw new IllegalStateException("Gift before child in file");
                }

                String giftName = parts[1];
                Gift.State state = Gift.State.valueOf(parts[2]);

                Gift gift = new Gift(giftName);
                switch (state) {
                case PREPARED:
                    gift.markPrepared();
                    break;
                case DELIVERED:
                    gift.markDelivered();
                    break;
                default:
                    break;
                }

                currentChild.addGift(gift);
            }
        }

        reader.close();
        return children;
    }
}
//@@author
