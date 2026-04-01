package seedu.clauscontrol.ui;

import java.io.InputStream;
import java.util.Scanner;

//@@author shrabasti-c-reused
/* Reused from TextUi class of AB2 application
 * Link: https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/ui/TextUi.java
 * with minor modifications
 */
public class TextUi {
    private final Scanner in;

    public TextUi(InputStream in) {
        this.in = new Scanner(in);
    }

    public String getUserCommand() {
        return in.nextLine();
    }
}
//@@author
