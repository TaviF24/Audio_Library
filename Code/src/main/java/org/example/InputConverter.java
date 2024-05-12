package org.example;

import org.example.CommandMapper.*;
import org.example.Commands.AbstractCommand;
import org.example.Commands.Command;
import org.example.Data.Users.User;
import org.example.DatabaseManager.Credits;
import org.example.DatabaseManager.DBWrapper;
import org.example.Exceptions.AudioLibraryRuntimeException;
import org.example.Exceptions.UnknownCommandException;

import java.util.ArrayList;
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
            new SearchCommandMapper()
    );

    public static AbstractCommand mapCommand(String command, String[] args){
        for (CommandMapper mapper : MAPPERS) {
            if(command.isEmpty()){
                return null;
            }
            try{
                Optional<AbstractCommand> commandOptional = mapper.tryMapCommand(command, args);
                if(commandOptional.isPresent()){
                    return commandOptional.get();
                }
            }catch (AudioLibraryRuntimeException e){
                System.out.println(e.getMessage());
                DBWrapper dbWrapper = new DBWrapper(Credits.getConnectionCredits());
                StringBuilder commandAndArgs = new StringBuilder(command);
                for (String arg : args) {
                    commandAndArgs.append(" ").append(arg);
                }

                dbWrapper.saveCommand(Session.getSessionUser(), commandAndArgs.toString(), false);
                return null;
            }
        }
        throw new UnknownCommandException();
//        return null;
    }


}
