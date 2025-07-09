package main.security;

import java.security.MessageDigest;

public class PasswortUtil {

    public static String hash(String passwort) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(passwort.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b)); // Hex-Zeichen
            }
            return sb.toString();
        } catch (Exception e) {
        throw new RuntimeException("Fehler beim Hashen: " + e.getMessage());
        }
    }
}
