package main.patterns;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SingletonLogger {
    // Die einzige Instanz (Singleton)
    private static SingletonLogger instance;

    // Datumsformat für Logeinträge
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Privater Konstruktor, damit keine anderen Klassen instanziieren können
    private SingletonLogger() {
    }

    // Methode, um die einzige Instanz zu bekommen
    public static synchronized SingletonLogger getInstance() {
        if (instance == null) {
            instance = new SingletonLogger();
        }
        return instance;
    }

    // Log-Methode, die Nachrichten mit Zeitstempel ausgibt
    public void log(String message) {
        String time = LocalDateTime.now().format(dtf);
        System.out.println("[" + time + "] " + message);
    }
}
