package org.example.DTOStorage.Deserializer;

import org.example.Exceptions.Checked.UnknownFormatException;

public class DeserializerFactory {

    /**
     * Creates a Deserializer based on the specified format.
     *
     * @param format the format for deserialization
     * @return a Deserializer corresponding to the specified format
     * @throws UnknownFormatException if the format is unknown
     */
    public static Deserializer create(String format) throws UnknownFormatException {
        switch (format) {
            case "txt" -> {
                return new PersonalDeserializer();
            }
            case "json" -> {
                return new JSONDeserializer();
            }
            case "csv" -> {
                return new CSVDeserializer();
            }
            default -> throw new UnknownFormatException();
        }
    }
}
