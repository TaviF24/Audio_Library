package org.example.CommandMapper;

import org.example.Commands.AbstractCommand;
import org.example.Commands.HelpCommand;
import org.example.Exceptions.Unchecked.InvalidNumberOfParametersException;

import java.util.Optional;

public class HelpCommandMapper implements CommandMapper{
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if(!"help".equals(command)){
            return Optional.empty();
        }
        if(args.length !=0){
            throw new InvalidNumberOfParametersException(command);
        }
        return Optional.of(new HelpCommand(args));
    }
}
