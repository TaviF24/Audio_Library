package org.example.DTOStorage.Deserializer;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.example.DTOs.AbstractDTO;

public class CSVDeserializer implements Deserializer {
    @Override
    public Object deserialize(InputStream inputStream, AbstractDTO dto) {

        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        Class type = dto.getTypeOfElementsOfList();
        ObjectReader oReader = mapper.reader(type).with(schema);
        try {
            MappingIterator<Object> map = oReader.readValues(inputStream);
            List<Object> list = map.readAll();
            dto.setList(list);
            return dto;

        } catch (IOException e) {
            System.out.println("CSV deserializer error\n" + e);
        }

        return null;
    }
}
