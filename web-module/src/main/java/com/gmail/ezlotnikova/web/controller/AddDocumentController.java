package com.gmail.ezlotnikova.web.controller;

import java.util.UUID;
import javax.validation.Valid;

import com.gmail.ezlotnikova.service.DocumentService;
import com.gmail.ezlotnikova.service.model.DocumentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddDocumentController {

    private final DocumentService documentService;

    public AddDocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents/add")
    public String showAddDocumentForm(Model model) {
        model.addAttribute("document", new DocumentDTO());
        return "add-document";
    }

    @PostMapping("/documents/add")
    public String addDocument(
            @Valid @ModelAttribute(name = "document") DocumentDTO document,
            BindingResult errors) {
        String uniqueNumber = UUID.randomUUID().toString();
        document.setUniqueNumber(uniqueNumber);
        if (errors.hasErrors()) {
            return "add-document";
        } else {
            documentService.add(document);
            return "redirect:/documents";
        }
    }

}