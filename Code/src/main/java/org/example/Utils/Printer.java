package org.example.Utils;

import org.example.Commands.AbstractCommand;

public class Printer {

    /**
     * Prints a status message based on the success of the command execution.
     *
     * @param command the command executed
     * @param status {@code true} if the command executed successfully, {@code false} otherwise
     */
    public static void printStatusMessage(AbstractCommand command, boolean status) {
        if (status) {
            System.out.println(command.getSuccessMessage());
        }
    }
}
