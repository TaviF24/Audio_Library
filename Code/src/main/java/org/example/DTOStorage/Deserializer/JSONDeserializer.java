package org.example.DTOStorage.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonParser;
import org.example.DTOs.AbstractDTO;
import org.example.DTOs.PlaylistSongsDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JSONDeserializer implements Deserializer{
    @Override
    public Object deserialize(InputStream inputStream, AbstractDTO dto) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(inputStream, dto.getClass());
        } catch (IOException e) {
            System.out.println("JSON deserializer error\n" + e);
        }
        return null;
    }
}
