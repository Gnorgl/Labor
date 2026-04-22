package bib.local.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bib.local.domain.exceptions.BuchExistiertBereitsException;
import bib.local.persistence.FilePersistenceManager;
import bib.local.persistence.PersistenceManager;
import bib.local.entities.Buch;


/**
 * Klasse zur Verwaltung von Büchern.
 * 
 * @author teschke
 * @version 1.2 (Verwaltung der Bücher in Liste mt Generics)
 */
public class BuecherVerwaltung {

	// Verwaltung des Buchbestands in einer ArrayList
	private List<Buch> buchBestand = new ArrayList<>();

	// Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
	private PersistenceManager pm = new FilePersistenceManager();
	
	/**
	 * Methode zum Einlesen von Buchdaten aus einer Datei.
	 * 
	 * @param datei Datei, die einzulesenden Bücherbestand enthält
	 * @throws IOException
	 */
	public void ladeDaten(String datei) throws IOException {
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
	public void speichereDaten(String datei) throws IOException  {
		// PersistenzManager für Schreibvorgänge öffnen
		pm.openForWriting(datei);

		// Durchlaufen einer Liste mit einem Iterator:
		if (!buchBestand.isEmpty()) {
            for (Buch b : buchBestand) {
                pm.speichereBuch(b);
            }
//			Iterator<Buch> iter = buchBestand.iterator();
//			while (iter.hasNext()) {
//				Buch b = iter.next();
//				pm.speichereBuch(b);
//			}
		}

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

		// das übernimmt die ArrayList:
		buchBestand.add(einBuch);
	}

	/**
	 * Methode zum Löschen eines Buchs aus dem Bestand. 
	 * 
	 * @param einBuch das löschende Buch
	 */
	public void loeschen(Buch einBuch) {
		// das übernimmt die ArrayList:
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
		List<Buch> suchErg = new ArrayList<>();

		// Durchlaufen einer Liste mit einem Iterator:
        for (Buch aktBuch : buchBestand) {
            if (aktBuch.getTitel().equals(titel)) {
                // gefundenes Buch in Suchergebnis eintragen
                suchErg.add(aktBuch);
            }
        }
//		Iterator<Buch> it = buchBestand.iterator();
//		while (it.hasNext()) {
//			Buch aktBuch = it.next();
//			if (aktBuch.getTitel().equals(titel)) {
//				// gefundenes Buch in Suchergebnis eintragen
//				suchErg.add(aktBuch);
//			}
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
	 * 			Buch-Objekte. Eigentlich müssten die einzelnen Buch-Objekte
	 * 			auch kopiert werden.
	 *
	 * @return Liste aller Bücher im Buchbestand (Kopie)
	 */
	public List<Buch> getBuchBestand() {
		return new ArrayList<>(buchBestand);
	}
	
	// TODO: Weitere Methoden, z.B. zum Auslesen und Entfernen von Büchern
	// ...
}
