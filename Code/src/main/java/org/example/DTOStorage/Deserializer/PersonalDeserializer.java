package org.example.DTOStorage.Deserializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.example.DTOs.AbstractDTO;

public class PersonalDeserializer implements Deserializer {
    @Override
    public Object deserialize(InputStream inputStream, AbstractDTO dto) {
        String str;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList<String> buf = new ArrayList<>();
        try {
            while ((str = reader.readLine()) != null) {
                buf.add(str);
            }
        } catch (IOException e) {
            System.out.println("Personal deserializer error\n" + e);
        }

        dto.setFieldsFromList(buf);

        return dto;
    }
}
