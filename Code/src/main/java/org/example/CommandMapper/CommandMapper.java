package org.example.CommandMapper;

import java.util.Optional;
import org.example.Commands.AbstractCommand;

/**
 * Interface for mapping strings to their corresponding commands implementations.
 */
public interface CommandMapper {

    /**
     * Attempts to map the given command and arguments to an {@link AbstractCommand} object.
     * <p>
     * This method tries to create an {@link AbstractCommand} based on the provided command string and arguments.
     * If the mapping is successful, the command is returned wrapped in an {@link Optional}.
     * </p>
     *
     * @param command the command string to be mapped
     * @param args the arguments for the command
     * @return an {@link Optional} containing the mapped {@link AbstractCommand} if mapping is successful, otherwise an empty {@link Optional}
     */
    Optional<AbstractCommand> tryMapCommand(String command, String[] args);
}
