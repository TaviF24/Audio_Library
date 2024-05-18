package org.example.CommandMapper;

import java.util.Optional;
import org.example.Commands.AbstractCommand;
import org.example.Commands.AuthenticationAndAuthorization.LogoutCommand;
import org.example.Exceptions.Unchecked.InvalidNumberOfParametersException;

public class LogoutCommandMapper implements CommandMapper {
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if (!"logout".equals(command)) {
            return Optional.empty();
        }
        if (args.length != 0) {
            throw new InvalidNumberOfParametersException(command);
        }
        return Optional.of(new LogoutCommand(args));
    }
}
