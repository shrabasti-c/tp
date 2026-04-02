# Chakraborty Shrabasti - Project Portfolio Page

## Overview
ClausControl is a desktop application for Santa Claus to manage children,
gifts and elves, optimized for use via a Command Line Interface (CLI).

### Summary of Contributions
### Code Contributed
[Link to code dashboard](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2026-02-20T00%3A00%3A00&filteredFileName=&tabOpen=true&tabType=authorship&tabAuthor=shrabasti-c&tabRepo=AY2526S2-CS2113-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented

#### 1. Add child feature (`child` command)
Allows Santa to create a child profile consisting of minimally name along with age and location.
* Implemented `child` command to handle child profile creation.
* Added input validation and error handling in `Parser` and `Name`.
* Created `Child`, `ReadOnlyChild` and `ChildCommand` classes to facilitate the command.
* Added comprehensive JUnit testing for this feature

#### 2. Edit child feature (`edit` command)
Allows Santa to edit a child profile's details (name, age, location).
* Implemented `edit` command to edit child profile details.
* Added input validation and error handling in `Parser`. 
* Created `EditCommand` class to facilitate the command.
* Added comprehensive JUnit testing for this feature.

#### 3. View Child Feature (`view` command)
Allows Santa to view a child profile comprising all details associated with it.
* Implemented `view` command to view child profile details.
* Added input validation and error handling in `Parser`. 
* Created `ViewCommand` class to facilitate the command.

#### 3. Delete Child Feature (`view` command)
Allows Santa to delete a child profile along with all details associated with it.
* Implemented `delete` command to delete child profile details.
* Created `DeleteCommand` class to facilitate the command.

#### 5. Edit Todo feature (`edittodo` command)
Allows Santa to edit a task in his todolist.
* Implemented `edittodo` command to edit a todolist task.
* Added input validation and error handling in `Parser`.
* Created `EditTodoCommand` class to facilitate the command with added logging.

#### 6. Storage Feature (childlist)
Allows Santa to access saved data(childlist,giftlist and elflist) across all sessions.
* Contribution to `Storage` class - implemented storage capability for child ages and locations.

### Contributions to the UG
* Added sections for: `child`, `edit`, `view`, `delete`, `edittodo`.
* Added all commands to the Command Summary table.
* Formatted initial UG Canvas Submission and submitted it.

### Contributions to the DG
* Added implementation details for: child feature and other common features.
* Added sequence diagram for all above features.

### Contributions to Team-Based Tasks
* Set up GitHub repository (current maintainer).
* Led project meetings.
* Set up channel for communication.
* Reviewed and approved PRs from teammates.
* Configured majority of AboutUs deliverable.
* Set up milestones in GitHub (v1.0 and v2.0) with dates.
* Set up initial repo structure for this project i.e. packages like command, data, and the main class to be used as boilerplate for the entire team.
* Set up initial Parser, Command and Claus Control classes (common classes, and ClausControl with additional Logging) with foundational boilerplate used by entire team.
* Set up TextUi and other output formatting for use of entire team.

### Review/Mentoring Contributions
* Resolved majority of Git merge conflicts of team. 
* Debugging of conflicts/failing CI of other members.
* Provided helpful advice regarding use of PlantUML.