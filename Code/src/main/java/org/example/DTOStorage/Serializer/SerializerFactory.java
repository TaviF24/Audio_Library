package org.example.DTOStorage.Serializer;

import org.example.Exceptions.Checked.UnknownFormatException;

public class SerializerFactory {
    public static AbstractSerializer create(String format) throws UnknownFormatException {
        switch (format){
            case "personal" -> {return new PersonalSerializer();}
            case "json" -> {return new JSONSerializer();}
            case "csv" -> {return new CSVSerializer();}
            default -> throw new UnknownFormatException();
        }
    }
}
