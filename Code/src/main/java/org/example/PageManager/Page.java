package org.example.PageManager;

import java.util.List;

public class Page {
    private final List<String> content;
    private final int fieldsPerElement;
    private final int elementsPerPage;

    public Page(List<String> content, int fieldsPerElement, int elementsPerPage) {
        this.content = content;
        this.fieldsPerElement = fieldsPerElement;
        this.elementsPerPage = elementsPerPage;
    }

    public String getContent() {
        String allContent = "";
        for (int i = 0; i < elementsPerPage; i++) {
            if (i >= content.size() / fieldsPerElement) {
                break;
            }
            allContent += (i + 1) + ". ";
            for (int j = 0; j < fieldsPerElement; j++) {
                allContent += content.get(j + i * fieldsPerElement) + " | ";
            }
            allContent += "\n";
        }
        return allContent;
    }
}
