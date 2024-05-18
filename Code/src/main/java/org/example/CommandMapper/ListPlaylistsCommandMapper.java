package org.example.CommandMapper;

import java.util.Optional;
import org.example.Commands.AbstractCommand;
import org.example.Commands.ListPlaylistsCommand;
import org.example.Exceptions.Unchecked.InvalidNumberOfParametersException;
import org.example.Exceptions.Unchecked.InvalidTypeOfParameterException;

public class ListPlaylistsCommandMapper implements CommandMapper {
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if (!"list".equals(command)
                || (args.length > 0 && !"playlists".equals(args[0]))
                || args.length == 0) {
            return Optional.empty();
        }
        if (args.length > 2) {
            throw new InvalidNumberOfParametersException(command + " " + args[0]);
        }
        try {
            if (args.length == 2) {
                Integer.parseInt(args[1]);
            }
        } catch (NumberFormatException e) {
            throw new InvalidTypeOfParameterException(command);
        }
        String[] newArgs;
        if (args.length - 1 == 0) {
            newArgs = new String[] {"1"};
        } else {
            newArgs = new String[args.length - 1];
        }
        System.arraycopy(args, 1, newArgs, 0, args.length - 1);
        return Optional.of(new ListPlaylistsCommand(newArgs));
    }
}
