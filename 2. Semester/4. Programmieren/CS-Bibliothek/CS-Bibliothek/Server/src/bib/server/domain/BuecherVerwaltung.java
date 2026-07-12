package bib.server.domain;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import bib.common.exceptions.BuchExistiertBereitsException;
import bib.common.entities.Buch;
import bib.server.persistence.FilePersistenceManager;
import bib.server.persistence.PersistenceManager;


/**
 * Klasse zur Verwaltung von Büchern.
 * 
 * @author teschke
 * @version 3 (Verwaltung der Buecher in Vector mit Generics)
 */
public class BuecherVerwaltung {

	// Verwaltung des Buchbestands in Vector (mit Generics)
	private List<Buch> buchBestand = new Vector<Buch>();

	// Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
	private PersistenceManager pm = new FilePersistenceManager();
	
	/**
	 * Methode zum Einlesen von Buchdaten aus einer Datei.
	 * 
	 * @param datei Datei, die einzulesenden Bücherbestand enthält
	 * @throws IOException
	 */
	public void liesDaten(String datei) throws IOException {
		// PersistenzManager für Lesevorgänge öffnen
		pm.openForReading(datei);

		Buch einBuch;
		do {
			// Buch-Objekt einlesen
			einBuch = pm.ladeBuch();
			if (einBuch != null) {
				// Buch in Liste einfügen
				try {
					einfuegen(einBuch);
				} catch (BuchExistiertBereitsException e1) {
					// Kann hier eigentlich nicht auftreten,
					// daher auch keine Fehlerbehandlung...
				}
			}
		} while (einBuch != null);

		// Persistenz-Schnittstelle wieder schließen
		pm.close();
	}

	/**
	 * Methode zum Schreiben der Buchdaten in eine Datei.
	 * 
	 * @param datei Datei, in die der Bücherbestand geschrieben werden soll
	 * @throws IOException
	 */
	public void schreibeDaten(String datei) throws IOException  {
		// PersistenzManager für Schreibvorgänge öffnen
		pm.openForWriting(datei);

		for (Buch b : buchBestand) {
			pm.speichereBuch(b);
		}

//		// Alternative Implementierung mit Iterator:
//		Iterator<Buch> iter = buchBestand.iterator();
//		while (iter.hasNext()) {
//			Buch b = iter.next();
//			pm.speichereBuch(b);
//		}

		// Persistenz-Schnittstelle wieder schließen
		pm.close();
	}
		
	/**
	 * Methode, die ein Buch an das Ende der Bücherliste einfügt.
	 * 
	 * @param einBuch das einzufügende Buch
	 * @throws BuchExistiertBereitsException wenn das Buch bereits existiert
	 */
	public void einfuegen(Buch einBuch) throws BuchExistiertBereitsException {
		if (buchBestand.contains(einBuch)) {
			throw new BuchExistiertBereitsException(einBuch, " - in 'einfuegen()'");
		}

		// das übernimmt der Vector
		buchBestand.add(einBuch);
	}

	/**
	 * Methode zum Löschen eines Buchs aus dem Bestand. 
	 * 
	 * @param einBuch das löschende Buch
	 */
	public void loeschen(Buch einBuch) {
		// das übernimmt der Vector
		buchBestand.remove(einBuch);
	}

	/**
	 * Methode, die anhand eines Titels nach Büchern sucht. Es wird eine Liste von Büchern
	 * zurückgegeben, die alle Bücher mit exakt übereinstimmendem Titel enthält.
	 * 
	 * @param titel Titel des gesuchten Buchs
	 * @return Liste der Bücher mit gesuchtem Titel (evtl. leer)
	 */
	public List<Buch> sucheBuecher(String titel) {
		List<Buch> suchErg = new Vector<Buch>();

		// Durchlaufen des Vectors mittels Iterator
		// (alternativ: for each-Schleife)
		Iterator<Buch> it = buchBestand.iterator();
		while (it.hasNext()) {
			Buch aktBuch = it.next();
			if (aktBuch.getTitel().equals(titel)) {
				// gefundenes Buch in Suchergebnis eintragen
				suchErg.add(aktBuch);
			}
		}
//		// Alternative Implementierung mit for-each-Schleife:
//		for (Buch buch : buchBestand) {
//			if ((buch).getTitel().equals(titel))
//				suchErg.add(buch);
//		}

		return suchErg;
	}

	/**
	 * Methode, die eine KOPIE des Bücherbestands zurückgibt.
	 * (Eine Kopie ist eine gute Idee, wenn ich dem Empfänger
	 * der Daten nicht ermöglichen möchte, die Original-Daten
	 * zu manipulieren.)
	 * ABER ACHTUNG: Die in der kopierten Bücherliste referenzierten
	 * 			sind nicht kopiert worden, d.h. ursprüngliche
	 * 			Bücherliste und ihre Kopie verweisen auf dieselben
	 *  		Buch-Objekte. Eigentlich müssten die einzelnen Buch-Objekte
	 * 			auch kopiert werden.
	 *
	 * @return Liste aller Bücher im Buchbestand (Kopie)
	 */
	public List<Buch> getBuchBestand() {
		return new Vector<>(buchBestand);
	}
	
	// TODO: Weitere Methoden, z.B. zum Auslesen und Entfernen von Büchern
	// ...
	
}
