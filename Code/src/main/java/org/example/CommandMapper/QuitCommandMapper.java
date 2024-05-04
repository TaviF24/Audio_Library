package org.example.CommandMapper;
import org.example.Commands.AbstractCommand;
import org.example.Commands.Command;
import org.example.Commands.QuitCommand;

import java.util.Optional;

public class QuitCommandMapper implements CommandMapper{
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {

        if(!"quit".equals(command))
            return Optional.empty();
        return Optional.of(new QuitCommand());
    }
}
