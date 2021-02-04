package com.rest.API.dto.model.id;

public class IdDto {
    String id;
    String href;

    public IdDto(){}

    public IdDto(String id, String href){
        this.id=id;
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "IdDto{" +
                "id='" + id + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
