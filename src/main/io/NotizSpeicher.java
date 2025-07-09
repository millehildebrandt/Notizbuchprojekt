package main.io;

import main.model.Notiz;

import java.io.*;

public class NotizSpeicher {

    public static void speichern(Notiz notiz, String dateiname) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dateiname))) {
            oos.writeObject(notiz);
            System.out.println("Notiz wurde serialisiert gespeichert in: " + dateiname);
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
        }
    }

    public static Notiz laden(String dateiname) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dateiname))) {
            return (Notiz) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Fehler beim Laden: " + e.getMessage());
            return null;
        }
    }
}
