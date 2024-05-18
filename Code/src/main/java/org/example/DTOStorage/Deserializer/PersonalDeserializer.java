package org.example.DTOStorage.Deserializer;

import org.example.DTOs.AbstractDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class PersonalDeserializer implements Deserializer{
    @Override
    public Object deserialize(InputStream inputStream, AbstractDTO dto) {
        HashMap<String,Object> hashMap = dto.getAllFields();
        String str;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList<String> buf = new ArrayList<>();
        try{
        while((str = reader.readLine()) != null){
            buf.add(str);
        }
        }catch (IOException ignored){}

        dto.setFieldsFromList(buf);

        return dto;
    }
}
