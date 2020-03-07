package com.gmail.ezlotnikova.web.controller;

import com.gmail.ezlotnikova.service.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeleteDocumentController {

    private final DocumentService documentService;

    public DeleteDocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents/delete/{id}")
    public String deleteDocumentById(@PathVariable Long id) {
        documentService.deleteById(id);
        return "redirect:/documents";
    }

}