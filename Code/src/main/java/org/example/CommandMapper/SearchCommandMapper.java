package org.example.CommandMapper;

import org.example.Commands.AbstractCommand;
import org.example.Commands.SearchCommand;
import org.example.Exceptions.InvalidNumberOfParametersException;
import org.example.Exceptions.InvalidTypeOfParameterException;

import java.util.Optional;

public class SearchCommandMapper implements CommandMapper{
    @Override
    public Optional<AbstractCommand> tryMapCommand(String command, String[] args) {
        if(!"search".equals(command)){
            return Optional.empty();
        }
        if(args.length<2){
            throw new InvalidNumberOfParametersException(command);
        }
        if(!"author".equals(args[0]) && !"name".equals(args[0])){
            throw new InvalidTypeOfParameterException(command);
        }
        StringBuilder searchCriteria = new StringBuilder(args[1]);
        for(int i=2; i<args.length; i++){
            searchCriteria.append(" ").append(args[i]);
        }
        String[] newArgs = new String[]{args[0], searchCriteria.toString()};
        return Optional.of(new SearchCommand(newArgs));
    }
}
