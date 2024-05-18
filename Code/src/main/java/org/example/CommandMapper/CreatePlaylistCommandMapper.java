package org.example.CommandMapper;

import org.example.Commands.AbstractCommand;
import org.example.Commands.CreatePlaylistCommand;
import org.example.Exceptions.Unchecked.InvalidNumberOfParametersException;

import java.util.Optional;

public class CreatePlaylistCommandMapper implements CommandMapper{
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if(!"create".equals(command) || (args.length > 0 && !"playlist".equals(args[0])) || args.length == 0){
            return Optional.empty();
        }
        if(args.length != 2)
            throw new InvalidNumberOfParametersException(command + " " + args[0]);
        String[] newArgs = new String[args.length-1];
        System.arraycopy(args, 1, newArgs, 0, args.length - 1);
        return Optional.of(new CreatePlaylistCommand(newArgs));
    }
}
