package com.gmail.ezlotnikova.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gmail.ezlotnikova.repository.model.Document;

public interface DocumentRepository {

    Document add(Connection connection, Document document) throws SQLException;

    List<Document> findAll(Connection connection) throws SQLException;

    Document findDocumentById(Connection connection, Long id) throws SQLException;

    Integer deleteById(Connection connection, Long id) throws SQLException;

}