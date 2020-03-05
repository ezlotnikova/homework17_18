package com.gmail.ezlotnikova.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gmail.ezlotnikova.repository.ConnectionRepository;
import com.gmail.ezlotnikova.repository.DocumentRepository;
import com.gmail.ezlotnikova.repository.model.Document;
import com.gmail.ezlotnikova.service.DocumentService;
import com.gmail.ezlotnikova.service.model.DocumentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ConnectionRepository connectionRepository;
    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(
            ConnectionRepository connectionRepository,
            DocumentRepository documentRepository) {
        this.connectionRepository = connectionRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public DocumentDTO add(DocumentDTO documentDTO) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Document databaseDocument = convertDTOToDatabaseDocument(documentDTO);
                Document addedDocument = documentRepository.add(connection, databaseDocument);
                DocumentDTO addedDocumentDTO = convertDatabaseDocumentToDTO(addedDocument);
                connection.commit();
                return addedDocumentDTO;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return documentDTO;
    }

    @Override
    public List<DocumentDTO> findAll() {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<Document> documents = documentRepository.findAll(connection);
                List<DocumentDTO> documentDTOList = new ArrayList<>();
                for (Document document : documents) {
                    DocumentDTO documentDTO = convertDatabaseDocumentToDTO(document);
                    documentDTOList.add(documentDTO);
                }
                connection.commit();
                return documentDTOList;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    @Override
    public DocumentDTO findDocumentById(Long id) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Document foundDocument = documentRepository.findDocumentById(connection, id);
                DocumentDTO foundDocumentDTO = convertDatabaseDocumentToDTO(foundDocument);
                connection.commit();
                return foundDocumentDTO;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Integer deleteById(Long id) {
        Integer rowsDeleted = null;
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                rowsDeleted = documentRepository.deleteById(connection, id);
                connection.commit();
                return rowsDeleted;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return rowsDeleted;
    }

    private Document convertDTOToDatabaseDocument(DocumentDTO documentDTO) {
        Document document = new Document();
        String uniqueNumber = documentDTO.getUniqueNumber();
        document.setUniqueNumber(uniqueNumber);
        String description = documentDTO.getDescription();
        document.setDescription(description);
        return document;
    }

    private DocumentDTO convertDatabaseDocumentToDTO(Document document) {
        DocumentDTO documentDTO = new DocumentDTO();
        Long id = document.getId();
        documentDTO.setId(id);
        String uniqueNumber = document.getUniqueNumber();
        documentDTO.setUniqueNumber(uniqueNumber);
        String description = document.getDescription();
        documentDTO.setDescription(description);
        return documentDTO;
    }

}