package bib.local.entities;


/**
 * Klasse zur Repräsentation einzelner Bücher.
 * 
 * @author teschke
 */
public class Buch {

	// Attribute zur Beschreibung eines Buchs:
	private String titel;
	private int nummer;
	private boolean verfuegbar; 
	
	
	public Buch(String titel, int nr) {
		this(titel, nr, true);
	}

	public Buch(String titel, int nr, boolean verfuegbar) {
		nummer = nr;
		this.titel = titel;
		this.verfuegbar = verfuegbar;
	}
	
	// --- Dienste der Buch-Objekte ---

	/**
	 * Standard-Methode von Object überschrieben.
	 * Methode wird immer automatisch aufgerufen, wenn ein Buch-Objekt als String
	 * benutzt wird (z.B. in println(buch);)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String verfuegbarkeit = verfuegbar ? "verfuegbar" : "ausgeliehen";
		return ("Nr: " + nummer + " / Titel: " + titel + " / " + verfuegbarkeit);
	}

	/**
	 * Standard-Methode von Object überschrieben.
	 * Methode dient Vergleich von zwei Buch-Objekten anhand ihrer Werte,
	 * d.h. Titel und Nummer.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public boolean equals(Object anderesBuch) {
		if (anderesBuch instanceof Buch) 
			return ((this.nummer == ((Buch) anderesBuch).nummer) 
					&& (this.titel.equals(((Buch) anderesBuch).titel)));
		else
			return false;
	}

	
	/*
	 * Ab hier Accessor-Methoden
	 */
	
	public int getNummer() {
		return nummer;
	}

	public String getTitel() {
		return titel;
	}

	public boolean isVerfuegbar() {
		return verfuegbar;
	}
}
