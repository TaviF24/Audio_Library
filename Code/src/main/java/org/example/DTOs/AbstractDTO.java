package org.example.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractDTO {

    @JsonIgnore
    public abstract List<Object> getListToBeExported();

    @JsonIgnore
    public abstract void setList(List<Object> list);

    @JsonIgnore
    public abstract HashMap<String, Object> getAllFields();

    @JsonIgnore
    public abstract Class getTypeOfElementsOfList();

    public abstract void setFieldsFromList(List<String> list);
}
