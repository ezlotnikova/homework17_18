package com.gmail.ezlotnikova.web.controller;

import java.util.List;

import com.gmail.ezlotnikova.service.DocumentService;
import com.gmail.ezlotnikova.service.model.DocumentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllDocumentsController {

    private final DocumentService documentService;

    public ShowAllDocumentsController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents")
    public String showAllDocuments(Model model) {
        List<DocumentDTO> documents = documentService.findAll();
        model.addAttribute("documents", documents);
        return "documents";
    }

}