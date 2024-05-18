package org.example.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractDTO {

    /**
     * Gets the list to be exported.
     *
     * @return the list to be exported
     */
    @JsonIgnore
    public abstract List<Object> getListToBeExported();

    /**
     * Sets the list.
     *
     * @param list the list you want to be exported
     */
    @JsonIgnore
    public abstract void setList(List<Object> list);

    /**
     * Gets all fields of the DTO.
     *
     * @return a map containing all fields
     */
    @JsonIgnore
    public abstract HashMap<String, Object> getAllFields();

    /**
     * Gets the type of elements of the list.
     *
     * @return the type of elements of the list
     */
    @JsonIgnore
    public abstract Class getTypeOfElementsOfList();

    /**
     * Sets fields from list. The input format must be similar to personal format. If you don't know the format, export a playlist and check the file.
     *
     * @param list the list from which to set fields
     */
    public abstract void setFieldsFromList(List<String> list);
}
