package org.example;

import org.example.CommandInterface.CommandFormat;
import org.example.CommandMapper.*;
import org.example.Commands.AbstractCommand;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Exceptions.Unchecked.AudioLibraryUncheckedException;
import org.example.Exceptions.Unchecked.UnknownCommandException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class InputConverter {
    private static final List<CommandMapper> MAPPERS = Arrays.asList(
            new QuitCommandMapper(),
            new RegisterCommandMapper(),
            new LoginCommandMapper(),
            new LogoutCommandMapper(),
            new ListPlaylistsCommandMapper(),
            new CreatePlaylistCommandMapper(),
            new PromoteCommandMapper(),
            new CreateSongCommandMapper(),
            new AuditCommandMapper(),
            new SearchCommandMapper(),
            new AddCommandMapper(),
            new ExportPlaylistCommandMapper(),
            new ImportPlaylistCommandMapper(),
            new RedoCommandMapper(),
            new HelpCommandMapper()
    );

    /**
     * Maps the given command format to an appropriate command object.
     * <p>
     * This method attempts to map the command string and its arguments from the provided
     * {@link CommandFormat} to a corresponding {@link AbstractCommand} using available mappers.
     * If the command cannot be mapped, an exception is thrown.
     * </p>
     *
     * @param commandFormat the format of the command to be mapped
     * @return the mapped {@link AbstractCommand} object, or {@code null} if mapping fails
     * @throws UnknownCommandException if the command cannot be mapped to any known command
     */
    public static AbstractCommand mapCommand(CommandFormat commandFormat){
        String command = commandFormat.getCommand();
        String[] args = commandFormat.getArgs();
        for (CommandMapper mapper : MAPPERS) {
            if(command.isEmpty()){
                return null;
            }
            try{
                Optional<AbstractCommand> commandOptional = mapper.tryMapCommand(command, args);
                if(commandOptional.isPresent()){
                    return commandOptional.get();
                }
            }catch (AudioLibraryUncheckedException e){
                System.out.println(e.getMessage());
                DBWrapper dbWrapper = new DBWrapper(Credits.getConnectionCredits());
                dbWrapper.saveCommand(Session.getSessionUser(), commandFormat.getOriginalCommand(), false);
                return null;
            }
        }
        throw new UnknownCommandException();
    }


}
