package main.io;

import main.model.Notiz;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDateiSpeicher {

    // Speichert eine oder mehrere Notizen in eine ZIP-Datei
    public static void speichern(List<Notiz> notizen, String zipDateiName) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipDateiName))) {
            int counter = 1;
            for (Notiz notiz : notizen) {
                // Dateiname im ZIP für jede Notiz, Leerzeichen durch _ ersetzt
                String dateiName = "Notiz_" + counter + "_" + notiz.getTitel().replaceAll("\\s+", "_") + ".txt";

                ZipEntry entry = new ZipEntry(dateiName);
                zos.putNextEntry(entry);

                byte[] daten = notiz.formatieren().getBytes();
                zos.write(daten, 0, daten.length);
                zos.closeEntry();

                counter++;
            }
            System.out.println("Notizen als ZIP-Datei gespeichert: " + zipDateiName);
        } catch (IOException e) {
            System.err.println("Fehler beim Schreiben der ZIP-Datei: " + e.getMessage());
        }
    }
}
