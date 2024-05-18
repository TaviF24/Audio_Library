package org.example.DTOStorage.Deserializer;

import org.example.Exceptions.Checked.UnknownFormatException;

public class DeserializerFactory {
    public static Deserializer create(String format) throws UnknownFormatException {
        switch (format){
            case "txt" -> {return new PersonalDeserializer();}
            case "json" -> {return new JSONDeserializer();}
            case "csv" -> {return new CSVDeserializer();}
            default -> throw new UnknownFormatException();
        }
    }
}
