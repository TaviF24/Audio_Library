package org.example;

import org.example.CommandMapper.CommandMapper;
import org.example.CommandMapper.QuitCommandMapper;
import org.example.Commands.AbstractCommand;
import org.example.Commands.Command;
import org.example.Exceptions.InvalidCommandExceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class InputConverter {
    private static final List<CommandMapper> MAPPERS = Arrays.asList(new QuitCommandMapper());

    public static AbstractCommand mapCommand(String command, String[] args){
        for (CommandMapper mapper : MAPPERS) {
            Optional<AbstractCommand> commandOptional = mapper.tryMapCommand(command, args);
            if(commandOptional.isPresent()){
                return commandOptional.get();
            }
        }
        return null;
    }


}
