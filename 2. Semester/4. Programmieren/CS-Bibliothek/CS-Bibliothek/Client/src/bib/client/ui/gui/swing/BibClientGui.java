package bib.client.ui.gui.swing;

import bib.client.net.BibliotheksFassade;
import bib.client.ui.gui.swing.models.BooksTableModel;
import bib.common.entities.Buch;
import bib.common.exceptions.BuchExistiertBereitsException;
import bib.common.interfaces.BibliotheksInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;

public class BibClientGui extends JFrame {

	public static final int DEFAULT_PORT = 6789;

	private BibliotheksInterface bib;

	private JButton addButton;
	private JTextField nummerTextField;
	private JTextField titelTextField;
	private JTextField suchTextfeld;
	private JList<Buch> buecherListe;
	private JTable buecherTabelle;

	public BibClientGui(String host, int port) throws HeadlessException, IOException {
		super("Bibliothek");

		// die Bib-Verwaltung erledigt die Aufgaben,
		// die nichts mit Ein-/Ausgabe zu tun haben
		bib = new BibliotheksFassade(host, port);

//		// Code für Umschaltung des Look-and-Feels:
//		// (Einfach mal ausprobieren!)
//		try {
//			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
////			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
////			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//			SwingUtilities.updateComponentTreeUI(this);
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
//				 UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
//		}

		initialize();
	}

	private void initialize() {

		// Steuerung des Verhaltens beim Schließen des Fensters
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowCloser());

		// Menü definieren
		JMenuBar menuBar = new BibMenuBar(bib);
		setJMenuBar(menuBar);

		// Layout des gesamten Fensters: BorderLayout mit drei Komponenten (Panels)
		// NORTH: Suchbereich
		// WEST: Einfügebereich
		// CENTER: Ausgabebereich (Bücher)
		this.setLayout(new BorderLayout());

		// NORTH: Suchbereich
		JPanel suchPanel = createSearchPanel();

		// WEST: Einfügebereich
		JPanel einfuegePanel = createAddPanel();

		// CENTER: Ausgabebereich (Bücher)
		JComponent buecherPanel = createBooksPanel();

		// "Zusammenbau" der Komponenten in BorderLayout
		this.add(suchPanel, BorderLayout.NORTH);
		this.add(einfuegePanel, BorderLayout.WEST);
		Container cp = getContentPane();
		cp.add(buecherPanel, BorderLayout.CENTER);
		// Hinweis zu ContentPane oben:
		// Komponenten müssen in Swing der ContentPane hinzugefügt werden (siehe oben).
		// this.add() oder add() können aber auch direkt auf einem Container-Objekt
		// aufgerufen werden. Die Komponenten werden dann per Default der ContentPane
		// hinzugefügt.

