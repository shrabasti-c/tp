//@@author GShubhan
package seedu.clauscontrol.commands;

/**
 * Displays a summary of all available commands and their formats.
 */
public class HelpCommand extends Command {

    private static final String HELP_TEXT =
            "Here are all available commands:\n\n"
                    + "--- Child Management ---\n"
                    + "  child n/NAME [l/LOCATION] [a/AGE]                         Add a child\n"
                    + "  edit INDEX [n/NAME] [l/LOCATION] [a/AGE]                  Edit a child\n"
                    + "  view INDEX                                                 View a child's profile\n"
                    + "  delete INDEX                                               Delete a child (requires confirm)\n"
                    + "  childlist                                                  List all children\n"
                    + "  find n/NAME | a/AGE | l/LOCATION                          Search for children\n\n"
                    + "--- Actions & Lists ---\n"
                    + "  action INDEX a/ACTION s/SEVERITY                          Record an action (severity: -5 to 5)\n"
                    + "  nice                                                       View the nice list\n"
                    + "  naughty                                                    View the naughty list\n"
                    + "  reassign INDEX l/nice|naughty                              Manually reassign a child\n"
                    + "  finalize                                                   Freeze lists and enable gifts\n"
                    + "  unfinalize                                                 Unfreeze the lists\n\n"
                    + "--- Gift Management ---\n"
                    + "  gift INDEX g/GIFT [g/GIFT ...]                            Assign gifts to a child\n"
                    + "  degift INDEX GIFT_INDEX                                   Remove a gift (requires confirm)\n"
                    + "  prepared INDEX GIFT_INDEX                                 Mark a gift as prepared\n"
                    + "  delivery_status INDEX GIFT_INDEX d/delivered|undelivered  Update delivery status\n"
                    + "  giftlist                                                   View all gifts\n\n"
                    + "--- Elf Management ---\n"
                    + "  elf n/NAME                                                 Add an elf\n"
                    + "  rmelf e/INDEX                                              Remove an elf (requires confirm)\n"
                    + "  editelf e/INDEX n/NAME                                    Rename an elf\n"
                    + "  task INDEX t/DESCRIPTION                                  Assign a task to an elf\n"
                    + "  detask e/INDEX t/TASK_INDEX                               Remove a task (requires confirm)\n"
                    + "  elflist                                                    List all elves and tasks\n\n"
                    + "--- Todo List ---\n"
                    + "  todo d/DESCRIPTION by/YYYY-MM-DD                         Add a todo\n"
                    + "  edittodo INDEX [d/DESC] [by/DATE]                        Edit a todo\n"
                    + "  todolist                                                   View all todos\n"
                    + "  removetodo INDEX                                           Remove a todo\n\n"
                    + "--- General ---\n"
                    + "  reset                                                      Reset all data (requires confirm)\n"
                    + "  help                                                       Show this help message\n"
                    + "  bye                                                        Exit the application";

    @Override
    public String execute() {
        return HELP_TEXT;
    }
}
//@@author