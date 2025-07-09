package main.io;

import main.model.Notiz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextDateiSpeicher {

    // Speichert eine Notiz als einfache Textdatei ab
    public static void speichern(Notiz notiz, String dateiname) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dateiname))) {
            bw.write(notiz.formatieren());
            System.out.println("Notiz als Textdatei gespeichert: " + dateiname);
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben der Textdatei: " + e.getMessage());
        }
    }
}
