package bib.local.ui.gui.models;

import bib.local.entities.Buch;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class BooksTableModel extends AbstractTableModel  {

    private List<Buch> buecher;
    private String[] spaltenNamen = { "Nummer","Titel", "verfügbar" };

    public BooksTableModel(List<Buch> aktuelleBuecher) {
    	super();
    	// Ich erstelle eine Kopie der Bücherliste,
    	// damit beim Aktualisieren (siehe Methode setBooks())
    	// keine unerwarteten Seiteneffekte entstehen.
    	buecher = new ArrayList<>(aktuelleBuecher);
    }

    public void setBooks(List<Buch> aktuelleBuecher) {
        buecher.clear();
        buecher.addAll(aktuelleBuecher);
        // JTable benachrichtigen, dass Daten geändert wurden.
        // (Löst neues Zeichnen aus.)
        fireTableDataChanged();
    }


    /*
     * Ab hier überschriebene Methoden mit Informationen, 
     * die eine JTable von jedem TableModel erwartet:
     * - Anzahl der Zeilen
     * - Anzahl der Spalten
     * - Benennung der Spalten
     * - Wert einer Zelle in Zeile / Spalte
     */
    
    @Override
    public int getRowCount() {
        return buecher.size();
    }

    @Override
    public int getColumnCount() {
        return spaltenNamen.length;
    }

    @Override
    public String getColumnName(int col) {
        return spaltenNamen[col];
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        Buch gewaehltesBuch = buecher.get(row);
        return switch (col) {
            case 0 -> gewaehltesBuch.getNummer();
            case 1 -> gewaehltesBuch.getTitel();
            case 2 -> gewaehltesBuch.isVerfuegbar();
            default -> null;
        };
    }
}
