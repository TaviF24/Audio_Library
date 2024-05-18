package org.example.DTOStorage.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.DTOs.AbstractDTO;
import org.example.DTOs.PlaylistSongsDTO;

import java.io.IOException;
import java.io.OutputStream;

public class JSONSerializer extends AbstractSerializer{

    public JSONSerializer() {
        setExtension(".json");
    }

    @Override
    public void serialize(OutputStream outputStream, AbstractDTO dto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputStream, dto);
    }
}
