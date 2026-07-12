package bib.local.ui.gui.panels;

import bib.local.entities.Buch;

import javax.swing.*;

// Wichtig: Das BooksListPanel _ist eine_ JList und damit eine Component;
// es kann daher in das Layout eines anderen Containers
// (in unserer Anwendung des Frames) eingefügt werden.
public class BooksListPanel extends JList<Buch> {

	public BooksListPanel(java.util.List<Buch> buecher) {
		super();

		// ListModel erzeugen ...
		DefaultListModel<Buch> listModel = new DefaultListModel<>();
		// ... bei JList "anmelden" und ...
		setModel(listModel);
		// ... Daten an Model übergeben
		updateBooks(buecher);
	}
	
	public void updateBooks(java.util.List<Buch> buecher) {

		// Sortierung (mit Lambda-Expression)
		buecher.sort((b1, b2) -> b1.getNummer() - b2.getNummer());	// Sortierung nach Nummer
//		buecher.sort((b1, b2) -> b1.getTitel().compareTo(b2.getTitel()));	// Sortierung nach Titel

		// ListModel von JList holen und ...
		DefaultListModel<Buch> listModel = (DefaultListModel<Buch>) getModel();

		// ... Inhalt aktualisieren
		listModel.clear();
		listModel.addAll(buecher);
	}
}
