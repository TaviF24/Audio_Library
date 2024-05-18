package org.example.DTOStorage.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import org.example.DTOs.AbstractDTO;

public class JSONSerializer extends AbstractSerializer {

    public JSONSerializer() {
        setExtension(".json");
    }

    @Override
    public void serialize(OutputStream outputStream, AbstractDTO dto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputStream, dto);
    }
}
