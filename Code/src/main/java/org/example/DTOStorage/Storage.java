package org.example.DTOStorage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import org.example.DTOStorage.Deserializer.Deserializer;
import org.example.DTOStorage.Deserializer.DeserializerFactory;
import org.example.DTOStorage.Serializer.AbstractSerializer;
import org.example.DTOStorage.Serializer.SerializerFactory;
import org.example.DTOs.AbstractDTO;
import org.example.Exceptions.Checked.InvalidPathException;
import org.example.Exceptions.Checked.UnknownFormatException;
import org.example.Utils.FileUtils;

public class Storage<T extends AbstractDTO> {

    /**
     * Reads data from a file using the specified format and deserializes it into objects of type T.
     *
     * @param path the path to the file
     * @param format the format of the file
     * @param type the type of the objects to deserialize
     * @return a list of deserialized objects
     * @throws UnknownFormatException if the format is unknown
     * @throws InvalidPathException if the path is invalid
     */
    public T readAll(String path, String format, AbstractDTO type)
            throws UnknownFormatException, InvalidPathException {
        Deserializer deserializer = DeserializerFactory.create(format);
        try {
            InputStream inputStream = FileUtils.openFileForReading(path);
            Object object = deserializer.deserialize(inputStream, type);
            if (object == null) {
                throw new RuntimeException(
                        "Deserializer error. The input from the file may not correspond with the"
                                + " return type of the command");
            }
            return (T) object;
        } catch (FileNotFoundException e) {
            throw new InvalidPathException(path);
        }
    }

    /**
     * Writes a list of DTOs to a file using the specified format.
     *
     * @param dtos the list of DTOs to write
     * @param format the format to use for serialization
     * @param fileName the name of the file to write to
     * @throws UnknownFormatException if the format is unknown
     * @throws IOException if an I/O error occurs
     */
    public void writeAll(List<T> dtos, String format, String fileName)
            throws UnknownFormatException, IOException {
        AbstractSerializer serializer = SerializerFactory.create(format);
        OutputStream outputStream =
                FileUtils.openFileForWriting(fileName + serializer.getExtension(), false);
        for (T dto : dtos) {
            serializer.serialize(outputStream, dto);
        }
    }
}
