package main.security;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private static final String USER_FILE = "users.txt";
    private Map<String, String> users = new HashMap<>();

    public UserManager() {
        loadUsers();
    }

    private void loadUsers() {
        File file = new File(USER_FILE);
        if (!file.exists()) {
            System.out.println("Noch keine Benutzerdatei vorhanden.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] teile = line.split(":");
                if (teile.length == 2) {
                    users.put(teile[0], teile[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Laden der Benutzer: " + e.getMessage());
        }
    }

    public boolean anmelden(String benutzername, String passwort) {
        String hash = PasswortUtil.hash(passwort);
        return users.containsKey(benutzername) && users.get(benutzername).equals(hash);
    }

    public boolean registrieren(String benutzername, String passwort) {
        if (users.containsKey(benutzername)) {
            return false; // Benutzer schon vorhanden
        }
        String hash = PasswortUtil.hash(passwort);
        users.put(benutzername, hash);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            bw.write(benutzername + ":" + hash);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
            return false;
        }
    }
}
