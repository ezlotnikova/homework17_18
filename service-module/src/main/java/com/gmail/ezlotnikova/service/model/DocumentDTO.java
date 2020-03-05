package com.gmail.ezlotnikova.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DocumentDTO {

    private Long id;
    private String uniqueNumber;
    @NotNull
    @Size(min = 5, max = 100, message = "Must be between 5 and 100 characters long")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}