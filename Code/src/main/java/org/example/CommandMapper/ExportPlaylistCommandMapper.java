package org.example.CommandMapper;

import org.example.Commands.AbstractCommand;
import org.example.Commands.ExportPlaylistCommand;
import org.example.Exceptions.Unchecked.InvalidNumberOfParametersException;

import java.util.Optional;

public class ExportPlaylistCommandMapper implements CommandMapper{
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if(!"export".equals(command) || (args.length > 0 && !"playlist".equals(args[0])) || args.length == 0){
            return Optional.empty();
        }
        if(args.length != 3){
            throw new InvalidNumberOfParametersException(command + " " + args[0]);
        }
        String[] newArgs = new String[]{args[1], args[2]};
        return Optional.of(new ExportPlaylistCommand(newArgs));
    }
}
