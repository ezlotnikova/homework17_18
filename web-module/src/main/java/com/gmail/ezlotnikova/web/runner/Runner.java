package com.gmail.ezlotnikova.web.runner;

import com.gmail.ezlotnikova.service.DocumentService;
import com.gmail.ezlotnikova.service.model.DocumentDTO;
import com.gmail.ezlotnikova.web.helper.generator.DocumentGenerator;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import static com.gmail.ezlotnikova.web.helper.generator.constant.Constant.INITIAL_DOCUMENT_AMOUNT;

@Component
public class Runner implements ApplicationRunner {

    private final DocumentService documentService;
    private final DocumentGenerator documentGenerator;
    private int startDocumentAmount = INITIAL_DOCUMENT_AMOUNT;

    public Runner(DocumentService documentService, DocumentGenerator documentGenerator) {
        this.documentService = documentService;
        this.documentGenerator = documentGenerator;
    }

    @Override
    public void run(ApplicationArguments args) {
        populateDocumentTable(startDocumentAmount);
    }

    private void populateDocumentTable(int initialDocumentAmount) {
        for (int i = 0; i < initialDocumentAmount; i++) {
            DocumentDTO document = documentGenerator.generateDocumentByNumber(i);
            documentService.add(document);
        }
    }

}