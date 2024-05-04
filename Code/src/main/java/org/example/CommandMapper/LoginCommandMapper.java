package org.example.CommandMapper;

import org.example.Commands.AbstractCommand;
import org.example.Commands.Command;

import java.util.Optional;

public class LoginCommandMapper implements CommandMapper{
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        return Optional.empty();
    }
}
