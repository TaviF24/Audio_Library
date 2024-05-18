package org.example.CommandMapper;

import org.example.Commands.AbstractCommand;
import org.example.Commands.AuthenticationAndAuthorization.RegisterCommand;
import org.example.Exceptions.Unchecked.InvalidNumberOfParametersException;

import java.util.Optional;

public class RegisterCommandMapper implements CommandMapper{
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if(!"register".equals(command)){
            return Optional.empty();
        }
        if(args.length != 2){
            throw new InvalidNumberOfParametersException(command);
//            return Optional.empty();
        }
        return Optional.of(new RegisterCommand(args));
    }
}
