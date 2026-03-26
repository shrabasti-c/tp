# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Finalize Feature (Shubhan Gabra)

#### Overview
The "finalize" command freezes the nice and naughty lists, preventing further addition of actions and reassignments.
After the finalize command, gift assignment is enabled.

#### Implementation
The finalize feature uses a boolean flag "isFinalized" stored in Duke.java.
This flag is passed to every command via setData() in Command.java.
When the user types "finalize", FinalizeCommand.execute() returns a success message.
Duke then detects it via instanceof FinalizeCommand and sets the flag to true.



## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
