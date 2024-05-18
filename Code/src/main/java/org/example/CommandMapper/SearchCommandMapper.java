package org.example.CommandMapper;

import java.util.Optional;
import org.example.Commands.AbstractCommand;
import org.example.Commands.SearchCommand;
import org.example.Exceptions.Unchecked.InvalidNumberOfParametersException;
import org.example.Exceptions.Unchecked.InvalidTypeOfParameterException;

public class SearchCommandMapper implements CommandMapper {
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if (!"search".equals(command)) {
            return Optional.empty();
        }
        if (args.length < 2 || args.length > 3) {
            throw new InvalidNumberOfParametersException(command);
        }
        if (!"author".equals(args[0]) && !"name".equals(args[0])) {
            throw new InvalidTypeOfParameterException(command);
        }
        try {
            if (args.length == 3) {
                Integer.parseInt(args[2]);
            }
        } catch (NumberFormatException e) {
            throw new InvalidTypeOfParameterException(command);
        }
        if (args.length == 2) {
            String[] newArgs = new String[] {args[0], args[1], "1"};
            return Optional.of(new SearchCommand(newArgs));
        }
        return Optional.of(new SearchCommand(args));
    }
}
