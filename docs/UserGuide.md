# User Guide

## Introduction

**ElfManager** is a specialized CLI tool designed for efficient tracking and organization of "Elves" and "Children" data. Built for speed and reliability, it allows users to manage complex personnel assignments and location-based data through a clean command-line interface.

## Quick Start

1. Ensure that you have **Java 17** or above installed on your computer.
2. Download the latest version of `Duke` from [here](http://link.to/duke).
3. Open your terminal, navigate to the folder where the file is located, and run the command: `java -jar duke.jar`.

## Features

### Elf Management

#### Adding an Elf: `elf`
Adds a new Elf to the system records.
* Format: `elf n/NAME`
* Example: `elf n/Buddy`

#### Removing an Elf: `rmelf`
Removes an existing Elf based on their index in the list.
* Format: `rmelf e/ELF_INDEX`
* Example: `rmelf e/1`

#### Editing an Elf: `Editelf`
Updates the name of an existing Elf identified by their index.
* Format: `Editelf e/ELF_INDEX n/ELF_NEW_NAME`
* Example: `Editelf e/1 n/Legolas`

### Listing Entities

#### Listing Children: `childlist`
Displays a complete list of all children currently in the database.

#### Listing Elves: `elflist`
Displays a complete list of all elves currently in the database.

### Finding Children

#### Finding by Name: `find n/`
Searches for children matching the specified name.
* Format: `find n/NAME`
* Example: `find n/James Jake`

#### Finding by Age: `find a/`
Searches for children who match a specific age.
* Format: `find a/AGE`
* Example: `find a/11`

#### Finding by Location: `find l/`
Searches for children based on their registered location.
* Format: `find l/LOCATION`
* Example: `find l/Singapore`

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: All your data is automatically saved in a local `data` folder within the same directory as the `.jar` file. Simply copy the entire folder and the `.jar` file to the new computer to resume where you left off.

## Command Summary

| Action | Format | Example |
| :--- | :--- | :--- |
| **Add Elf** | `elf n/NAME` | `elf n/Dobby` |
| **Remove Elf** | `rmelf e/INDEX` | `rmelf e/1` |
| **Edit Elf** | `Editelf e/INDEX n/NEW_NAME` | `Editelf e/2 n/Zobby` |
| **List Children** | `childlist` | `childlist` |
| **List Elves** | `elflist` | `elflist` |
| **Find by Name** | `find n/NAME` | `find n/James Jake` |
| **Find by Age** | `find a/AGE` | `find a/11` |
| **Find by Location** | `find l/LOCATION` | `find l/Singapore` |

* Add todo `todo n/TODO_NAME d/DEADLINE`
