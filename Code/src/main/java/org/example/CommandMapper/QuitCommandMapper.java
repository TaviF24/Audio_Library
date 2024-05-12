package org.example.CommandMapper;
import org.example.Commands.AbstractCommand;
import org.example.Commands.Command;
import org.example.Commands.QuitCommand;
import org.example.Exceptions.InvalidNumberOfParametersException;

import java.util.Optional;

public class QuitCommandMapper implements CommandMapper{
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if(!"quit".equals(command)){
            return Optional.empty();
        }
        if(args.length != 0){
            throw new InvalidNumberOfParametersException(command);
        }
        return Optional.of(new QuitCommand(args));
    }
}
