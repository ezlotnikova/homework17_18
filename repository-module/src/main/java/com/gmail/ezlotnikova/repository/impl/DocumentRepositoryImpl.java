package com.gmail.ezlotnikova.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gmail.ezlotnikova.repository.DocumentRepository;
import com.gmail.ezlotnikova.repository.model.Document;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    @Override
    public Document add(Connection connection, Document document) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO document (unique_number, description) VALUES (?,?)",
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, document.getUniqueNumber());
            statement.setString(2, document.getDescription());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Adding document failed, no rows affected");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    document.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Adding document failed, no ID obtained");
                }
            }
            return document;
        }
    }

    @Override
    public List<Document> findAll(Connection connection) throws SQLException {
        try (
                Statement statement = connection.createStatement()
        ) {
            List<Document> documents = new ArrayList<>();
            try (ResultSet resultSet = statement.executeQuery(
                    "SELECT id, unique_number, description FROM document")) {
                while (resultSet.next()) {
                    Document document = getDocument(resultSet);
                    documents.add(document);
                }
                return documents;
            }
        }
    }

    @Override
    public Document findDocumentById(Connection connection, Long id) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id, unique_number, description FROM document " +
                                "WHERE id = ?")
        ) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return getDocument(rs);
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public Integer deleteById(Connection connection, Long id) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM document WHERE id = ?")
        ) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        }
    }

    private Document getDocument(ResultSet resultSet) throws SQLException {
        Document document = new Document();
        Long id = resultSet.getLong("id");
        document.setId(id);
        String uniqueNumber = resultSet.getString("unique_number");
        document.setUniqueNumber(uniqueNumber);
        String description = resultSet.getString("description");
        document.setDescription(description);
        return document;
    }

}