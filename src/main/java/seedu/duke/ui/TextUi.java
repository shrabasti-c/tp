//@@author shrabasti-c-reused
//Reused from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/ui/TextUi.java
//with minor modifications

package seedu.duke.ui;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class TextUi {
    private final Scanner in;

    public TextUi() {
        this(System.in);
    }

    public TextUi(InputStream in) {
        this.in = new Scanner(in);
    }

    /**
     * Reads the text entered by the user.
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        return in.nextLine();
    }
}
//@@author
