package org.example.DTOStorage.Serializer;

import org.example.DTOs.AbstractDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface Serializer {
    void serialize(OutputStream outputStream, AbstractDTO dto) throws IOException;
}
