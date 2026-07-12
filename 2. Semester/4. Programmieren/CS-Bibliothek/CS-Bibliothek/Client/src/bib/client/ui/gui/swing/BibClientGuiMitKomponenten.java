package bib.client.ui.gui.swing;

import bib.client.net.BibliotheksFassade;
import bib.client.ui.gui.swing.panels.AddBookPanel;
import bib.client.ui.gui.swing.panels.BooksTablePanel;
import bib.client.ui.gui.swing.panels.SearchBooksPanel;
import bib.common.interfaces.BibliotheksInterface;
import bib.common.entities.Buch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

/**
 * Alternative Version der Bibliotheks-GUI, bei der die im Layout vorgesehenen Bereich für das
 * - Hinzufügen neuer Bücher,
 * - Suchen nach Büchern und
 * - Ausgeben von Büchern
 * nicht mehr innerhalb dieser Klasse definiert werden, sondern in Klassen im Unterpaket "panels".
 * Jede der genannten Aufgaben wird dort in einer Unterklasse von Panel implementiert;
 * hier werden nur noch Objekte der Unterklassen erzeugt und in das Layout des Frames eingefügt.
 * Die Kommunikation zwischen den Panels und dem (zentralen) Frame erfolgt dabei über Interfaces,
 * die in den Panel-Unterklassen definiert und in der Frame-Unterklasse implementiert werden.
 *
 * @author thorsten
 *
 */
public class BibClientGuiMitKomponenten extends JFrame implements AddBookPanel.AddBookListener, SearchBooksPanel.SearchResultListener {

	public static final int DEFAULT_PORT = 6789;

	private BibliotheksInterface bib;

	private SearchBooksPanel searchPanel;
	private AddBookPanel addPanel;
//    private BooksListPanel booksPanel;        // Alternative zu Verwendung einer Tabelle (siehe nächste Zeile)
	private BooksTablePanel booksPanel;

	public BibClientGuiMitKomponenten(String title, String host, int port) throws HeadlessException, IOException {
		super(title);

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

		// Klick auf Kreuz / roten Kreis (Fenster schließen)
		// behandeln lassen:
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowCloser());

		// Menü definieren
		JMenuBar menuBar = new BibMenuBar(bib);
		setJMenuBar(menuBar);

		// Layout des Frames: BorderLayout
		setLayout(new BorderLayout());

		// NORTH
		searchPanel = new SearchBooksPanel(bib, this);

		// WEST:
		addPanel = new AddBookPanel(bib, this);

		// CENTER: nur eine Liste/Tabelle, die mit dem Inhalt einer util-List gefüllt wird
		// Hinweis: Es gibt die List-Schnittstelle aus dem Paket java.util
		//          und die List-Klasse aus dem Paket java.awt.
		//          Da die IDE bereits das gesamte AWT-Paket importiert hat
		//          (siehe import-Anweisung oben), spreche ich hier die
		//          List-Schnittstelle explizit durch Angabe des util-Pakets an.
		List<Buch> buecher = bib.gibAlleBuecher();
		// Wahlweise Liste oder Tabelle...
//        booksPanel = new BooksListPanel(buecher);
		booksPanel = new BooksTablePanel(buecher);
		// ... in ScrollPane einfügen
		JScrollPane scrollPane = new JScrollPane(booksPanel);

		// "Zusammenbau" in BorderLayout des Frames
		add(searchPanel, BorderLayout.NORTH);
		add(addPanel, BorderLayout.WEST);
		add(scrollPane, BorderLayout.CENTER);

		setSize(800, 600);
		setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 *
	 * Listener, der Benachrichtungen erhält, wenn im AddBookPanel ein Buch eingefügt wurde.
	 * (Als Reaktion soll die Bücherliste aktualisiert werden.)
	 * @see bib.local.ui.gui.panels.AddBookPanel.AddBookListener#onBookAdded(bib.local.entities.Buch)
	 */
	@Override
	public void onBookAdded(Buch buch) {
		// Ich lade hier einfach alle Bücher neu und lasse sie anzeigen
		List<Buch> buecher = bib.gibAlleBuecher();
		booksPanel.updateBooks(buecher);
	}

	/*
	 * (non-Javadoc)
	 *
	 * Listener, der Benachrichtungen erhält, wenn das SearchBooksPanel ein Suchergebnis bereitstellen möchte.
	 * (Als Reaktion soll die Bücherliste aktualisiert werden.)
	 * @see bib.local.ui.gui.swing.panels.SearchBooksPanel.SearchResultListener#onSearchResult(java.util.List)
	 */
	@Override
	public void onSearchResult(List<Buch> booksList) {
		booksPanel.updateBooks(booksList);
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
					BibClientGuiMitKomponenten gui = new BibClientGuiMitKomponenten("Bibliothek", host, port);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
