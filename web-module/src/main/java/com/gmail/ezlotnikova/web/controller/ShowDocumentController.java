package com.gmail.ezlotnikova.web.controller;

import com.gmail.ezlotnikova.service.DocumentService;
import com.gmail.ezlotnikova.service.model.DocumentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ShowDocumentController {

    private final DocumentService documentService;

    public ShowDocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents/{id}")
    public String showDocumentById(@PathVariable Long id, Model model) {
        DocumentDTO document = documentService.findDocumentById(id);
        model.addAttribute("document", document);
        return "document";
    }

}