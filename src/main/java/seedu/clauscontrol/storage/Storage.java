//@@author prerana-r11
package seedu.clauscontrol.storage;
import seedu.clauscontrol.data.child.Child;
import seedu.clauscontrol.data.elf.Elf;
import seedu.clauscontrol.data.elf.ElfTask;
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

    public void save(List<Child> children,List<Elf> elves) throws IOException {
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
        for (Elf elf : elves) {
            writer.write("ELF|" + elf.getName());
            writer.newLine();

            for (ElfTask task : elf.getTasks()) {
                writer.write("TASK|" + task.toString());
                writer.newLine();
            }
        }

        writer.close();
    }

    //this function was written with the aid of ChatGPT
    public StorageData load() throws IOException {
        List<Child> children = new ArrayList<>();
        List<Elf> elves = new ArrayList<>();

        Child currentChild = null;
        Elf currentElf = null;

        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;


        while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.trim().split("\\|");
            if (parts.length == 0) {
                continue;
            }

            switch (parts[0]) {
            case "CHILD": {
                currentElf = null;
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
                break;
            }
            case "GIFT": {
                if (currentChild == null) {
                    throw new IllegalStateException("Gift before child in file");
                }

                String giftName = parts[1];
                Gift.State state;
                try {
                    state = Gift.State.valueOf(parts[2]);
                } catch (IllegalArgumentException e) {
                    logger.warning("Invalid gift state in file: " + parts[2]);
                    continue;
                }

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
                break;
            }
            case "ELF": {
                currentChild = null;
                try {
                    currentElf = new Elf(new Name(parts[1]));
                    elves.add(currentElf);
                } catch (IllegalValueException e) {
                    logger.warning("Invalid elf name: " + parts[1]);
                }
                break;
            }
            case "TASK": {
                if (currentElf == null) {
                    throw new IllegalStateException("Task before elf in file");
                }
                currentElf.addTask(new ElfTask(parts[1]));
                break;
            }
            default:
                break;
            }
        }

        reader.close();
        return new StorageData(children, elves);
    }
}
//@@author

