# 📘 NotizbuchProjekt

Ein vielseitiges Java-Lernprojekt zur Vorbereitung auf die **AP1-Prüfung** in der Umschulung zur Fachinformatiker:in – Fachrichtung Anwendungsentwicklung.  
Dieses Projekt ist mehr als nur ein Notizbuch: Es integriert zahlreiche Themen aus den Bereichen OOP, GUI, Datenhaltung, Sicherheit, Design Patterns, Projektmanagement, Wirtschaft und vieles mehr.

---

## 🚀 Projektziele

- **Lernen durch Praxis:** Theorie aus dem Unterricht direkt in Code übersetzen
- **AP1-relevante Themen** strukturiert anwenden
- **Nachvollziehbarer Aufbau** mit Java, SQLite, JavaFX, JSON, XML, Threads usw.
- Vorbereitung auf die spätere Umsetzung eines **CRM-Systems in C#**

---

## 🗂️ Projektstruktur

![Image](https://github.com/user-attachments/assets/1ae02f33-1cce-4648-be4e-a78762fe6a23)

## 💡 Wichtige Features

| Funktion                   | Beschreibung |
|---------------------------|--------------|
| ✍️ Notizenverwaltung       | Anlegen, Anzeigen, Löschen von Notizen |
| 💾 Speicherung             | In-Memory, als Datei (JSON), und in SQLite-Datenbank |
| 👤 Benutzerverwaltung      | Registrierung, Login, Passwort-Hashing |
| 🖼️ GUI (JavaFX)           | Grafische Oberfläche mit Loginmaske und Notizbereich |
| 🧱 Design Patterns         | Singleton, Observer, MVC – mit Codebeispielen |
| 🧪 JUnit-Tests             | Testgetriebene Entwicklung |
| 🔐 Sicherheit              | Passwort-Hashing, Grundkonzepte IT-Sicherheit |
| 🧮 Wirtschaftstools        | Nutzwertanalyse, Leasingrechner, Stromkosten |
| 🔄 Threads & Daemons       | Hintergrundprozesse wie Autospeicherung |
| 🔌 Datenbankanbindung      | Speicherung & Abruf über SQLite (`jdbc:sqlite`) |
| 🧠 UML-Simulationen        | Ablaufdiagramme als Java-Code |
| 📊 Projektmanagement       | Scrum vs. Wasserfall, Netzplan, SMART-Ziele |

---

## 🛠️ Voraussetzungen

- Java 21+
- Maven 3.x
- SQLite JDBC-Treiber (`org.xerial:sqlite-jdbc`)
- JavaFX (falls GUI verwendet wird)
- IntelliJ IDEA empfohlen

---

## 🗃️ Datenbank

SQLite-Datei wird automatisch erstellt im Projektverzeichnis:

```bash
notizbuch.db
CREATE TABLE IF NOT EXISTS notizen (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titel TEXT NOT NULL,
    inhalt TEXT,
    erstellt_am TEXT
);

## Lernziel & Hintergrund
Dieses Projekt wurde im Rahmen der Umschulung zur Fachinformatiker:in – AE entwickelt und dient als praktischer Lernspeicher für alle prüfungsrelevanten Inhalte der AP1.
Langfristiges Ziel ist die Entwicklung eines modularen CRM-Systems, das später auch in C# umgesetzt werden kann.

📄 Lizenz
MIT-Lizenz – siehe LICENSE

## Autor
Mille Hildebrandt
Umschulung zur Fachinformatikerin AE (IHK)
Deutschland, 2025
