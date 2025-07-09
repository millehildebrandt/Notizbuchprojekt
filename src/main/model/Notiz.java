package main.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Notiz implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String titel;
    private final String inhalt;
    private final LocalDate datum;

    public Notiz(String titel, String inhalt) {
        this.titel = titel;
        this.inhalt = inhalt;
        this.datum = LocalDate.now();
    }

    public String getTitel() {
        return titel;
    }

    public String getInhalt() {
        return inhalt;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public String formatieren() {
        return "Titel: " + titel + "\n" +
                "Datum: " + datum + "\n" +
                "Inhalt:\n" + inhalt + "\n";
    }
}



