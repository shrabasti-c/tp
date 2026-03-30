package seedu.duke.data.child;

import java.util.ArrayList;
import seedu.duke.data.exception.IllegalValueException;
import seedu.duke.data.gift.Gift;

//Solution below inspired by https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/data
// /person/Person.java
//@@author shrabasti-c
public class Child implements ReadOnlyChild{
    private Name name;
    private ArrayList<Gift> gifts;
    private int age = -1;         // 新增
    private String location = null;

    //@@author GShubhan
    private String listAssignment = null;
    private ArrayList<String> actions = new ArrayList<>();
    private ArrayList<Integer> severities = new ArrayList<>();
    //@@author

    //@@author shrabasti-c
    public Child(Name name) {
        this.name = name;
        this.gifts=new ArrayList<>();
    }
    
    //@@author Kiri
    public Child(Name name, int age, String location) {
        this(name);
        this.age = age;
        this.location = (location != null) ? location.trim() : null;
    }
    
    public int getAge() { return age; }
    public String getLocation() { return location; }
    public boolean hasAge() { return age != -1; }
    public boolean hasLocation() { return location != null && !location.isEmpty(); }
    //@@author
    
    public void addGift(Gift gift){
        gifts.add(gift);
    }
    public ArrayList<Gift> getGifts(){
        return gifts;
    }

    //@@author shrabasti-c
    @Override
    public Name getName() {
        return name;
    }
    //@@author

    //@@author shrabasti-c-reused
    // ChatGPT was used to ideate and generate this mutator method
    public void setName(String newName) throws IllegalValueException {
        this.name = new Name(newName);
    }
    //@@author

    //@@author shrabasti-c
    @Override
    public String toString() {
        return name.toString();
    }
    //@@author

    //@@author GShubhan
    public void addAction(String action, int severity) {
        assert severity >= -5 && severity <= 5 : "Severity out of range";
        assert action != null && !action.isEmpty() : "Action cannot be empty";
        actions.add(action);
        severities.add(severity);
    }

    public int getTotalScore() {
        int total = 0;
        for (int severity : severities) {
            total += severity;
        }
        return total;
    }

    public void setListAssignment(String list) {
        listAssignment = list;
    }
    //@@author

    //@@author GShubhan
    public boolean isNice() {
        if (listAssignment != null) {
            return listAssignment.equals("nice");
        }
        return getTotalScore() >= 0;
    }

    public boolean isNaughty() {
        if (listAssignment != null) {
            return listAssignment.equals("naughty");
        }
        return getTotalScore() < 0;
    }
    //@@author
}
