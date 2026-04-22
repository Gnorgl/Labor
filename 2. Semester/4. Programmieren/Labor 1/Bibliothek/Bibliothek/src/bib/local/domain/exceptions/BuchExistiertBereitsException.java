package bib.local.domain.exceptions;

import bib.local.entities.Buch;

/**
 * Exception zur Signalisierung, dass ein Buch bereits existiert (z.B. bei einem Einfügevorgang).
 * 
 * @author teschke
 */
public class BuchExistiertBereitsException extends Exception {

	private Buch buch;
	
	/**
	 * Konstruktor
	 * 
	 * @param buch Das bereits existierende Buch
	 * @param zusatzMsg zusätzlicher Text für die Fehlermeldung
	 */
	public BuchExistiertBereitsException(Buch buch, String zusatzMsg) {
		super("Buch mit Titel " + buch.getTitel() + " und Nummer " + buch.getNummer() 
				+ " existiert bereits" + zusatzMsg);
		this.buch = buch;
	}

	public Buch getBuch() {
		return buch;
	}
}
