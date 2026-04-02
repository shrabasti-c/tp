# Shubhan Gabra - Project Portfolio Page

## Overview
ClausControl is a desktop application for Santa Claus to manage children,
gifts and elves, optimized for use via a Command Line Interface (CLI).

## Summary of Contributions

### Code Contributed
[Link to code dashboard](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2026-02-20T00%3A00%3A00&filteredFileName=&tabOpen=true&tabType=authorship&tabAuthor=GShubhan&tabRepo=AY2526S2-CS2113-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Enhancements Implemented

#### 1. Action Tracking (`action` command)
Allows Santa to record good or bad actions for a child with a severity score
between -5 and 5. The total score determines the child's nice/naughty classification.
* Implemented `ActionCommand`, `addAction()` and `getTotalScore()` in `Child`
* Added input validation and error handling in `Parser`

#### 2. Nice and Naughty Lists (`nice`, `naughty` commands)
Auto-generates the nice and naughty lists based on children's total action scores.
* Implemented `NiceCommand` and `NaughtyCommand`
* Used `isNice()` and `isNaughty()` methods which respect manual overrides

#### 3. Reassign Feature (`reassign` command)
Allows Santa to manually override a child's list assignment.
* Implemented `ReassignCommand` and `setListAssignment()` in `Child`
* Manual override takes priority over score-based classification

#### 4. Finalize Feature (`finalize` command)
Freezes the nice and naughty lists, blocking further action changes and
enabling gift allocation.
* Implemented `FinalizeCommand` and `isFinalized` flag in `ClausControl`
* Used `instanceof` check in `ClausControl` to flip the flag
* Updated `ActionCommand`, `GiftCommand` and `ReassignCommand` to respect the flag

#### 5. Todo and Reminder Feature (`todo`, `todolist`, `removetodo` commands)
Allows Santa to manage a todo list with deadlines. Todos due within 7 days
are shown as reminders on startup.
* Implemented `AddTodoCommand`, `TodoListCommand`, `RemoveTodoCommand`
* Implemented `Todo` data class using Java's `LocalDate`
* Implemented `TodoStorage` for persistent storage across sessions
* Implemented `showUpcomingTodos()` in `ClausControl` for startup reminders

### Contributions to the UG
* Added sections for: `action`, `nice`, `naughty`, `reassign`, `finalize`,
  `todo`, `todolist`, `removetodo`
* Added all commands written by me to the Command Summary table

### Contributions to the DG
* Added implementation details for: Action Tracking, Nice/Naughty Lists,
  Reassign, Finalize and Todo features
* Added all appendices for the DG.
* Added sequence diagram for the Finalize feature
* Added sequence diagram for the Action command


### Contributions to Team-Based Tasks
* Set up team meeting on MS teams
* Reviewed PRs from teammates
* Updated `Command.java` `setData()` to support the `isFinalized` flag
  used by all team members
* Led a few project meetings.
* Contribute to google docs for planning of TP.
* Help draft UG and DG.

### Review/Mentoring Contributions
* Helped teammates resolve Git merge conflicts
* Debugging of conflicts/failing CI of other members.
