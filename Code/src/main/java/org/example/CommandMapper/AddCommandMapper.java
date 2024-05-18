package org.example.CommandMapper;

import org.example.Commands.AbstractCommand;
import org.example.Commands.AddCommand;
import org.example.Exceptions.Unchecked.InvalidNumberOfParametersException;
import org.example.Exceptions.Unchecked.InvalidTypeOfParameterException;

import java.util.Optional;

public class AddCommandMapper implements CommandMapper{
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if(!"add".equals(command)){
            return Optional.empty();
        }
        if(args.length < 3){
            throw new InvalidNumberOfParametersException(command);
        }
        if(!"byName".equals(args[0]) && !"byId".equals(args[0])){
            throw new InvalidTypeOfParameterException(command);
        }
        if("byId".equals(args[0])){
            try {
                Integer.parseInt(args[1]);
            }catch (NumberFormatException e){
                throw new InvalidTypeOfParameterException(command);
            }
        }
        return Optional.of(new AddCommand(args));
    }
}
