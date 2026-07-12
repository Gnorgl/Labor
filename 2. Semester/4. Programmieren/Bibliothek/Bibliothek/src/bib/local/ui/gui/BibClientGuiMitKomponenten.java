package bib.local.ui.gui;

import bib.local.domain.Bibliothek;
import bib.local.entities.Buch;
import bib.local.ui.gui.panels.AddBookPanel;
import bib.local.ui.gui.panels.BooksListPanel;
import bib.local.ui.gui.panels.BooksTablePanel;
import bib.local.ui.gui.panels.SearchBooksPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
public class BibClientGuiMitKomponenten extends JFrame
	implements SearchBooksPanel.SearchResultListener, AddBookPanel.AddBookListener {

	private Bibliothek bib;

	private SearchBooksPanel searchPanel;
	private AddBookPanel addPanel;
//	private BooksListPanel booksPanel;
	private BooksTablePanel booksPanel;

	public BibClientGuiMitKomponenten(String titel) {
		super(titel);
		
		try {
			bib = new Bibliothek("BIB");
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initialize() {

		// Menü definieren
		JMenuBar menuBar = new BibMenuBar(bib);
		setJMenuBar(menuBar);

		// Klick auf Kreuz / roten Kreis (Fenster schließen) behandeln lassen:
		// A) Mittels Default Close Operation
//		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// B) Mittels WindowAdapter (für Sicherheitsabfrage)
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowClosingListener());
		
		// Layout des Frames: BorderLayout
		setLayout(new BorderLayout());

		// North
		searchPanel = new SearchBooksPanel(bib, this);
		
		// West
		addPanel = new AddBookPanel(bib, this);
		
		// Center	
		java.util.List<Buch> buecher = bib.gibAlleBuecher();
		// (wahlweise Anzeige als Liste oder Tabelle)
//		booksPanel = new BooksListPanel(buecher);
		booksPanel = new BooksTablePanel(buecher);
		JScrollPane scrollPane = new JScrollPane(booksPanel);
		
		// "Zusammenbau" in BorderLayout des Frames
		add(searchPanel, BorderLayout.NORTH);
		add(addPanel, BorderLayout.WEST);
		Container cp = getContentPane();
		cp.add(scrollPane, BorderLayout.CENTER);
		// Hinweis zur Nutzung von ContentPane oben:
		// Komponenten müssen in Swing der ContentPane hinzugefügt werden (siehe oben).
		// add() kann aber auch direkt auf einem Container-Objekt aufgerufen werden.
		// Die Komponenten werden dann per Default der ContentPane hinzugefügt.

		setSize(640, 480);
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
		java.util.List<Buch> buecher = bib.gibAlleBuecher();
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
	public void onSearchResult(java.util.List<Buch> buecher) {
		booksPanel.updateBooks(buecher);
	}
	
	
	public static void main(String[] args) {
		// Start der Anwendung (per anonymer Klasse)
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				BibClientGuiMitKomponenten gui = new BibClientGuiMitKomponenten("Bibliothek");
			}
		});
		
//		// Start der Anwendung (per Lambda-Expression)
//		SwingUtilities.invokeLater(() -> { BibGuiMitKomponenten gui = new BibGuiMitKomponenten("Bibliothek"); });
	}
}
