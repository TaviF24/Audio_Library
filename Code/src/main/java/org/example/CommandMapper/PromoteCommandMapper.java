package org.example.CommandMapper;

import org.example.Commands.AbstractCommand;
import org.example.Commands.PromoteCommand;
import org.example.Exceptions.Unchecked.InvalidNumberOfParametersException;

import java.util.Optional;

public class PromoteCommandMapper implements CommandMapper{
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if(!"promote".equals(command)){
            return Optional.empty();
        }
        if(args.length != 1){
            throw new InvalidNumberOfParametersException(command);
        }
        String[] newArgs = new String[2];
        newArgs[0] = args[0];
        newArgs[1] = "";
        return Optional.of(new PromoteCommand(newArgs));
    }
}
