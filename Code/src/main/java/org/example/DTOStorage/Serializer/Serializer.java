package org.example.DTOStorage.Serializer;

import org.example.DTOs.AbstractDTO;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Interface for serializing AbstractDTO objects to an OutputStream.
 */
public interface Serializer {

    /**
     * Serializes an AbstractDTO object to the specified OutputStream.
     *
     * @param outputStream the output stream to serialize to
     * @param dto the AbstractDTO object to serialize
     * @throws IOException if an I/O error occurs during serialization
     */
    void serialize(OutputStream outputStream, AbstractDTO dto) throws IOException;
}
