# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

### Finalize Feature (Shubhan Gabra)

#### Overview
The "finalize" command freezes the nice and naughty lists, preventing further addition of actions and reassignments.
After the finalize command, gift assignment is enabled.

#### Implementation
The finalize feature uses a boolean flag "isFinalized" stored in Duke.java.
This flag is passed to every command via setData() in Command.java.
When the user types "finalize", FinalizeCommand.execute() returns a success message.
Duke then detects it via instanceof FinalizeCommand and sets the flag to true.

### Add Child Feature (Chakraborty Shrabasti)

#### Overview
The `child` command creates a child entity/profile consisting of its name and location.

#### Implementation
The proposed child profile is facilitated by `Child` Class. 
It implements `ReadOnlyChild` which contains a name fetching mechanism, the name being stored internally via a `Name` class with a reference to a `name` String input by the user.
The child operation must minimally have a name argument i.e. location, etc. are optional.
Additionally, it implements the following operations:
* `toAdd()`— adds the child to the internal child list.
* `execute()`— returns a successfull operation message.
These operations comprise the `ChildCommand` class (which inherits from a base `Command` class).
Given below is an example usage scenario and how the add child mechanism behaves at each step.
1. The user launches the application for the first time.
2. The user executes `child n/Bruce Wayne` to add a child in the child list.
3. The Parser parses the command and returns the arguments to a new `ChildCommand`.
4. Given a valid Name the `Child` object is instantiated and returned.
* Note: At the point of instantiation the Name is validated. An incorrect input means the object creation does not proceed.
5. The `Child` is added to the Child List.
6. The successful message is displayed.

Given below is a sequence diagram describing the child operation (happy path).
![](diagrams/ChildSequenceDiagram.png)

**Aspect:** How to implement the Child Profile  
  - **Alternative 1 (current choice):** Construct a `ReadOnlyChild` interface which implements `Child`  
    - **Pros:** Ensures no external access as well as immutability  
    - **Cons:** More lines of code and more complex implementation (extra interface)  

  - **Alternative 2:** Implement via a Single `Child` Class  
    - **Pros:** Lesser lines of code and simpler implementation  
    - **Cons:** Higher risk of child data modification  

 **Aspect:** How to store the Child Name  
  - **Alternative 1 (current choice):** Store as `Name` class  
    - **Pros:** Ensures validation at time of object creation  
    - **Cons:** More lines of code and more complex implementation (extra class)  

  - **Alternative 2:** Store as `String` in `Child` class  
    - **Pros:** Lesser lines of code and simpler implementation  
    - **Cons:** No enforced validation and violation of Separation of Concerns  

### Add Elf Feature (XIAO Yanjing)

#### Overview
The "elf" command creates an elf entity consisting of its name and task(initialized as null).

#### Implementation
The proposed elf profile is facilitated by Elf Class.
It implements ReadOnlyElf with a name fetching mechanism, stored internally with a Name class with a reference to a name String input by the user.
Given below is an example usage scenario and how the add elf mechanism behaves at each step.
Step 1. The user launches the application for the first time.
Step 2. The user executes elf n/Buddy to add an elf in the elf list.

## Product scope
### Target user profile

Santa Claus [single user]

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|------|--------------|------------------|
|v1.0| Santa|Assign gifts to all children|I can spread christmas cheer|
|v1.0| Santa|Edit gifts - add/remove|I can correct my list if i make mistakes|
|v1.0| Santa|Mark/unmark gifts as delivered/not delivered|I can keep track of my delivery list|
|v1.0| Santa|See list of gifts|I can allocate the budget appropriately|
|v2.0| user |find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements


## Glossary

* **Elf**: A system entity representing a staff member or volunteer.
* **Child**: A system entity representing a recipient or target user.
* **CLI**: Command Line Interface; an interface where users type commands to interact with the software.
* **Index**: A unique numerical identifier assigned to an item in the current displayed list.

## Instructions for manual testing

#### Initial Launch
1. Download the `.jar` file and place it in an empty folder.
2. Run `java -jar duke.jar`. The GUI should load with a sample message.

#### Testing Elf Commands
1. **Adding:** Type `elf n/Buddy`. Verify "Buddy" appears in the `elflist`.
2. **Editing:** Type `Editelf e/1 n/Dobby`. Verify the first Elf's name changes to "Dobby".
3. **Deleting:** Type `rmelf e/1`. Verify the Elf is removed.

#### Testing Find Functionality
1. Ensure you have children data loaded (use `childlist` to check).
2. Type `find l/Singapore`.
3. **Expected Result:** Only children with the location "Singapore" are displayed in the list.
