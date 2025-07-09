package main.services;

import main.db.NotizRepository;
import main.model.Notiz;
import main.patterns.SingletonLogger;

import java.util.ArrayList;
import java.util.List;

public class NotizService {

    // Interner Speicher für Notizen (in-memory)
    private final List<Notiz> notizen = new ArrayList<>();

    // Datenbank-Repository
    private final NotizRepository repository = new NotizRepository();

    private final SingletonLogger logger = SingletonLogger.getInstance();

    // Notiz speichern (in internen Speicher und DB)
    public void speichern(Notiz notiz) {
        // Intern speichern
        notizen.add(notiz);
        logger.log("Notiz '" + notiz.getTitel() + "' wurde im internen Speicher gespeichert.");

        // In DB speichern
        repository.speichern(notiz);
    }

    // Alle Notizen zurückgeben (aus DB)
    public List<Notiz> getAlleNotizen() {
        // Wenn du alle aus DB holen willst, dann:
        return repository.getAlleNotizen();

        // Oder, falls du beide Listen zusammenführen willst:
        // List<Notiz> alle = new ArrayList<>(notizen);
        // alle.addAll(repository.getAlleNotizen());
        // return alle;
    }

    // Notiz anhand Titel löschen (in internem Speicher und DB)
    public boolean loeschen(String titel) {
        boolean internGeloescht = false;

        // Löschen aus internem Speicher
        for (Notiz n : new ArrayList<>(notizen)) {  // Kopie, um ConcurrentModification zu vermeiden
            if (n.getTitel().equals(titel)) {
                notizen.remove(n);
                internGeloescht = true;
                logger.log("Notiz '" + titel + "' wurde aus internem Speicher gelöscht.");
                break;
            }
        }

        // Löschen aus DB
        boolean dbGeloescht = repository.loeschen(titel);

        return internGeloescht || dbGeloescht;
    }
}
