package seedu.clauscontrol.data.exception;

//@@author shrabasti-c-reused
//Reused from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/data/exception
// /IllegalValueException.java
/**
 * Signals that some given data does not fulfill some constraints.
 */
public class IllegalValueException extends Exception {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public IllegalValueException(String message) {
        super(message);
    }
}
//@@author
