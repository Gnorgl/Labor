package bib.local.ui.gui;

import bib.local.domain.Bibliothek;
import bib.local.domain.exceptions.BuchExistiertBereitsException;
import bib.local.entities.Buch;
import bib.local.ui.gui.models.BooksTableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class BibClientGUI extends JFrame {

    private Bibliothek bib;

    private JButton suchButton;
    private JTextField suchTextfeld;
    private JButton addButton;
    private JTextField nummerTextfeld;
    private JTextField titelTextfeld;
//    private JList<Buch> buecherListe;
    private JTable buecherListe;

    public BibClientGUI(String titel, String datei) {
        super(titel);

        try {
            bib = new Bibliothek(datei);

//            // Code für Umschaltung des Look-and-Feels:
//            // (Einfach mal ausprobieren!)
//            try {
////                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
////    			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//    			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//                SwingUtilities.updateComponentTreeUI(this);
//            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
//                     UnsupportedLookAndFeelException e) {
//                e.printStackTrace();
//            }

            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        // Steuerung des Verhaltens beim Schließen des Fensters
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        WindowClosingListener closer = new WindowClosingListener();
        addWindowListener(closer);

        // Menü definieren
        JMenuBar menuBar = new BibMenuBar(bib);
        setJMenuBar(menuBar);

        // Layout des gesamten Fensters: BorderLayout mit drei Komponenten (Panels)
        // NORTH: Suchbereich
        // WEST: Einfügebereich
        // CENTER: Ausgabebereich (Bücher)
        setLayout(new BorderLayout());

        // NORTH: Suchbereich
        JPanel suchPanel = createSearchPanel();
        // WEST: Einfügebereich
        JPanel einfuegePanel = createAddPanel();
        // CENTER: Ausgabebereich (Bücher)
        JComponent buecherPanel = createBooksPanel();

        // "Zusammenbau" der Komponenten in BorderLayout
        add(suchPanel, BorderLayout.NORTH);
        add(einfuegePanel, BorderLayout.WEST);
        Container cp = getContentPane();
        cp.add(buecherPanel, BorderLayout.CENTER);
        // Hinweis zur Nutzung von ContentPane oben:
        // Komponenten müssen in Swing der ContentPane hinzugefügt werden (siehe oben).
        // add() kann aber auch direkt auf einem Container-Objekt aufgerufen werden.
        // Die Komponenten werden dann per Default der ContentPane hinzugefügt.

        setSize(800, 600);
        setVisible(true);
    }


    // NORTH: Suchbereich
    private JPanel createSearchPanel() {
        JPanel suchPanel = new JPanel();

        // Nur zu Demo-Zwecken: ein Listener zur Beobachtung von MouseEvents
        // mit Konsolenausgaben bei Mausklicks im Bereich des Such-Panels
        // sowie beim Betreten und Verlassen des Panels.
        suchPanel.addMouseListener(new MouseEventListener());

        // North: GridBagLayout
        // (Hinweis: Das ist schon ein komplexerer LayoutManager, der mehr kann als hier gezeigt.
        //  Hervorzuheben ist hier die Idee, explizit Constraints (also Nebenbedindungen) für
        //  die Positionierung / Ausrichtung / Größe von GUI-Komponenten anzugeben.)
        GridBagLayout gridBagLayout = new GridBagLayout();
        suchPanel.setLayout(gridBagLayout);

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;	    // Zeile 0

        JLabel suchLabel = new JLabel("Suchbegriff:");
        c.gridx = 0;	    // Spalte 0
        c.weightx = 0.2;	// 20% der gesamten Breite
        gridBagLayout.setConstraints(suchLabel, c);
        suchPanel.add(suchLabel);

        suchTextfeld = new JTextField();
        suchTextfeld.setToolTipText("Hier Suchbegriff eingeben.");
        c.gridx = 1;	    // Spalte 1
        c.weightx = 0.6;	// 60% der gesamten Breite
        gridBagLayout.setConstraints(suchTextfeld, c);
        suchPanel.add(suchTextfeld);

        suchButton = new JButton("Such!");
        c.gridx = 2;	    // Spalte 2
        c.weightx = 0.2;	// 20% der gesamten Breite
        gridBagLayout.setConstraints(suchButton, c);
        suchPanel.add(suchButton);

        suchButton.addActionListener(new SearchActionListener());

        suchPanel.setBorder(BorderFactory.createTitledBorder("Suche"));

        return suchPanel;
    }

    // WEST: Einfügebereich
    private JPanel createAddPanel() {
        JPanel einfuegePanel = new JPanel();
        // BoxLayout (vertikal)
        einfuegePanel.setLayout(new BoxLayout(einfuegePanel, BoxLayout.Y_AXIS));

        // Abstandhalter ("Filler") zwischen Rand und erstem Element
        // mit Angabe von Minimalgröße, bevorzugter Größe und Maximalgröße
        Dimension borderMinSize = new Dimension(5, 10);
        Dimension borderPrefSize = new Dimension(5, 10);
        Dimension borderMaxSize = new Dimension(5, 10);
        einfuegePanel.add(
                new Box.Filler(
                        borderMinSize,
                        borderPrefSize,
                        borderMaxSize));

        // Labels und Eingabefelder
        einfuegePanel.add(new JLabel("Nummer:"));
        nummerTextfeld = new JTextField();
        einfuegePanel.add(nummerTextfeld);
        einfuegePanel.add(new JLabel("Titel:"));
        titelTextfeld = new JTextField();
        einfuegePanel.add(titelTextfeld);

        // Abstandhalter ("Filler") zwischen Eingabefeldern und Button
        // mit Angabe von Minimalgröße, bevorzugter Größe und Maximalgröße
        // (bevorzugte und maximale Größe mit größtmöglicher Höhe, damit
        // sich der Filler bei vertikalem Platz streckt).
        Box.Filler filler = new Box.Filler(
                new Dimension(1, 20),
                new Dimension(1, Short.MAX_VALUE),
                new Dimension(1, Short.MAX_VALUE));
        einfuegePanel.add(filler);

        // Hinzufügen-Button
        addButton = new JButton("Hinzufügen");

        // Listener registrieren
        addButton.addActionListener(event -> onAddButtonClick());

        einfuegePanel.add(addButton);

        // Abstandhalter ("Filler") zwischen letztem Element und Rand
        einfuegePanel.add(new Box.Filler(
                borderMinSize, borderPrefSize, borderMaxSize));

        einfuegePanel.setBorder(BorderFactory.createTitledBorder("Hinzufügen"));

        return einfuegePanel;
    }

    private void onAddButtonClick() {
        int nummer = Integer.parseInt(nummerTextfeld.getText());
        String titel = titelTextfeld.getText();
        try {
            // Buch in Bibliothek einfügen
            bib.fuegeBuchEin(titel, nummer);
            // Felder zurücksetzen
            nummerTextfeld.setText("");
            titelTextfeld.setText("");
            // Bücherliste aktualisieren
            java.util.List<Buch> buecher = bib.gibAlleBuecher();
            updateBooksList(buecher);
        } catch (BuchExistiertBereitsException ex) {
            // Dialog mit Fehlermeldung öffnen
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    // CENTER: Ausgabebereich (Bücher in ScrollPane)
    private JComponent createBooksPanel() {
        // JList mit Büchern aus Bibliothek füllen

        // Variante 1: Swing-Liste
        //   1A: ohne ListModel
//        buecherListe = new JList<>();
        //   1B: mit ListModel
//        // ListModel als zunächst leeren "Datencontainer" anlegen:
//		DefaultListModel<Buch> listModel = new DefaultListModel<>();
//		buecherListe = new JList<>(listModel);

        // Variante 2: Swing-Tabelle
        BooksTableModel booksTableModel = new BooksTableModel(Collections.emptyList());
        buecherListe = new JTable(booksTableModel);

        java.util.List<Buch> buecher = bib.gibAlleBuecher();
        updateBooksList(buecher);

        // JList in JScrollPane stecken, damit Inhalt gescrollt werden kann.
        JScrollPane scrollPane = new JScrollPane(buecherListe);

        scrollPane.setBorder(BorderFactory.createTitledBorder("Bücher"));

        return scrollPane;
    }

    private void updateBooksList(java.util.List<Buch> buecher) {
        // Sortierung

        // Sortierung nach Nummer
        // 1.) Comparator über anonyme Klasse
//        buecher.sort(new Comparator<Buch>() {
//            @Override
//            public int compare(Buch buch1, Buch buch2) {
//                return buch1.getNummer() - buch2.getNummer();
////                return buch1.getTitel().compareTo(buch2.getTitel());
//            }
//        });

        // 2.) Comparator über Lambda Expression
        buecher.sort((buch1, buch2) -> buch1.getNummer() - buch2.getNummer());
        // 3.) Mit Methodenreferenz
//        buecher.sort(Comparator.comparingInt(Buch::getNummer));

        // Sortierung nach Titel
//        // 1.) Comparator über Lambda Expression
//        buecher.sort((buch1, buch2) -> buch1.getTitel().compareTo(buch2.getTitel()));
//        // 2.) Mit Methodenreferenz
//        buecher.sort(Comparator.comparing(Buch::getTitel));

//        // Anzeige der Bücher per JList (1) oder JTable (2)
        // Variante 1A: JList ohne dezidiertes ListModel mit Büchern befüllen
//        buecherListe.setListData(new Vector<>(buecher));
        // Variante 1B: JList mit ListModel mit Büchern befüllen
//        DefaultListModel<Buch> listModel = (DefaultListModel<Buch>) buecherListe.getModel();
//        listModel.removeAllElements();
//        listModel.addAll(buecher);

        // Variante 2: JTable mit TableModel
        BooksTableModel booksTableModel = (BooksTableModel) buecherListe.getModel();
        booksTableModel.setBooks(buecher);
    }

    class SearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String suchBegriff = suchTextfeld.getText();
            java.util.List<Buch> suchErgebnis;
            if (suchBegriff.isBlank()) {
                suchErgebnis = bib.gibAlleBuecher();
            } else {
                suchErgebnis = bib.sucheNachTitel(suchBegriff);
            }
            suchTextfeld.setText("");
            updateBooksList(suchErgebnis);
        }
    }

    public static void main(String[] args) {
//        BibClientGUI frame = new BibClientGUI("Bibliothek", "BIB");

        // Start der Anwendung (per anonymer Klasse)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BibClientGUI gui = new BibClientGUI("Bibliothek", "BIB");
            }
        });

//		// Start der Anwendung (per Lambda-Expression)
//		SwingUtilities.invokeLater(() -> { BibClientGUI gui = new BibClientGUI("Bibliothek", "BIB"); });
    }

}
