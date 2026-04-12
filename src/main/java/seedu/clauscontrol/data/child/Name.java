package seedu.clauscontrol.data.child;
import seedu.clauscontrol.data.exception.IllegalValueException;

//@@author shrabasti-c-reused
/* Reused from Name class of AB2 application
 * Link: https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/data/person/Name.java
 * with minor modifications
 */
public class Name {
    public static final String MESSAGE_NAME_CONSTRAINTS = "A child/elf's name should contain spaces" +
            " or alphabetic characters only";
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alpha} ]+";
    public final String fullName;

    public Name(String name) throws IllegalValueException {
        assert name != null : "Child name should not be null";
        String trimmedName = name.trim();
        if (!isValidName(trimmedName)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.fullName = trimmedName;
    }

    public static boolean isValidName(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullName;
    }
}
//@@author
