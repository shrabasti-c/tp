# Chakraborty Shrabasti's Project Portfolio Page

## Overview
ClausControl is a desktop application for Santa Claus to manage children, gifts, elves and tasks, optimized for use via a Command Line Interface (CLI).
It is written in Java, and has about 8200 LoC.

Given below are my contributions to the project.


* **Code contributed**: [RepoSense link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2026-02-20T00%3A00%3A00&filteredFileName=&tabOpen=true&tabType=authorship&tabAuthor=shrabasti-c&tabRepo=AY2526S2-CS2113-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Child Feature**: Added the ability to create a child profile consisting of minimally name along with age and location.
  * Justification: This command is integral and the fundamental building block of the application. The implementation had to be exhaustive to ensure no erroneous child profile could be built. The implementation mechanism was later used by other team members to implement their entities (ex: Elf Class reused Child Class Code).
  * Contribution: Implemented `child` command to handle child profile creation. Added input validation and error handling in `Parser` and `Name`. Created `Child`, `ReadOnlyChild` and `ChildCommand` classes to facilitate the command. Comprehensively tested (JUnit and manual) the feature.
  * Credits: Code reused from AddressBook-Level2, shrabasti-c's iP and ChatGPT [refer DG and relevant parts of code for details and extent]


* **Edit Feature**: Added the ability to edit a child profile's details (name, age, location).
    * Contribution:  Implemented `edit` command to edit child profile details. Added input validation and error handling in `Parser`. Created `EditCommand` class to facilitate the command. Comprehensively tested (JUnit and manual) the feature.
    * Credits: Code reused from AddressBook-Level2 [refer DG and relevant parts of code for details and extent]


* **View Child Feature**: Added the ability to view a child profile comprising all details associated with it.
    * Contribution:  Implemented `view` command to view child profile details. Added input validation and error handling. Created `ViewCommand` class to facilitate the command. Comprehensively tested (JUnit and manual) the feature.


* **Delete Child Feature**: Added the ability to delete a child profile along with all details associated with it.
    * Contribution:  Implemented `delete` command to delete child profile details. Added input validation and error handling. Created `DeleteCommand` class to facilitate the command. Comprehensively tested (JUnit and manual) the feature.


* **Edit Todo Feature**: Added the ability to edit a task in Santa's todolist.
    * Contribution:  Implemented `edittodo` command to edit a todolist task. Added input validation and error handling in `Parser`. Created `EditTodoCommand` class to facilitate the command with added logging. Comprehensively tested (JUnit and manual) the feature.


* **Storage Feature**[Enhancement to Existing Feature]: Implemented storage capability for child ages and locations in `Storage` class.


* **Documentation**:
    * User Guide:
        * Added sections for: `child`, `edit`, `view`, `delete`, `edittodo`, `bye`.
        * Added all above commands to the Command Summary table.
        * Formatted initial UG Canvas Submission and submitted it [Estimated 2 hours extra contribution w.r.t. team].
        * Formatted final UG submission and added common sections: Title, Table of Contents, Features (with Notes) for v1.0 release.
        * Carried out basic formatting of whole team's parts [A significant endeavor - Estimated 2 hours extra contribution w.r.t. team] for all releases (v1.0, v2.0, v2.0.1, v2.1) of User Guide.
    
    * Developer Guide: The following sections of the DG and their corresponding diagrams were written by shrabasti-c.
        * Acknowledgements
        * Architecture [under Heading **Design**] [+2 diagrams in the section - 1 architecture, 1 sequence]
        * Data Component [+1 class diagram in the section]
        * Child Feature [+1 sequence diagram in the section]


* **Contributions to Team-Based Tasks**:
    * Set up GitHub repository (current maintainer).
    * Led project meetings.
    * Set up channel for communication.
    * Reviewed and approved PRs from teammates.
    * Configured majority of AboutUs deliverable.
    * Set up milestones in GitHub (v1.0, v2.0, etc.) with dates.
    * Set up initial directory structure and base common classes for this project i.e. 
        * Justification: This formed the baseline skeleton following which everyone could work on their features independently while maintaining separation of responsibilities.
        * Contribution: 
          * Refactored packages like command, data, and the main class to be used as boilerplate for the entire team. 
          * Set up initial Parser, Command and Claus Control classes (common classes, and ClausControl with additional Logging) with foundational boilerplate used by entire team. 
          * Set up TextUi and other output formatting for use of entire team.
        * Credits: Code reused from AddressBook-Level2 and shrabasti-c's iP. [refer DG and relevant parts of code for details and extent]


* **Review/Mentoring Contributions**:
    * Resolved majority of Git merge conflicts of team. 
    * Debugging of conflicts/failing CI of other members.
    * Debugging of other team's tP during PE-D [Bugs found: 10, above the PE average].
    * Provided helpful advice regarding use of PlantUML to team.