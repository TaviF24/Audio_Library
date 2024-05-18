package org.example.DTOStorage.Deserializer;

import org.example.DTOs.AbstractDTO;

import java.io.InputStream;
import java.util.List;

/**
 * Interface for deserializing data from an InputStream into objects.
 */
public interface Deserializer {

    /**
     * Deserializes data from the specified InputStream into an object of type Object,
     * based on the provided AbstractDTO type.
     *
     * @param inputStream the input stream to deserialize from
     * @param dto the AbstractDTO object providing context for deserialization
     * @return the deserialized object
     */
    Object deserialize(InputStream inputStream, AbstractDTO dto);
}
