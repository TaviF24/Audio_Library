package org.example.DTOStorage;

import org.example.DTOStorage.Deserializer.Deserializer;
import org.example.DTOStorage.Deserializer.DeserializerFactory;
import org.example.DTOStorage.Serializer.AbstractSerializer;
import org.example.DTOStorage.Serializer.Serializer;
import org.example.DTOStorage.Serializer.SerializerFactory;
import org.example.DTOs.AbstractDTO;
import org.example.Exceptions.Checked.InvalidPathException;
import org.example.Exceptions.Checked.UnknownFormatException;
import org.example.Utils.FileUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Storage<T extends AbstractDTO> {

    public T readAll(String path, String format, AbstractDTO type) throws UnknownFormatException, InvalidPathException {
        Deserializer deserializer = DeserializerFactory.create(format);
        try{
            InputStream inputStream = FileUtils.openFileForReading(path);
            Object object = deserializer.deserialize(inputStream, type);
            if(object == null){
                throw new RuntimeException("Deserializer error. The input from the file may not correspond with the return type of the command");
            }
            return (T) object;
        }catch (FileNotFoundException e){
            throw new InvalidPathException(path);
        }
    }
    public void writeAll(List<T> dtos, String format, String fileName) throws UnknownFormatException, IOException {
        AbstractSerializer serializer = SerializerFactory.create(format);
        OutputStream outputStream = FileUtils.openFileForWriting(fileName + serializer.getExtension(), false);
        for (T dto : dtos) {
            serializer.serialize(outputStream, dto);
        }


    }


}
