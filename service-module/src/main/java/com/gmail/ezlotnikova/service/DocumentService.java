package com.gmail.ezlotnikova.service;

import java.util.List;

import com.gmail.ezlotnikova.service.model.DocumentDTO;

public interface DocumentService {

    DocumentDTO add(DocumentDTO document);

    List<DocumentDTO> findAll();

    DocumentDTO findDocumentById(Long id);

    Integer deleteById(Long id);

}