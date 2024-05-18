package org.example.Commands;

import org.example.Exceptions.Checked.AudioLibraryCheckedException;

/**
 * Interface for executing commands.
 */
public interface Command {

    /**
     * Executes the command.
     *
     * @return {@code true} if the command is executed successfully, {@code false} otherwise
     * @throws AudioLibraryCheckedException if an error occurs during command execution
     */
    boolean execute() throws AudioLibraryCheckedException;
}
