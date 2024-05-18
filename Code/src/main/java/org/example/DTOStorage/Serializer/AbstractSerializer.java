package org.example.DTOStorage.Serializer;

public abstract class AbstractSerializer implements Serializer{
    private String extension;

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
