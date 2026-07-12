package bib.common.interfaces;

import java.io.IOException;
import java.util.List;

import bib.common.exceptions.BuchExistiertBereitsException;
import bib.common.entities.Buch;

public interface BibliotheksInterface {

	/**
	 * Methode, die eine Liste aller im Bestand befindlichen Bücher zurückgibt.
	 * 
	 * @return Liste aller Bücher im Bestand der Bibliothek
	 */
	List<Buch> gibAlleBuecher();

	/**
	 * Methode zum Suchen von Büchern anhand des Titels. Es wird eine Liste von Büchern
	 * zurückgegeben, die alle Bücher mit exakt übereinstimmendem Titel enthält.
	 * 
	 * @param titel Titel des gesuchten Buchs
	 * @return Liste der gefundenen Bücher (evtl. leer)
	 */
	List<Buch> sucheNachTitel(String titel);

	/**
	 * Methode zum Einfügen eines neuen Buchs in den Bestand. 
	 * Wenn das Buch bereits im Bestand ist, wird der Bestand nicht geändert.
	 * 
	 * @param titel Titel des Buchs
	 * @param nummer Nummer des Buchs
	 * @returns Buch-Objekt, das im Erfolgsfall eingefügt wurde
	 * @throws BuchExistiertBereitsException wenn das Buch bereits existiert
	 */
	Buch fuegeBuchEin(String titel, int nummer) throws BuchExistiertBereitsException;

	/**
	 * Methode zum Löschen eines Buchs aus dem Bestand. 
	 * Es wird nur das erste Vorkommen des Buchs gelöscht.
	 * 
	 * @param titel Titel des Buchs
	 * @param nummer Nummer des Buchs
	 */
	void loescheBuch(String titel, int nummer);
	
	/**
	 * Methode zum Speichern des Buchbestands in einer Datei.
	 * 
	 * @throws IOException
	 */
	void schreibeBuecher() throws IOException;

	/**
	 * Methode zum Beenden einer Verbindung zum Server.
	 * 
	 * @throws IOException
	 */
	void disconnect() throws IOException;
}