		this.setSize(new Dimension(800, 600));
		this.setVisible(true);
	}

	// CENTER: Ausgabebereich (Bücher in ScrollPane)
	private JComponent createBooksPanel() {
		java.util.List<Buch> buecher = bib.gibAlleBuecher();

//		// Variante 1: Swing-Liste
//        //   1A: ohne ListModel
//        buecherListe = new JList<>(buecher);
//        JScrollPane scrollPane = new JScrollPane(buecherListe);
//        //   1B: mit ListModel
//		// ListModel als "Datencontainer" anlegen und befüllen:
//		DefaultListModel<Buch> listModel = new DefaultListModel<>();
//        listModel.addAll(buecher);
//        // JList erzeugen (und in ScrollPane platzieren):
//		buecherListe = new JList<>(listModel);
//		JScrollPane scrollPane = new JScrollPane(buecherListe);

		// Variante 2: Swing-Tabelle
		// TableModel als "Datencontainer" anlegen:
		BooksTableModel tableModel = new BooksTableModel(buecher);
		// JTable erzeugen (und in ScrollPane platzieren):
		buecherTabelle = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(buecherTabelle);

		// Umrandung um ScrollPane
		scrollPane.setBorder(BorderFactory.createTitledBorder("Bücher"));

		return scrollPane;
	}

	// WEST: Einfügebereich
	private JPanel createAddPanel() {
		JPanel einfuegePanel = new JPanel();
		// West: BoxLayout (vertikal)
		einfuegePanel.setLayout(new BoxLayout(einfuegePanel, BoxLayout.PAGE_AXIS));

		// Abstandhalter ("Filler") zwischen Rand und erstem Element
		Dimension borderMinSize = new Dimension(5, 10);
		Dimension borderPrefSize = new Dimension(5, 10);
		Dimension borderMaxSize = new Dimension(5, 10);
		einfuegePanel.add(new Box.Filler(borderMinSize, borderPrefSize, borderMaxSize));

		// Eingabefelder
		einfuegePanel.add(new JLabel("Nummer: "));
		nummerTextField = new JTextField();
		einfuegePanel.add(nummerTextField);
		einfuegePanel.add(new JLabel("Titel: "));
		titelTextField = new JTextField();
		einfuegePanel.add(titelTextField);

		// Abstandhalter ("Filler") zwischen Eingabefeldern und Button
		Dimension minSize = new Dimension(1, 20);
		Dimension prefSize = new Dimension(1, Short.MAX_VALUE);
		Dimension maxSize = new Dimension(1, Short.MAX_VALUE);
		Box.Filler filler = new Box.Filler(minSize, prefSize, maxSize);
		einfuegePanel.add(filler);

		// Button
		addButton = new JButton("Hinzufügen");
		// Verhalten des Add-Buttons
		// 1.) ActionListener über anonyme Klasse
//        addButton.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        onAddButtonClick();
//                    }
//                }
//        );
		// 2.) ActionListener über Lambda Expression
		addButton.addActionListener(e -> onAddButtonClick());
		einfuegePanel.add(addButton);

		// Abstandhalter ("Filler") zwischen letztem Element und Rand
		einfuegePanel.add(new Box.Filler(borderMinSize, borderPrefSize, borderMaxSize));

		// Umrandung
		einfuegePanel.setBorder(BorderFactory.createTitledBorder("Einfügen"));

		return einfuegePanel;
	}

	// NORTH: Suchbereich
	private JPanel createSearchPanel() {
		JPanel suchPanel = new JPanel();
		// North: GridBagLayout
		// (Hinweis: Das ist schon ein komplexerer LayoutManager, der mehr kann als hier gezeigt.
		//  Hervorzuheben ist hier die Idee, explizit Constraints (also Nebenbedindungen) für
		//  die Positionierung / Ausrichtung / Größe von GUI-Komponenten anzugeben.)
		GridBagLayout gridBagLayout = new GridBagLayout();
		suchPanel.setLayout(gridBagLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;	// Zeile 0

		JLabel suchLabel = new JLabel("Suchbegriff:");
		c.gridx = 0;	// Spalte 0
		c.weightx = 0.2;	// 20% der gesamten Breite
		gridBagLayout.setConstraints(suchLabel, c);
		suchPanel.add(suchLabel);

		suchTextfeld = new JTextField();
		suchTextfeld.setToolTipText("Hier Suchbegriff eintragen.");
		c.gridx = 1;	// Spalte 1
		c.weightx = 0.6;	// 60% der gesamten Breite
		gridBagLayout.setConstraints(suchTextfeld, c);
		suchPanel.add(suchTextfeld);

		JButton suchButton = new JButton("Such!");
		c.gridx = 2;	// Spalte 2
		c.weightx = 0.2;	// 20% der gesamten Breite
		gridBagLayout.setConstraints(suchButton, c);
		suchPanel.add(suchButton);

		// ActionListener über Mitgliedsklasse
		suchButton.addActionListener(new SuchActionListener());

		// Umrandung
		suchPanel.setBorder(BorderFactory.createTitledBorder("Suche"));

		return suchPanel;
	}

	private void updateBooksPanel(java.util.List<Buch> buecher) {
		// Sortierung

		// Sortierung nach Nummer
		// 1.) Comparator über anonyme Klasse
//        buecher.sort(new Comparator<Buch>() {
//            @Override
//            public int compare(Buch b1, Buch b2) {
//                return b1.getNummer() - b2.getNummer();
//            }
//        });
		// 2.) Comparator über Lamda Expression
		buecher.sort((b1, b2) -> b1.getNummer() - b2.getNummer());
		// 3.) Mit Methodenreferenz
//        buecher.sort(Comparator.comparingInt(Buch::getNummer));

//        // Sortierung nach Titel
//        // 1.) Comparator über Lamda Expression
//        buecher.sort((b1, b2) -> b1.getTitel().compareTo(b2.getTitel()));
////        // 2.) Mit Methodenreferenz
////        buecher.sort(Comparator.comparing(Buch::getTitel));

		// Anzeige der Bücher per JList (1) oder JTable (2)
//        // Variante 1A: JList ohne dezidiertes ListModel mit Büchern befüllen
//        buecherListe.setListData(new Vector<>(buecher));
//        // Variante 1B: JList mit ListModel mit Büchern befüllen
//        DefaultListModel<Buch> listModel = (DefaultListModel<Buch>) buecherListe.getModel();
//        listModel.removeAllElements();
//        listModel.addAll(buecher);

		// Variante 2: JTable mit TableModel mit Büchern befüllen
		BooksTableModel tableModel = (BooksTableModel) buecherTabelle.getModel();
		tableModel.setBooks(buecher);
	}

	public void onAddButtonClick() {
		String nummerText = nummerTextField.getText();
		String titel = titelTextField.getText();
		if (!nummerText.isBlank() && !titel.isBlank()) {
			try {
				int nummer = Integer.parseInt(nummerTextField.getText());
				bib.fuegeBuchEin(titel, nummer);
				java.util.List<Buch> buecher = bib.gibAlleBuecher();
				updateBooksPanel(buecher);
				nummerTextField.setText("");
				titelTextField.setText("");
			} catch (NumberFormatException nfe) {
				System.err.println("Fehler: Bitte eine Zahl als Nummer eingeben");
			} catch (BuchExistiertBereitsException bebe) {
				System.err.println("Fehler: " + bebe.getMessage());
			}
		}
	}


	public static void main(String[] args) {
		int portArg = 0;
		String hostArg = null;
		InetAddress ia = null;

		// ---
		// Hier werden die main-Parameter geprüft:
		// ---

		// Host- und Port-Argument einlesen, wenn angegeben
		if (args.length > 2) {
			System.out.println("Aufruf: java <Klassenname> [<hostname> [<port>]]");
			System.exit(0);
		}
		switch (args.length) {
			case 0 -> {
				try {
					ia = InetAddress.getLocalHost();
				} catch (Exception e) {
					System.out.println("XXX InetAdress-Fehler: " + e);
					System.exit(0);
				}
				hostArg = ia.getHostName(); // host ist lokale Maschine
				portArg = DEFAULT_PORT;
			}
			case 1 -> {
				portArg = DEFAULT_PORT;
				hostArg = args[0];
			}
			case 2 -> {
				hostArg = args[0];
				try {
					portArg = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					System.out.println("Aufruf: java BibClientGUI [<hostname> [<port>]]");
					System.exit(0);
				}
			}
		}


		// Swing-UI auf dem GUI-Thread initialisieren
		// (host und port müssen für Verwendung in inner class final sein)
		final String host = hostArg;
		final int port = portArg;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BibClientGui gui = new BibClientGui(host, port);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Mitgliedsklasse mit ActionListener für Such-Button
	 */
	class SuchActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String suchBegriff = suchTextfeld.getText();
			java.util.List<Buch> suchErgebnis;
			if (!suchBegriff.isEmpty()) {
				suchErgebnis = bib.sucheNachTitel(suchBegriff);
			} else {
				suchErgebnis = bib.gibAlleBuecher();
			}
			suchTextfeld.setText("");
			updateBooksPanel(suchErgebnis);
		}
	}
}
