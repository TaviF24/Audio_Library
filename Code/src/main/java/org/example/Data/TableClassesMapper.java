package org.example.Data;

import java.util.HashMap;

/**
 * Interface for mapping table classes to their respective columns.
 */
public interface TableClassesMapper {

    /**
     * Retrieves the columns of the implementing class as a map.
     *
     * @return a map containing the columns of the implementing class
     */
    HashMap<String, Object> getColumns();
}
