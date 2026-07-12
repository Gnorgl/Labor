package bib.client.ui.gui.swing.panels;

import java.util.Collections;

import javax.swing.JTable;

import bib.client.ui.gui.swing.models.BooksTableModel;
import bib.common.entities.Buch;

// Wichtig: Das BooksTablePanel _ist ein_ Panel und damit auch eine Component;
// es kann daher in das Layout eines anderen Containers
// (in unserer Anwendung des Frames) eingefügt werden.
public class BooksTablePanel extends JTable {

	public BooksTablePanel(java.util.List<Buch> buecher) {
		super();

		// TableModel erzeugen ...
		BooksTableModel tableModel = new BooksTableModel(Collections.emptyList());
		// ... bei JTable "anmelden" und ...
		setModel(tableModel);
		// ... Daten an Model übergeben (für Sortierung o.ä.)
		updateBooks(buecher);
	}

	public void updateBooks(java.util.List<Buch> buecher) {
		// Sortierung (mit Lambda-Expression)
		buecher.sort((b1, b2) -> b1.getNummer() - b2.getNummer());	// Sortierung nach Nummer
//		buecher.sort((b1, b2) -> b1.getTitel().compareTo(b2.getTitel()));	// Sortierung nach Titel

		// TableModel von JTable holen und ...
		BooksTableModel tableModel = (BooksTableModel) getModel();
//		// ... Inhalt aktualisieren
		tableModel.setBooks(buecher);
	}
}
