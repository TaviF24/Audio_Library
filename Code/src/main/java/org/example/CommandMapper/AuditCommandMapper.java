package org.example.CommandMapper;

import org.example.Commands.AbstractCommand;
import org.example.Commands.AuditCommand;
import org.example.Exceptions.Unchecked.InvalidNumberOfParametersException;
import org.example.Exceptions.Unchecked.InvalidTypeOfParameterException;

import java.util.Optional;

public class AuditCommandMapper implements CommandMapper{
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if(!"audit".equals(command)){
            return Optional.empty();
        }
        if(args.length > 2 || args.length == 0){
            throw new InvalidNumberOfParametersException(command);
        }
        try{
            if(args.length == 2){
                Integer.parseInt(args[1]);
            }
        }catch (NumberFormatException e){
            throw new InvalidTypeOfParameterException(command);
        }
        if(args.length == 1){
            String[] newArgs = new String[]{args[0], "1"};
            return Optional.of(new AuditCommand(newArgs));
        }
        return Optional.of(new AuditCommand(args));
    }
}
