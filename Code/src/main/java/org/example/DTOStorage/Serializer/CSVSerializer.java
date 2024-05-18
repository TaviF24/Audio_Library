package org.example.DTOStorage.Serializer;

//import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.example.DTOs.AbstractDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class CSVSerializer extends AbstractSerializer{
    public CSVSerializer() {
        setExtension(".csv");
    }

    @Override
    public void serialize(OutputStream outputStream, AbstractDTO dto) throws IOException {
        List<Object> list = dto.getListToBeExported();
        if(!list.isEmpty()){
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = mapper.typedSchemaFor(list.get(0).getClass()).withHeader();
            schema = schema.withColumnSeparator(',');
            ObjectWriter objectWriter = mapper.writer(schema);
            objectWriter.writeValue(outputStream, list);
        }
    }
}
