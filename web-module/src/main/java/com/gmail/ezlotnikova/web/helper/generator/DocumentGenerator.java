package com.gmail.ezlotnikova.web.helper.generator;

import java.util.UUID;

import com.gmail.ezlotnikova.service.model.DocumentDTO;
import org.springframework.stereotype.Component;

@Component
public class DocumentGenerator {

    public DocumentDTO generateDocumentByNumber(int i) {
        DocumentDTO document = new DocumentDTO();
        String uniqueNumber = UUID.randomUUID().toString();
        document.setUniqueNumber(uniqueNumber);
        document.setDescription("Description_" + (i + 1));
        return document;
    }

}