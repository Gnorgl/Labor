package bib.server.domain;

import java.io.IOException;
import java.util.List;

import bib.common.exceptions.BuchExistiertBereitsException;
import bib.common.interfaces.BibliotheksInterface;
import bib.common.entities.Buch;

/**
 * Klasse zur Verwaltung einer (sehr einfachen) Bibliothek.
 * Bietet Methoden zum Zurückgeben aller Bücher im Bestand, 
 * zur Suche nach Büchern, zum Einfügen neuer Bücher 
 * und zum Speichern des Bestands.
 * 
 * @author teschke
 * @version 4 (wie Version 3, nun allerdings mit zusätzlicher disconnect()-
 *            Methode für die Abmeldung von Clients im Netzwerk)
 */
public class Bibliothek implements BibliotheksInterface {

	// Präfix für Namen der Dateien, in der die Bibliotheksdaten gespeichert sind
	private String datei = "";

	private BuecherVerwaltung meineBuecher;
	// private KundenVerwaltung meineKunden;
	// hier weitere Verwaltungsklassen, z.B. für Autoren oder Angestellte

	/**
	 * Konstruktor, der die Basisdaten (Bücher, Kunden, Autoren) aus Dateien einliest
	 * (Initialisierung der Bibliothek).
	 * 
	 * Namensmuster für Dateien:
	 *   datei+"_B.txt" ist die Datei der Bücher
	 *   datei+"_K.txt" ist die Datei der Kunden
	 * 
	 * @param datei Präfix für Dateien mit Basisdaten (Bücher, Kunden, Autoren)
	 * @throws IOException z.B. wenn eine der Dateien nicht existiert.
	 */
	public Bibliothek(String datei) throws IOException {
		this.datei = datei;

		// Buchbestand aus Datei einlesen
		meineBuecher = new BuecherVerwaltung();
		meineBuecher.liesDaten(datei+"_B.txt");

		//			// Kundenkartei aus Datei einlesen
		//			meineKunden = new KundenVerwaltung();
		//			meineKunden.liesDaten(datei+"_K.txt");
		//			meineKunden.schreibeDaten(datei+"_K.txt");
	}


	/**
	 * Methode, die eine Liste aller im Bestand befindlichen Bücher zurückgibt.
	 * 
	 * @return Liste aller Bücher im Bestand der Bibliothek
	 */
	public List<Buch> gibAlleBuecher() {
		// einfach delegieren an meineBuecher
		return meineBuecher.getBuchBestand();
	}

	/**
	 * Methode zum Suchen von Büchern anhand des Titels. Es wird eine Liste von Büchern
	 * zurückgegeben, die alle Bücher mit exakt übereinstimmendem Titel enthält.
	 * 
	 * @param titel Titel des gesuchten Buchs
	 * @return Liste der gefundenen Bücher (evtl. leer)
	 */
	public List<Buch> sucheNachTitel(String titel) {
		// einfach delegieren an meineBuecher
		return meineBuecher.sucheBuecher(titel); 
	}

	/**
	 * Methode zum Einfügen eines neuen Buchs in den Bestand. 
	 * Wenn das Buch bereits im Bestand ist, wird der Bestand nicht geändert.
	 * 
	 * @param titel Titel des Buchs
	 * @param nummer Nummer des Buchs
	 * @returns Buch-Objekt, das im Erfolgsfall eingefügt wurde
	 * @throws BuchExistiertBereitsException wenn das Buch bereits existiert
	 */
	public Buch fuegeBuchEin(String titel, int nummer) throws BuchExistiertBereitsException {
		Buch b = new Buch(titel, nummer);
		meineBuecher.einfuegen(b);
		return b;
	}

	/**
	 * Methode zum Löschen eines Buchs aus dem Bestand. 
	 * Es wird nur das erste Vorkommen des Buchs gelöscht.
	 * 
	 * @param titel Titel des Buchs
	 * @param nummer Nummer des Buchs
	 */
	public void loescheBuch(String titel, int nummer) {
		Buch b = new Buch(titel, nummer);
		meineBuecher.loeschen(b);
	}

	/**
	 * Methode zum Speichern des Buchbestands in einer Datei.
	 * 
	 * @throws IOException z.B. wenn Datei nicht existiert
	 */
	public void schreibeBuecher() throws IOException {
		meineBuecher.schreibeDaten(datei+"_B.txt");
	}

	// TODO: Weitere Funktionen der Bibliotheksverwaltung, z.B. ausleihen, zurückgeben etc.
	// ...

	@Override
	public void disconnect() throws IOException {
		// Hier gibt's nichts zu tun, da Anforderungen eines Verbindungsabbruchs 
		// durch einen Client bereits vom ClientRequestProcessor verarbeitet werden.
	}

	// TODO: Weitere Funktionen der Bibliotheksverwaltung, z.B. ausleihen, zurückgeben etc.
	// ...
}
