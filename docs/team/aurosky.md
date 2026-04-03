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
* Added input validation and error handling in `Parser`
* Created `Elf`, `ReadOnlyElf` and `ElfCommand` classes to facilitate the command.

#### 2. Edit elf feature (`editelf` command)
Allows Santa to update the name of an existing elf in the system.
* Implemented `EditElfCommand` which utilizes the `setName` method to modify the Name attribute of the target elf.
* Integrates error handling for `IllegalValueException` to ensure that the new name adheres to the system's naming constraints.
* Provides a "before and after" success message to confirm the name change from the old value to the new one.

#### 3. Remove feature (`rmelf` command)
Enables the removal of a specific elf from the system based on their current list index.
* Implemented `RmElfCommand` class to locate and delete the target elf from the global `elfList`.
* Includes robust index validation to prevent errors when a user provides an out-of-bounds or invalid index.
* Provides immediate feedback by returning the name of the removed elf and logging the operation for system tracking.

#### 4. Assign task to elf (`task` command)
Allows Santa to assign a specific work description to an individual elf.
* Implemented `TaskCommand` which encapsulates the input into an `ElfTask` object and adds it to the target elf’s task list.
* Features strict assertions and checks to ensure that both the task content is non-empty and the elf index is valid.
* Facilitates granular management of elf productivity by allowing real-time updates to their workload.

#### 5. Delete an elf's task (`detask` command)
Provides the functionality to delete a specific task from a chosen elf's assignment list.
* Implemented `DeTaskCommand` which requires both an elf index and a specific task index for precise removal.
* Includes safety checks to verify if the elf actually has tasks and if the provided task index exists within that list.
* Ensures that the task description is captured before deletion so the user receives a confirmation of exactly what was removed

#### 6. Find a child by his/her name/location/age (`find` command)
Allows Santa to search for specific children in the list using various criteria such as name, age, or location.
* Implemented `FindCommand` with a `SearchType` enum to handle different search parameters.
* Features case-insensitive querying and partial matching for names and locations to improve search flexibility.
* Provides detailed feedback including the child's index, name, age, and location for all matches found.

#### 7. View elf feature (`elflist` command)
Allows Santa to view a comprehensive list of all elves currently in the system along with their assigned tasks.
* Implemented via the `ElfListCommand` class, which iterates through the `elfList` to build a detailed string representation.
* Includes logic to handle empty lists and distinguishes between elves with no tasks versus those with active assignments.
* Uses a `StringBuilder` to efficiently format the output, ensuring a clear hierarchical view of elves and their corresponding tasks.

#### 8. View child feature (`childlist` command)
Allows Santa to view a complete, numbered list of all children currently stored in the system.
* Implemented `GiftListCommand` class to iterate through the `childList` and generate a formatted string summary.
* Includes validation to return a specific message when the list is empty, preventing confusing blank outputs.
* Serves as a quick reference for Santa to see the overall status of all registered children.

#### 9. System Reset feature (`reset` command)
Allows Santa to completely wipe all data and start fresh, clearing all children, elves, and tasks in one go.
* Implemented `ResetCommand` to perform a deep clean of both the `childList` and `elfList` simultaneously.
* Added logic to reset the system's finalization status, giving Santa a blank slate for the next holiday season.
* Integrated comprehensive logging to track the full system purge and ensure a successful reversion to a clean state.

### Contributions to the UG
* Added sections for: `elf`, `rmelf`, `editelf`, `find`,`childlist`, `elflist`, `reset`.
* Create Command Summary table framework.

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