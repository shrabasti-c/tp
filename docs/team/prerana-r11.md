# Prerana Ravi Shankar - Project Portfolio Page

## Overview
ClausControl is a desktop application for Santa Claus to manage children,
gifts and elves, optimized for use via a Command Line Interface (CLI).

## Summary of Contributions

### Code Contributed
[Link to code dashboard](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2026-02-20T00%3A00%3A00&filteredFileName=&tabOpen=true&tabType=authorship&tabAuthor=prerana-r11&tabRepo=AY2526S2-CS2113-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented

#### 1. Add gift feature (`gift` command)
Allows Santa to Assign a single gift or multiple gifts at a time to a child.
* Implemented `Giftcommand` to handle gift assignment.
* Added input validation and error handling in `Parser`
* Handles possible errors.


#### 2. Remove gift feature (`degift` command)
Allows Santa to remove a gift from the giftlist of a child.
* Implemented `DegiftCommand`.
* Gifts that are delivered cannot be removed.
* Both child and gift index are validated before removal.
* Handles possible errors.

#### 3. Delivery Status Feature (`delivery_status` command)
Allows Santa to set a gift as delivered or undelivered.
* Implemented `DeliveryStatusCommand`.
* Supports both d/delivered and d/undelivered status.
* Prevents redundant assignment for gifts already in desired status.
* Both child and gift index are validated.

#### 4. Prepare gift Feature (`prepared` command)
Allows Santa to mark a gift which is not delivered yet as prepared.
* Implemented `PrepareGiftCommand`.
* Gifts that are already delivered cannot be prepared.
* Both child and gift index are validated.
* Handles possible errors.

#### 5. View gifts feature (`giftlist` command)
Allows Santa to view all the gifts assigned to children.
* Implemented `GiftListCommand`.
* Returns error messages when child or gift index does not exist.
* Handles possible errors.

#### 6. Storage Feature (childlist,giftlist and elf)
Allows Santa to access saved data(childlist,giftlist and elflist) across all sessions.
* Implemented `Storage` class to handle saving and loading of data.
* Stored data in format(CHILD,GIFT,ELF,TASK).
* Implemented the StorageData class to return both child and elf lists in a single load operation.
* Integrated storage with ClausControl to load data on starting the application and save data after each user command.

### Contributions to the UG
* Added sections for: `gift`, `delivery_status`, `giftlist`, `prepared`, `degift`
* Added all commands to the Command Summary table

### Contributions to the DG
* Added implementation details for: add gift, remove gift, delivery_status, giftlist,storage and prepare gift features.
* Added sequence diagram for all features.
* Added documentation and UML diagrams for Storage and Parser components in Design section.

### Contributions to Team-Based Tasks
* Set up Google Docs for planning tasks.
* Helped in documentation of common sections in the UG and DG.
* Released JAR files on GitHub.
* Helped in testing of implemented commands and reported bugs to fix.
* Reviewed PRs from teammates


### Review/Mentoring Contributions
* Helped teammates resolve Git merge conflicts
* Resolved merge conflicts of team members 