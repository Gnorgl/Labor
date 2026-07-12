package bib.local.ui.gui.panels;

import bib.local.entities.Buch;
import bib.local.ui.gui.models.BooksTableModel;

import javax.swing.*;
import java.util.List;

public class BooksTablePanel extends JTable {

	public BooksTablePanel(List<Buch> buecher) {
		super();

		// TableModel erzeugen und gewünschte Sortierung übergeben ...
		BooksTableModel tableModel = new BooksTableModel(buecher);

		// ... bei JTable "anmelden" und ...
		setModel(tableModel);
	}
	
	public void updateBooks(List<Buch> buecher) {
		// Sortierung (mit Lambda-Expression)
		buecher.sort((b1, b2) -> b1.getNummer() - b2.getNummer());	// Sortierung nach Nummer
//		buecher.sort((b1, b2) -> b1.getTitel().compareTo(b2.getTitel()));	// Sortierung nach Titel

		// TableModel von JTable holen und ...
		BooksTableModel tableModel = (BooksTableModel) getModel();
//		// ... Inhalt aktualisieren
		tableModel.setBooks(buecher);
	}
}
