package main.beans;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class NotizBean implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String titel;
    private String inhalt;
    private LocalDate datum;

    // Öffentlicher, leerer Konstruktor (wichtig für JavaBeans)
    public NotizBean() {
        this.datum = LocalDate.now();
    }

    // Konstruktor mit Parametern
    public NotizBean(String titel, String inhalt) {
        this.titel = titel;
        this.inhalt = inhalt;
        this.datum = LocalDate.now();
    }

    // Getter und Setter – für JavaBean-Standard
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    // Optional: praktische Ausgabe-Methode
    public String formatieren() {
        return "Titel: " + titel + "\n" +
                "Datum: " + datum + "\n" +
                "Inhalt:\n" + inhalt + "\n";
    }
}
