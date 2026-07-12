package bib.common.exceptions;

import bib.common.entities.Buch;

/**
 * Exception zur Signalisierung, dass ein Buch bereits existiert (z.B. bei einem Einfügevorgang).
 * 
 * @author teschke
 */
public class BuchExistiertBereitsException extends Exception {

	/**
	 * Konstruktor
	 * 
	 * @param buch Das bereits existierende Buch
	 * @param zusatzMsg zusätzlicher Text für die Fehlermeldung
	 */
	public BuchExistiertBereitsException(Buch buch, String zusatzMsg) {
		super("Buch mit Titel " + buch.getTitel() + " und Nummer " + buch.getNummer() 
				+ " existiert bereits" + zusatzMsg);
	}

	/**
	 * _Zusätzlicher_ Konstruktor für Rekonstruktion von Exception aus Socket-Verbindung
	 * (Exceptions können über Text-Streams nicht als Objekte übertragen werden.)
	 * 
	 * @param message Die Fehlermeldung
	 */
	public BuchExistiertBereitsException(String message) {
		super(message);
	}
}
