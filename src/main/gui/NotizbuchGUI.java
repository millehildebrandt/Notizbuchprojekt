package main.gui;

import main.model.Notiz;
import main.services.NotizService;
import main.io.TextDateiSpeicher;
import main.io.ZipDateiSpeicher;
import main.io.NotizSpeicher;
import main.patterns.SingletonLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

public class NotizbuchGUI extends JFrame {

    private JTextField titelFeld;
    private JTextArea inhaltFeld;
    private JTextArea ausgabeFeld;

    private NotizService service = new NotizService();
    private SingletonLogger logger = SingletonLogger.getInstance();

    public NotizbuchGUI() {
        setTitle("Notizbuch GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        // Layout
        JPanel panel = new JPanel(new BorderLayout());
        JPanel eingabePanel = new JPanel(new GridLayout(5, 1));
        JPanel buttonPanel = new JPanel(new FlowLayout());

        titelFeld = new JTextField();
        inhaltFeld = new JTextArea(5, 20);
        ausgabeFeld = new JTextArea();
        ausgabeFeld.setEditable(false);

        eingabePanel.add(new JLabel("Titel:"));
        eingabePanel.add(titelFeld);
        eingabePanel.add(new JLabel("Inhalt:"));
        eingabePanel.add(new JScrollPane(inhaltFeld));

        // Buttons
        JButton speichernTxtBtn = new JButton("Speichern als TXT");
        JButton speichernZipBtn = new JButton("Speichern als ZIP");
        JButton speichernSerBtn = new JButton("Speichern als SER");
        JButton anzeigenBtn = new JButton("Alle Notizen anzeigen");

        // Aktionen
        speichernTxtBtn.addActionListener(e -> speichern("txt"));
        speichernZipBtn.addActionListener(e -> speichern("zip"));
        speichernSerBtn.addActionListener(e -> speichern("ser"));
        anzeigenBtn.addActionListener(this::anzeigen);

        buttonPanel.add(speichernTxtBtn);
        buttonPanel.add(speichernZipBtn);
        buttonPanel.add(speichernSerBtn);
        buttonPanel.add(anzeigenBtn);

        panel.add(eingabePanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(new JScrollPane(ausgabeFeld), BorderLayout.SOUTH);

        add(panel);
    }

    private void speichern(String format) {
        String titel = titelFeld.getText().trim();
        String inhalt = inhaltFeld.getText().trim();

        if (titel.isEmpty() || inhalt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Titel und Inhalt dürfen nicht leer sein.");
            return;
        }

        Notiz notiz = new Notiz(titel, inhalt);
        service.speichern(notiz);

        switch (format) {
            case "txt":
                TextDateiSpeicher.speichern(notiz, titel + ".txt");
                break;
            case "zip":
                ZipDateiSpeicher.speichern(Arrays.asList(notiz), titel + ".zip");
                break;
            case "ser":
                NotizSpeicher.speichern(notiz, titel + ".ser");
                break;
        }

        logger.log("Notiz gespeichert als " + format.toUpperCase() + ": " + titel);
        titelFeld.setText("");
        inhaltFeld.setText("");
    }

    private void anzeigen(ActionEvent e) {
        List<Notiz> notizen = service.getAlleNotizen();
        ausgabeFeld.setText("");

        if (notizen.isEmpty()) {
            ausgabeFeld.setText("Keine Notizen vorhanden.");
        } else {
            for (Notiz n : notizen) {
                ausgabeFeld.append(n.formatieren() + "\n-------------------------\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NotizbuchGUI().setVisible(true));
    }
}
