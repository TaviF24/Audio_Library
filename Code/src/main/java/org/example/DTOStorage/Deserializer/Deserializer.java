package org.example.DTOStorage.Deserializer;

import org.example.DTOs.AbstractDTO;

import java.io.InputStream;
import java.util.List;

public interface Deserializer {
    Object deserialize(InputStream inputStream, AbstractDTO dto);
}
