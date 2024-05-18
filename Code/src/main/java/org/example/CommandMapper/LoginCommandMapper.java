package org.example.CommandMapper;

import org.example.Commands.AbstractCommand;
import org.example.Commands.AuthenticationAndAuthorization.LoginCommand;
import org.example.Exceptions.Unchecked.InvalidNumberOfParametersException;

import java.util.Optional;

public class LoginCommandMapper implements CommandMapper{
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if(!"login".equals(command)) {
            return Optional.empty();
        }
        if(args.length != 2){
            throw new InvalidNumberOfParametersException(command);
        }
        return Optional.of(new LoginCommand(args));
    }
}
