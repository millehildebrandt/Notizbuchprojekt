package main.app;

import main.model.Notiz;
import main.services.NotizService;
import main.patterns.SingletonLogger;
import main.io.NotizSpeicher;
import main.io.TextDateiSpeicher;
import main.io.ZipDateiSpeicher;
import main.security.UserManager;
import main.gui.NotizbuchGUI;

import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NotizService notizService = new NotizService();
        SingletonLogger logger = SingletonLogger.getInstance();
        UserManager userManager = new UserManager();

        System.out.println("=== NotizbuchApp ===");

        // Benutzer-Login oder Registrierung
        boolean angemeldet = false;
        while (!angemeldet) {
            System.out.println("\n1) Anmelden");
            System.out.println("2) Registrieren");
            System.out.print("Auswahl: ");
            String eingabe = scanner.nextLine();

            System.out.print("Benutzername: ");
            String benutzername = scanner.nextLine();

            System.out.print("Passwort: ");
            String passwort = scanner.nextLine();

            switch (eingabe) {
                case "1":
                    if (userManager.anmelden(benutzername, passwort)) {
                        System.out.println("Anmeldung erfolgreich. Willkommen, " + benutzername + "!");
                        logger.log("Benutzer angemeldet: " + benutzername);
                        angemeldet = true;
                    } else {
                        System.out.println("Anmeldung fehlgeschlagen. Bitte erneut versuchen.");
                    }
                    break;
                case "2":
                    if (userManager.registrieren(benutzername, passwort)) {
                        System.out.println("Registrierung erfolgreich. Sie können sich nun anmelden.");
                    } else {
                        System.out.println("Benutzername bereits vergeben.");
                    }
                    break;
                default:
                    System.out.println("Ungültige Auswahl.");
            }
        }

        // Hauptmenü
        while (true) {
            System.out.println("\nMenü:");
            System.out.println("1) Neue Notiz erstellen und speichern");
            System.out.println("2) Alle Notizen anzeigen");
            System.out.println("3) Alle Notizen als ZIP speichern");
            System.out.println("4) Notiz löschen");
            System.out.println("5) Programm beenden");
            System.out.println("6) GUI starten");

            System.out.print("Bitte wählen: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.print("Titel: ");
                    String titel = scanner.nextLine();

                    System.out.print("Inhalt: ");
                    String inhalt = scanner.nextLine();

                    Notiz neueNotiz = new Notiz(titel, inhalt);
                    notizService.speichern(neueNotiz);

                    System.out.println("Speichern als (txt / zip / ser)?");
                    String format = scanner.nextLine().trim().toLowerCase();

                    switch (format) {
                        case "txt":
                            TextDateiSpeicher.speichern(neueNotiz, titel + ".txt");
                            logger.log("Notiz als TXT gespeichert: " + titel);
                            break;
                        case "zip":
                      ZipDateiSpeicher.speichern(Arrays.asList(neueNotiz), titel + ".zip");
                            logger.log("Notiz als ZIP gespeichert: " + titel);
                            break;
                        case "ser":
                            NotizSpeicher.speichern(neueNotiz, titel + ".ser");
                            logger.log("Notiz als SER gespeichert: " + titel);
                            break;
                        default:
                            System.out.println("Unbekanntes Format, keine Datei gespeichert.");
                            logger.log("Speichern fehlgeschlagen: unbekanntes Format " + format);
                    }
                    break;

                case "2":
                    List<Notiz> alleNotizen = notizService.getAlleNotizen();
                    if (alleNotizen.isEmpty()) {
                        System.out.println("Keine Notizen vorhanden.");
                    } else {
                        System.out.println("\nAlle Notizen:");
                        for (Notiz n : alleNotizen) {
                            System.out.println(n.formatieren());
                            System.out.println("-----------------------");
                        }
                    }
                    break;

                case "3":
                    List<Notiz> notizen = notizService.getAlleNotizen();
                    if (notizen.isEmpty()) {
                        System.out.println("Keine Notizen vorhanden, nichts zu speichern.");
                    } else {
                        System.out.print("Name der ZIP-Datei (z.B. alle_notizen.zip): ");
                        String zipName = scanner.nextLine();
                        ZipDateiSpeicher.speichern(notizen, zipName);
                        logger.log("Alle Notizen in ZIP gespeichert: " + zipName);
                    }
                    break;

                case "4":
                    System.out.print("Titel der zu löschenden Notiz: ");
                    String titelLoeschen = scanner.nextLine();

                    boolean geloescht = notizService.loeschen(titelLoeschen);
                    if (geloescht) {
                        System.out.println("Notiz gelöscht.");
                        logger.log("Notiz gelöscht: " + titelLoeschen);
                    } else {
                        System.out.println("Notiz nicht gefunden.");
                    }
                    break;

                case "5":
                    logger.log("Programm wird beendet.");
                    System.out.println("Auf Wiedersehen!");
                    scanner.close();
                    System.exit(0);
                    break;

                case "6":
                    System.out.println("GUI wird gestartet...");
                    javax.swing.SwingUtilities.invokeLater(() -> new NotizbuchGUI().setVisible(true));
                    break;

                default:
                    System.out.println("Ungültige Eingabe. Bitte wählen Sie 1-6.");
            }
        }
    }
}
