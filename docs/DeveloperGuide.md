# Developer Guide

## Acknowledgements

* We, team CS2113-T09-2, acknowledge the use of the following sources in our tP.

| Source                                                                                              | Extent of reuse                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 | 
|:----------------------------------------------------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **[AddressBook-Level2 (AB2)](https://github.com/se-edu/addressbook-level2/)**                       | The class Child was inspired by the Person class of AB2.<br/>The class EditCommandTest was inspired by the AddCommandTest class of AB2. <br/><br/>The class ClausControl was adapted from the Main class of AB2 with some modifications.<br/>The class Command was adapted from the addCommand class of AB2 with some modifications.<br/>The class ChildCommand was adapted from the AddCommand class of AB2 with some modifications.<br/><br/>The class TextUi was reused from the TextUi class of AB2 with minor modifications.<br/>The class IllegalValueException was reused from the IllegalValueException class of AB2 with minor modifications.<br/>The class Name was reused from the Name class of AB2 with minor modifications.<br/>The class ReadOnlyChild was reused from the ReadOnlyPerson class of AB2 with minor modifications. | 
| **[AddressBook-Level3 (AB3)](https://github.com/se-edu/addressbook-level3/)**                       | The docs of our tP (AboutUs, README, UG, DG & PPPs) were made with reference to the AB3 application's docs.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| **[iP (author: shrabasti-c)](https://github.com/shrabasti-c/ip)**                       | The structure of class ChildCommand was adapted from the *Command classes of shrabasti-c's iP with some modifications.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| **[GeeksforGeeks JUnit tests](https://www.geeksforgeeks.org/advance-java/unit-testing-of-system-out-println-with-junit/)**                      | The class ClausControlTest was inspired by the JUnit tests on the aforementioned website.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| **ChatGPT**                                                                                         | The load() function of Storage class was written with the aid of ChatGPT. <br/> The prepareAdd() and prepareEdit() functions of the Parser class (along with their refactored helpers) were reused from ChatGPT with significant modifications. ChatGPT was also used for trivial debugging.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| **Claude**                                                                                          | The tool was used for trivial debugging of ParserTest class after a merge conflict.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             |

## Design & implementation

### Finalize Feature (Shubhan Gabra)

#### Overview
The "finalize" command freezes the nice and naughty lists, preventing further
action changes and enabling gift allocation.

#### Implementation
The finalize feature uses a boolean flag "isFinalized" that is stored in Duke.java.
This flag is passed to every command via setData() in Command.java.
When the user types "finalize", FinalizeCommand.execute() returns a success message.
Duke then detects it via instanceof FinalizeCommand and sets the flag to true.

ActionCommand, GiftCommand and ReassignCommand each check "isFinalized"
at the start of execute(). Once finalized, action additions and reassignments
are blocked since the lists are frozen. Gift allocation is also blocked
before finalization since gifts should only be assigned after the lists are set.

#### Why this way?
The instanceof approach was chosen to keep each command class simple and focused.
Other options like passing a mutable wrapper object or using a static field were
considered but not used as they were less readable or harder to test.

Given below is an example usage scenario:

1. Add a child: child n/Tom  
2. Try adding a gift: gift 1 g/toy: should be blocked  
3. Add an action: action 1 a/helped grandma s/2  
4. Finalize: finalize  
5. Try adding another action: should be blocked  
6. Add a gift: gift 1 g/toy: should work now

Given below is a sequence diagram showing how the finalize command works.

![](diagrams/FinalizeSequenceDiagram.png)

### Action Tracking Feature (Shubhan Gabra)

#### Overview
The "action" command allows Santa to record a good or bad action for a child
with an associated severity score between -5 and 5.

#### Implementation
Each Child object stores two ArrayLists, one for action descriptions and one
for severity scores. getTotalScore() sums all severities to determine if the
child is on the nice (score >= 0) or naughty (score < 0) list.

Format: action CHILD_INDEX a/ACTION s/SEVERITY

The following steps occur:
1. Parser extracts the child index, action description and severity.
2. ActionCommand checks if the lists are finalised. If so, the action is blocked.
3. The child index is validated.
4. The action and severity are added to the child via addAction().

#### Why this way?
Storing actions and severities in parallel ArrayLists keeps the data simple
and makes getTotalScore() a straightforward loop. Alternatives like a single
ArrayList of Action objects were considered but rejected for added complexity.

Given below is a sequence diagram showing how the action command works.

![](diagrams/ActionSequenceDiagram.png)

### Nice and Naughty List Feature (Shubhan Gabra)

#### Overview
The "nice" and "naughty" commands list all children whose total action score
is >= 0 or < 0 respectively. Manual overrides via "reassign" take priority
over the score-based classification.

#### Implementation
Both NiceCommand and NaughtyCommand loop through the childList and call
isNice() or isNaughty() on each child. These methods check the listAssignment
field first. If it is not null, the manual override is used. Otherwise the
total score determines the list.

### Reassign Feature (Shubhan Gabra)

#### Overview
The "reassign" command allows Santa to manually override a child's list
assignment to either nice or naughty, regardless of their action score.
Once finalised, reassignment is blocked.

#### Implementation
ReassignCommand calls setListAssignment() on the child, which sets the
listAssignment field in Child. This field is checked first in isNice()
and isNaughty(), so it always takes priority over the computed score.

Format: reassign CHILD_INDEX l/LIST

### Todo and Reminder Feature (Shubhan Gabra)

#### Overview
The "todo" command allows Santa to add tasks with deadlines. On startup,
any todos due within the next 7 days are automatically shown as reminders.
Todos are persisted across sessions using TodoStorage.

#### Implementation
Each Todo stores a description and a LocalDate deadline. TodoStorage saves
and loads todos from todos.txt using a pipe-separated format.

The following steps occur when adding a todo:
1. Parser extracts the description and deadline from the input.
2. AddTodoCommand validates the description is not empty.
3. AddTodoCommand validates the deadline is not in the past.
4. The todo is added to the todoList and saved via TodoStorage.

On startup, ClausControl calls showUpcomingTodos() which filters todos
using isUpcoming(), showing only those due within 7 days.

Format: todo d/DESCRIPTION by/YYYY-MM-DD

#### Why this way?
LocalDate was chosen over epoch seconds since only date precision is needed.
A separate TodoStorage file was created to avoid modifying the existing
Storage class written by another team member.

**Alternatives considered:**
- Storing todos in the same data.txt file was rejected to avoid coupling
  with the existing storage format.
- Using epoch seconds was rejected as unnecessary since deadlines are
  date-based, not time-based.



### Add Child Feature (Chakraborty Shrabasti)

#### Overview
The `child` command creates a child entity/profile consisting of its name and location.

#### Implementation
The proposed child profile is facilitated by `Child` Class. 
It implements `ReadOnlyChild` which contains a name fetching mechanism, the name being stored internally via a `Name` class with a reference to a `name` String input by the user.
The child operation must minimally have a name argument i.e. location, etc. are optional.
Additionally, it implements the following operations:
* `toAdd()`—adds the child to the internal child list.
* `execute()`—returns a successful operation message.
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


### Add gift feature(Prerana Ravi Shankar)

#### Overview
The gift feature allows Santa to assign one or a list of gifts to a child. The gifts are assigned "in progress" status on assignment.
This allows Santa to manage the gifts assigned to children.

#### Implementation
This feature is implemented with the GiftCommand class.
When Santa enters "gift [child index] g/[gift name]" the Parser extracts the following-
1. The child index
2. The list of gifts prefixed with g/.

A GiftCommand object is created with the above parameters.
The following steps occur-
1. The command checks whether the lists have been finalised.
2. The child index is checked to ensure it is a numeral.
3. The child is retrieved from childList.
4. For each gift, a new Gift object is created and the gift is added to the child with addGift() method.
5. The gift is assigned the "in progress" status upon assignment.

#### UML Diagram- Sequence Diagram
Given below is the sequence diagram
![GiftSequenceDiagram.png](diagrams/GiftSequenceDiagram.png)

**Aspect:** How to implement the Gift feature
  - **Alternative 1 (current choice):** Allowing multiple gifts to be added in one command
    - **Pros:** User friendly as it supports multiple entries at once.
    - **Cons:** Parsing is complex.
  - **Alternative 2:** Allow single gift assignment per command.
    - **Pros:** Implementing this feature is simpler.
    - **Cons:** Not as user-friendly since multiple assignment is not supported.


### Degift feature(Prerana Ravi Shankar)

#### Overview
This feature allows Santa to remove a gift for a particular child. This is useful since it helps Santa update the giftlist.

#### Implementation
This feature is implemented with the DeGiftCommand class.
When Santa enters "degift [child index] [gift index]" the Parser extracts the following-
1. The child index
2. The gift index

A DeGiftCommand object is created with the above parameters.
The following steps occur- 
1. The child index is checked to ensure it is a numeral.
2. The child is retrieved from childList.
3. The command validates the gift index.
4. The gift is removed from the child's gift list.
5. The gift is removed using remove(giftIndex-1)

Appropriate error messages are returned in case a check fails.

#### UML Diagram- Sequence Diagram
Given below is the sequence diagram
![DeGiftSequenceDiagram.png](diagrams/DeGiftSequenceDiagram.png)

**Aspect:** How to implement the DeGift feature
  - **Alternative 1 (current choice):** Remove gift from child list
    - **Pros:** Simple to implement.
    - **Cons:** Requires proper error handling
  - **Alternative 2:** Remove gift by name
    - **Pros:** User friendly as the user need not refer to indexes.
    - **Cons:** Increases complexity of the code.


### Gift delivery status (Prerana Ravi Shankar)

#### Overview
This feature allows Santa to set the gift status as delivered or undelivered. This is useful as it allows Santa to plan the gift deliveries by
updating the delivery status.

#### Implementation
This feature is implemented with the DeliveryStatusCommand class.
When Santa enters "delivery_status [child index] [gift index] d/delivered/undelivered" the Parser extracts the following-
1. The child index
2. The gift index
3. delivery status

A DeliveryStatusCommand object is created with the above parameters.
The following steps occur-
1. The child index is checked to ensure it is a numeral.
2. The child is retrieved from childList.
3. The command validates the gift index.
4. The gift is retrieved from the child's giftlist.
5. The gift status is updated- markDelivered() if delivered=true and markUndelivered() when delivered=false;

Appropriate error messages are returned in case a check fails.

#### UML Diagram- Sequence Diagram
Given below is the sequence diagram
![DeliveryStatusSequenceDiagram.png](diagrams/DeliveryStatusSequenceDiagram.png)

**Aspect:** How to implement the Delivery Status feature
  - **Alternative 1 (current choice):** Use a boolean variable to determine action
    - **Pros:** Simple to implement.
    - **Cons:** Less intuitive as the parameter is not clearly understandable without looking at it's implementation.
  - **Alternative 2:** Using two command objects for delivered and undelivered actions.
    - **Pros:** User friendly.
    - **Cons:** Breaks encapsulation.

### Prepare Gift Feature (Prerana Ravi Shankar)

#### Overview
This feature allows Santa to set a gift status as prepared. This is to indicate that the gift is prepared and not delivered yet.
This allows Santa to track the progress of gifts.

#### Implementation
This feature is implemented with the PrepareGiftCommand class.
When Santa enters "prepared [child index] [gift index] " the Parser extracts the following-
1. The child index
2. The gift index


A PrepareGiftCommand object is created with the above parameters.
The following steps occur-
1. The command retrieves the child from the childlist.
2. The list of gifts are retrieved using the getGifts() method.
3. The gift as per the specified gift index is retrieved.
4. the method markPrepared() is called on the gift.

#### UML Diagram- Sequence Diagram
Given below is the sequence diagram
![PrepareGiftSequencDiagram.png](diagrams/PrepareGiftSequencDiagram.png)

**Aspect:** How to implement the Prepare feature
  - **Alternative 1 (current choice):** Use a separate class for Prepare command
    - **Pros:** Clear separation of responsibilities.
    - **Cons:** Additional class required.
  - **Alternative 2:** Merge the command into deliveryStatus command.
    - **Pros:** Fewer classes.
    - **Cons:** Increases complexity of conditional logic.


### Storage  (Prerana Ravi Shankar)

#### Overview
This component saves the data in the application and reloads it upon running the application.
It stores:
1. Child name
2. Gift name/s
3. Actions and severities
4. Elves and elf tasks

#### Implementation
**Saving data**
The save() method writes the lists into a .txt file in a structured format.
1. Each child's name is written with the CHILD tag.
2. The corresponding gifts of the child are written just below it with the GIFT tag.
3. The child's actions and severities are stores with the ACTION tag.
4. Each elf is written with the ELF tag.
5. The corresponding elf tasks are written under it with the TASK tag.

**Loading data**
The load() method reconstructs data from the .txt file.
1. It reads the file line by line.
2. Splits each line using "|".
3. Processes:
   "CHILD" → creates new Child
   "GIFT" → creates new Gift and restores the status of the gift.
   "ACTION" → restores an action and its severity
   "ELF" → creates a new Elf 
   "TASK" → adds a ElfTask
4. Restores gift state:
   PREPARED → markPrepared()
   DELIVERED → markDelivered()
   default → remains IN_PROGRESS
5. Adds gift to the current child

#### UML Diagram- Sequence Diagram
Given below is the sequence diagram 
![StorageSequenceDiagram.png](diagrams/StorageSequenceDiagram.png)

**Aspect:** How to implement the Storage feature
  - **Alternative 1 (current choice):** Simple .txt file used to store data
    - **Pros:** Human-readable file.
    - **Cons:** Less structures.
  - **Alternative 2:** JSON format
    - **Pros:** Structured.
    - **Cons:** Requires external libraries.


### GiftList Feature  (Prerana Ravi Shankar)

#### Overview
The giftlist feature displays all the gifts assigned to a child along with the child name. This allows Santa to view all the gifts in a structured format.

#### Implementation
This feature is implemented with the GiftListCommand class.
When Santa enters "giftlist " the Parser creates a GiftListCommand object.
*Only children with assigned gifts are displayed in the giftlist.*

1. The command checks if childList is empty.
2. It iterates through each child in the childList.
3. For each child, the list of gifts are retrieved using the getGifts() method.
4. The child's name and index are appended to the output.
5. The createList() method iterates through each gift and appends the gift index and gift details to the output.
6. If no gifts are assigned to any child, an appropriate error message is displayed.

#### UML Diagram- Sequence Diagram
Given below is the sequence diagram.
![GiftListSequenceDiagram.png](diagrams/GiftListSequenceDiagram.png)

**Aspect:** How to implement the GiftList feature
- **Alternative 1 (current choice):** Iterate through childlist and display children with assigned gifts.
    - **Pros:** Output is relevant and shows useful information.
    - **Cons:** Children without gifts are not displayed.
- **Alternative 2:** Display all children regardless or whether gifts are assigned or not.
    - **Pros:** Provides a complete overview of all children details.
    - **Cons:** Output may look messy.


## Documentation, logging, testing, configuration, dev-ops

### Setting up and maintaining the project website
This project uses Jekyll to manage documentation.

- All documentation files are under the docs/ folder.
- Markdown(.md) is used for writing documentation.
- The structure and navigation of the site are controlled with Jekyll.

To set up and maintain the project website, refer to:
- se-edu/guides: Using Jekyll for project documentation

### Project specific notes
When adapting documentation for ClausControl:
- Update all references to match Children, Gifts, Elves, Todos, Commanss
- Ensure command formats match logic in the parser.
- Update configuration files if required.

If using IntelliJ, enable soft wrapping for `.md` files.

### Style guidance
- Follow the Google Developer Documentation Style Guide
- Follow Markdown standards from:
    - se-edu/guides: Markdown coding standard

### Diagrams
Use PlantUML for diagrams such as:
- Command flows
- Class diagrams
- Sequence diagrams
Guide:
- se-edu/guides: Using PlantUML

### Converting documentation to PDF
Use browser print → Save as PDF.

## Testing Guide

### Running tests

#### Method 1: IntelliJ

- Right-click `src/test/java`
- Select **Run 'All Tests'**

You can also run:
- Individual test classes
- Specific test methods

#### Method 2: Gradle
./gradlew clean test

### Types of tests

#### 1. Unit Tests
These tests focus on internal logic and data classes.
Examples of behaviors tested:
1. Gift state transitions.
2. Todo deadline transitions.

#### 2. Command Tests
These tests validate command parsing and execution logic.
Some examples include:
1. ActionCommand→ updates child's behavior scores.
2. ViewCommand→ displays collected child information.

#### 3. Parser Tests
These tests validate whether the user input is correctly interpreted into commands.
They ensure that the correct command is created, parameters are parsed accurately and errors are thrown.
Examples:
1. Handling flexible input order (e.g. `child n/Name a/10 l/City`)

#### 4. System-Level Tests
These tests simulate real application execution.

## Logging Guide

### Logging Framework
ClausControl uses java's built in logging framework.

## Dev-Ops Guide

### Build Automation
ClausControl used **Gradle** for build automation.

### Common Gradle Commands
./gradlew clean → Removes build files
./gradlew test  → Runs all tests


















## Product scope
### Target user profile
## Appendix A: Product Scope

### Target user profile
Santa Claus managing a large number of children, gifts and elves

### Value proposition
Manage children, gifts and elves with automated nice/naughty classification, gift tracking, elf task management
and a todo reminder system.

## Appendix B: User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a ... | I want to ... | So that I can ... |
|----------|----------|---------------|-------------------|
| `* * *`  | Santa | add a child with name, age and location | track children in the system |
| `* * *`  | Santa | edit a child's details | correct mistakes |
| `* * *`  | Santa | delete a child | remove entries no longer needed |
| `* * *`  | Santa | view a child's full profile | see all details at once |
| `* * *`  | Santa | list all children | get an overview of all children |
| `* * *`  | Santa | find a child by name, age or location | locate a child quickly |
| `* * *`  | Santa | record good or bad actions for a child | track child behaviour |
| `* * *`  | Santa | view the nice list | see which children deserve gifts |
| `* * *`  | Santa | view the naughty list | see which children do not deserve gifts |
| `* * *`  | Santa | manually reassign a child to nice or naughty | override the automatic classification |
| `* * *`  | Santa | finalize the lists | lock in decisions and start allocating gifts |
| `* * *`  | Santa | assign gifts to a child | plan gift delivery |
| `* * *`  | Santa | remove a gift from a child | correct mistakes in gift allocation |
| `* * *`  | Santa | mark a gift as prepared | track gift preparation progress |
| `* * *`  | Santa | mark a gift as delivered or undelivered | track gift delivery status |
| `* * *`  | Santa | view all gifts assigned to children | keep track of gift allocation |
| `* * *`  | Santa | add elves | manage my workforce |
| `* * *`  | Santa | remove an elf | remove elves no longer needed |
| `* * *`  | Santa | edit an elf's name | correct mistakes |
| `* * *`  | Santa | assign tasks to elves | delegate work |
| `* * *`  | Santa | remove a task from an elf | update elf workload |
| `* * *`  | Santa | list all elves and their tasks | get an overview of my workforce |
| `* * *`  | Santa | add todos with deadlines | keep track of things to do |
| `* * *`  | Santa | view all todos | see everything on my list |
| `* * *`  | Santa | remove a todo | keep my list clean |
| `* * *`  | Santa | see upcoming reminders on startup | not miss important deadlines |
| `* * *`  | Santa | reset the entire system | start fresh |
| `* * *`  | Santa | data persists across sessions | not lose my work after restarting |

## Appendix C: Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 17 or above installed.
2. Should be able to hold up to 1000 children without noticeable sluggishness.
3. A user with above average typing speed should be able to accomplish most
   tasks faster using commands than using a mouse.
4. Data should persist across sessions without any manual saving by the user.
5. The system should handle invalid inputs gracefully without crashing.
6. Todo reminders should reflect the correct upcoming deadlines based on
   the current date at startup.

## Appendix D: Glossary

* **Child**: A system entity representing a gift recipient tracked by Santa.
* **Elf**: A system entity representing a staff member or volunteer.
* **CLI**: Command Line Interface — an interface where users type commands.
* **Nice list**: The list of children with a total action score >= 0.
* **Naughty list**: The list of children with a total action score < 0.
* **Severity**: An integer between -5 and 5 representing how good or bad an action is.
* **Finalize**: The act of locking the nice/naughty lists to enable gift allocation.
* **Index**: A unique numerical identifier assigned to an item in the displayed list.
* **Todo**: A task with a deadline added to Santa's reminder system.
* **Mainstream OS**: Windows, Linux, Unix, MacOS.
* **Gift state**: The current status of a gift — In Progress, Prepared or Delivered.
* **Task**: A piece of work assigned to an elf.

## Appendix E: Instructions for Manual Testing

Given below are instructions to test the app manually.

### Launch and shutdown
1. Download the jar file and copy into an empty folder.
2. Run `java -jar tp.jar`.
3. Expected: Shows the ClausControl logo and welcome message.
4. Type `bye` to exit.

### Testing child commands
1. Add a child: `child n/Tom`
   Expected: "Ho ho ho! New child added: Tom"
2. Add with all details: `child n/Lucy l/Singapore a/10`
   Expected: Child added with all details.
3. View child: `view 1`
   Expected: Tom's full profile shown.
4. Edit child: `edit 1 n/Tommy`
   Expected: Name updated to Tommy.
5. Find by name: `find n/Tom`
   Expected: Tom shown with index and details.
6. Find by location: `find l/Singapore`
   Expected: Lucy shown.
7. Find by age: `find a/10`
   Expected: Lucy shown.
8. List all: `childlist`
   Expected: All children listed.
9. Delete child: `delete 1` then `confirm`
   Expected: Child removed.
10. Invalid name: `child n/123`
    Expected: Error message about name constraints.

### Testing action commands
1. Add a good action: `action 1 a/helped grandma s/2`
   Expected: Action recorded with severity 2.
2. Add a bad action: `action 1 a/broke window s/-3`
   Expected: Action recorded with severity -3.
3. Invalid severity: `action 1 a/test s/10`
   Expected: Error - severity must be between -5 and 5.
4. View nice list: `nice`
   Expected: Shows children with total score >= 0.
5. View naughty list: `naughty`
   Expected: Shows children with total score < 0.
6. Reassign: `reassign 1 l/nice`
   Expected: Child moved to nice list regardless of score.

### Testing finalize and gift commands
1. Try adding a gift before finalize: `gift 1 g/toy`
   Expected: Blocked with message to finalize first.
2. Finalize: `finalize`
   Expected: Lists frozen message shown.
3. Try adding action after finalize: `action 1 a/test s/1`
   Expected: Blocked with message.
4. Add gift after finalize: `gift 1 g/toy`
   Expected: Gift added successfully.
5. Mark gift as prepared: `prepared 1 1`
   Expected: Gift status updated to Prepared.
6. Mark gift as delivered: `delivery_status 1 1 d/delivered`
   Expected: Gift status updated to Delivered.
7. View gift list: `giftlist`
   Expected: All gifts shown with their status.
8. Remove gift: `degift 1 1` then `confirm`
   Expected: Gift removed.

### Testing elf commands
1. Add elf: `elf n/Buddy`
   Expected: "Ho ho ho! New elf added: Buddy"
2. Assign task: `task 1 t/wrap gifts`
   Expected: Task assigned to Buddy.
3. List elves: `elflist`
   Expected: Buddy shown with task.
4. Edit elf: `editelf e/1 n/Dobby`
   Expected: Elf name updated.
5. Remove task: `detask e/1 t/1` then `confirm`
   Expected: Task removed.
6. Remove elf: `rmelf e/1` then `confirm`
   Expected: Elf removed.

### Testing todo commands
1. Add a todo: `todo d/Buy wrapping paper by/2026-12-20`
   Expected: Todo added successfully.
2. Past date: `todo d/Old task by/2020-01-01`
   Expected: Error - deadline cannot be in the past.
3. Invalid date: `todo d/Test by/2026-13-78`
   Expected: Error - invalid date format.
4. View todos: `todolist`
   Expected: All todos listed.
5. Remove todo: `removetodo 1`
   Expected: Todo removed.
6. Restart the app with a todo due within 7 days.
   Expected: Reminder shown on startup automatically.

### Testing reset command
1. Add some children and elves.
2. Type `reset` then `confirm`.
   Expected: All children and elves cleared.

### Saving data
1. Add some children and todos, then type `bye` to exit.
2. Relaunch the app.
3. Expected: Children and todos are restored.
