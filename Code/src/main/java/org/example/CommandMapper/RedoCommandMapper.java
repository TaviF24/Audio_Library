package org.example.CommandMapper;

import org.example.Commands.AbstractCommand;
import org.example.Commands.RedoCommand;
import org.example.Exceptions.Unchecked.InvalidNumberOfParametersException;
import org.example.Exceptions.Unchecked.InvalidTypeOfParameterException;

import java.util.Optional;

public class RedoCommandMapper implements CommandMapper{
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if(!"redo".equals(command)){
            return Optional.empty();
        }
        if(args.length != 1){
            throw new InvalidNumberOfParametersException(command);
        }
        try {
            Integer.parseInt(args[0]);
        }catch (NumberFormatException e){
            throw new InvalidTypeOfParameterException(command);
        }

        return Optional.of(new RedoCommand(args));
    }
}
