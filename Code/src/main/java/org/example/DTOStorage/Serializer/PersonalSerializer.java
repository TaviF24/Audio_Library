package org.example.DTOStorage.Serializer;

import org.example.DTOs.AbstractDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonalSerializer extends AbstractSerializer{

    public PersonalSerializer() {
        setExtension(".txt");
    }

    @Override
    public void serialize(OutputStream outputStream, AbstractDTO dto) throws IOException {

        PrintStream printStream = new PrintStream(outputStream);
        StringBuilder s = new StringBuilder();
        HashMap<String, Object> hashMap = dto.getAllFields();
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            if(entry.getValue() instanceof List<?>){
                s.append(entry.getKey()).append("\n");
                List<Object> list = (List<Object>) entry.getValue();
                s.append(list.size()).append("\n");
                for (Object o : list) {
                    s.append(o.toString()).append("\n");
                }
            }else{
                s.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
            }
        }
        printStream.println(s);
    }
}
