package org.example.CommandMapper;

import org.example.Commands.AbstractCommand;
import org.example.Commands.CreatePlaylistCommand;
import org.example.Commands.CreateSongCommand;
import org.example.Exceptions.InvalidNumberOfParametersException;
import org.example.Exceptions.InvalidTypeOfParameterException;

import java.util.Optional;

public class CreateSongCommandMapper implements CommandMapper{
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if(!"create".equals(command) || (args.length > 0 && !"song".equals(args[0])) || args.length == 0){
            return Optional.empty();
        }
        if(args.length != 4){
            throw new InvalidNumberOfParametersException(command + " " + args[0]);
        }
        try{
            Integer.parseInt(args[3]);
        }catch (NumberFormatException e){
            throw new InvalidTypeOfParameterException(command);
        }

        String[] newArgs = new String[args.length-1];
        System.arraycopy(args, 1, newArgs, 0, args.length - 1);
        return Optional.of(new CreateSongCommand(newArgs));
    }
}
