package org.example.CommandMapper;

import java.util.Optional;
import org.example.Commands.AbstractCommand;
import org.example.Commands.ImportPlaylistCommand;
import org.example.Exceptions.Unchecked.InvalidNumberOfParametersException;

public class ImportPlaylistCommandMapper implements CommandMapper {
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if (!"import".equals(command)
                || (args.length > 0 && !"playlist".equals(args[0]))
                || args.length == 0) {
            return Optional.empty();
        }
        if (args.length != 2) {
            throw new InvalidNumberOfParametersException(command + " " + args[0]);
        }
        String[] newArgs = new String[] {args[1]};
        return Optional.of(new ImportPlaylistCommand(newArgs));
    }
}
