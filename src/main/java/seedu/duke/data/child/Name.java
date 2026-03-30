package seedu.duke.data.child;
import seedu.duke.data.exception.IllegalValueException;

//@@author shrabasti-c-reused
//Reused from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/data/person/Name.java
//with minor modifications
public class Name {
    public static final String MESSAGE_NAME_CONSTRAINTS = "A child's name should contain spaces" +
            " or alphabetic characters only";
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alpha} ]+";
    public final String fullName;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public Name(String name) throws IllegalValueException {
        assert name != null : "Child name should not be null";
        String trimmedName = name.trim();
        if (!isValidName(trimmedName)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.fullName = trimmedName;
    }

    /**
     * Returns true if the given string is a valid person name.
     */
    public static boolean isValidName(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullName;
    }
}
//@@author
