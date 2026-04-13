# XIAO Yanjing, Kiri - Project Portfolio Page

## Overview
ClausControl is a desktop application for Santa Claus to manage children,
gifts and elves, optimized for use via a Command Line Interface (CLI).

## Summary of Contributions
### Code Contributed
[Link to code dashboard](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2026-02-20T00%3A00%3A00&filteredFileName=&tabOpen=true&tabType=authorship&tabAuthor=prerana-r11&tabRepo=AY2526S2-CS2113-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented

#### 1. Add elf feature (`elf` command)
Allows Santa to Assign a single gift or multiple gifts at a time to a child.
* Implemented `ElfCommand` to handle elf creation.
* Created `Elf`, `ReadOnlyElf` and `ElfCommand` classes to facilitate the command.

#### 2. Edit elf feature (`editelf` command)
Allows Santa to update the name of an existing elf in the system.
* Implemented `EditElfCommand` which utilizes the `setName` method to modify the Name attribute of the target elf.
* Integrates error handling for `IllegalValueException` to ensure that the new name adheres to the system's naming constraints.

#### 3. Remove feature (`rmelf` command)
Enables the removal of a specific elf from the system based on their current list index.
* Implemented `RmElfCommand` class to locate and delete the target elf from the global `elfList`.
* Includes robust index validation to prevent errors when a user provides an out-of-bounds or invalid index.

#### 4. Assign task to elf (`task` command)
Allows Santa to assign a specific work description to an individual elf.
* Implemented `TaskCommand` which encapsulates the input into an `ElfTask` object and adds it to the target elf’s task list.

#### 5. Delete an elf's task (`detask` command)
Provides the functionality to delete a specific task from a chosen elf's assignment list.
* Implemented `DeTaskCommand` which requires both an elf index and a specific task index for precise removal.

#### 6. Find a child by his/her name/location/age (`find` command)
Allows Santa to search for specific children in the list using name, age, or location.
* Implemented `FindCommand` with a `SearchType` enum to handle different search parameters.
* Provides detailed feedback including the child's index, name, age, and location for all matches found.

#### 7. View elf feature (`elflist` command)
Allows Santa to view a list of all elves currently in the system with their assigned tasks.
* Uses a `StringBuilder` to efficiently format the output, ensuring a clear hierarchical view of elves and their tasks.

#### 8. View child feature (`childlist` command)
Allows Santa to view a complete, numbered list of all children currently stored in the system.
* Implemented `GiftListCommand` class to iterate through the `childList` and generate a formatted string summary.

#### 9. System Reset feature (`reset` command)
Allows Santa to completely wipe all data, clearing all children, elves, todos and tasks.
* Implemented `ResetCommand` to perform a deep clean of both the `childList` and `elfList` simultaneously.

### Contributions to the UG
* Added sections for: `elf`, `rmelf`, `editelf`, `find`,`childlist`, `elflist`, `reset`.
* Create Command Summary table framework.
* Help fix grammar bugs

### Contributions to the DG
* Added implementation details for: `elf`, `rmelf`, `editelf`, `task`, `detask`, `find`,`childlist`, `elflist`, `reset`.
* Added sequence diagram all up features.

### Contributions to Team-Based Tasks
* Add feature in Google Docs for planning tasks.
* Helped in documentation of common sections in the UG and DG.
* Helped in testing of implemented commands and bugs fix.
* Reviewed PRs from teammates
* Implemented a `PendingCommand` safety layer that intercepts all deletion-related actions, requiring explicit confirmation from Santa to prevent accidental data loss.

### Review/Mentoring Contributions
* Helped teammates resolve Git merge conflicts
* Resolved merge conflicts of team members 