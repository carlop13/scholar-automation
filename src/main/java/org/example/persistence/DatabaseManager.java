package org.example.persistence;

import org.example.model.Publication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * Manages all interaction with the MySQL database.
 * Handles data connections and insertion.
 */
public class DatabaseManager {

    // --- DATABASE CONFIGURATION ---
    private static final String DB_URL = "jdbc:mysql://localhost:3306/scholar_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Carlish#13";

    /**
     * Inserts a publication into the database.
     * @param publication The Publication object to save.
     * @param researcherName The name of the researcher who owns the publication.
     */
    public void insertPublication(Publication publication, String researcherName) {
        // SQL query to insert data, using '?' as placeholders for security.
        String sql = "INSERT INTO articles (researcher_name, title, authors, publication_info, abstract_summary, cited_by) VALUES (?, ?, ?, ?, ?, ?)";

        // We use try-with-resources to ensure that the connection is closed automatically.
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // We map the data from the Publication object to the query placeholders.
            pstmt.setString(1, researcherName);
            pstmt.setString(2, publication.getTitle());

            // We convert the list of authors to a single String separated by commas.
            String authors = publication.getPublicationInfo().getAuthors().stream()
                    .map(author -> author.getName())
                    .collect(Collectors.joining(", "));
            pstmt.setString(3, authors);

            // We use the 'summary' as 'publication_info' and 'abstract_summary' for simplicity
            String summary = publication.getPublicationInfo().getSummary();
            pstmt.setString(4, summary);
            pstmt.setString(5, summary);

            pstmt.setInt(6, publication.getCitedBy() != null ? publication.getCitedBy().getTotal() : 0);

            // We execute the insertion.
            pstmt.executeUpdate();
            System.out.println("  -> Successfully inserted publication: " + publication.getTitle());

        } catch (SQLException e) {
            System.err.println("Database error when inserting publication: " + e.getMessage());
        }
    }
}