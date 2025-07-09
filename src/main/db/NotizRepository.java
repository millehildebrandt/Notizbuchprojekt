package main.db;

import main.model.Notiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotizRepository {

    // Verbindung zur H2-Dateidatenbank (nicht flüchtig!)
    private static final String DB_URL = "jdbc:h2:file:./notizbuch;AUTO_SERVER=TRUE";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    // Konstruktor wird einmal beim Start aufgerufen
    public NotizRepository() {
        // Datenbank und Tabelle anlegen, wenn sie nicht existiert
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS notizen (" +
                    "id IDENTITY PRIMARY KEY," + // Auto-Inkrement-ID
                    "titel VARCHAR(255) NOT NULL," +
                    "inhalt CLOB NOT NULL" +
                    ")";

            stmt.execute(sql);

        } catch (SQLException e) {
            System.err.println("❌ Fehler beim Initialisieren der H2-Datenbank: " + e.getMessage());
        }
    }

    // Speichert eine Notiz dauerhaft in der Datenbank
    public void speichern(Notiz notiz) {
        String sql = "INSERT INTO notizen (titel, inhalt) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, notiz.getTitel());
            pstmt.setString(2, notiz.getInhalt());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("❌ Fehler beim Speichern der Notiz: " + e.getMessage());
        }
    }

    // Gibt alle gespeicherten Notizen zurück
    public List<Notiz> getAlleNotizen() {
        List<Notiz> notizen = new ArrayList<>();
        String sql = "SELECT titel, inhalt FROM notizen";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String titel = rs.getString("titel");
                String inhalt = rs.getString("inhalt");
                notizen.add(new Notiz(titel, inhalt));
            }

        } catch (SQLException e) {
            System.err.println("❌ Fehler beim Laden der Notizen: " + e.getMessage());
        }

        return notizen;
    }

    // Löscht eine Notiz anhand des Titels
    public boolean loeschen(String titel) {
        String sql = "DELETE FROM notizen WHERE titel = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, titel);
            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("❌ Fehler beim Löschen der Notiz: " + e.getMessage());
            return false;
        }
    }
}
