# 🖥️ GUI – Benutzeroberfläche mit Java Swing

## Ziel

Die GUI ermöglicht eine einfache Nutzung des Notizbuchs mit grafischen Komponenten wie Textfeldern, Buttons, Listen.

## Aufbau

- **`NotizbuchGUI.java`**: Hauptklasse mit `JFrame`
- **Komponenten**: `JTextArea`, `JButton`, `JList`, `JFileChooser` etc.
- **Funktionen**:
    - Neue Notiz erstellen
    - Notiz speichern/laden (JSON, DB)
    - Notizliste anzeigen
    - AutoSave starten (Thread)

## Architektur

GUI ruft Methoden aus `NotizManager` auf → trennt Darstellung von Logik (MVC-Prinzip).

## Weiterführend

- GUI erweitert um:
    - Fortschrittsbalken bei Ladevorgängen
    - Popup-Dialoge bei Fehlern (z. B. DB nicht erreichbar)
    - Eingabefelder für Suchfunktionen

## Screenshot / Skizze

*(Hier kannst du per Screenshot oder ASCII zeigen, wie das GUI aussieht)*
