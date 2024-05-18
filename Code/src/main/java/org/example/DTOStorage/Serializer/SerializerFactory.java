package org.example.DTOStorage.Serializer;

import org.example.Exceptions.Checked.UnknownFormatException;

public class SerializerFactory {

    /**
     * Creates an AbstractSerializer based on the specified format.
     *
     * @param format the format for serialization
     * @return an AbstractSerializer corresponding to the specified format
     * @throws UnknownFormatException if the format is unknown
     */
    public static AbstractSerializer create(String format) throws UnknownFormatException {
        switch (format){
            case "personal" -> {return new PersonalSerializer();}
            case "json" -> {return new JSONSerializer();}
            case "csv" -> {return new CSVSerializer();}
            default -> throw new UnknownFormatException();
        }
    }
}
