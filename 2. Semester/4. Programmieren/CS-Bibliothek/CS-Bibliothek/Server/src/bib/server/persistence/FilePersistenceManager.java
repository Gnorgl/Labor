package bib.server.persistence;

import java.io.*;

import bib.common.entities.Buch;

/**
 * @author teschke
 *
 * Realisierung einer Schnittstelle zur persistenten Speicherung von
 * Daten in Dateien.
 * @see bib.server.persistence.PersistenceManager
 */
public class FilePersistenceManager implements PersistenceManager {

	private BufferedReader reader = null;
	private PrintWriter writer = null;
	
	public void openForReading(String datei) throws FileNotFoundException {
		// Lädt Datei nur dann, wenn sie im selben Verzeichnis liegt wie
		// der src-Ordner des _Projekts_:
		reader = new BufferedReader(new FileReader(datei));

		// Das ist hier eigentlich unpassend, weil 'Server' ja nur ein Modul
		// des übergeordneten Projekts ist. Die Alternative, die Daten aus
		// einem Ressourcen-Verzeichnis des Moduls zu laden, hat das Problem,
		// das dort nicht gespeichert werden kann.
		// Lädt Datei aus Ressourcen-Ordner des Moduls:
		//		ClassLoader classloader = FilePersistenceManager.class.getClassLoader();
		//		InputStream is = classloader.getResourceAsStream(datei);
		//		reader = new BufferedReader(new InputStreamReader(is));
	}

	public void openForWriting(String datei) throws IOException {
		writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));
	}

	public boolean close() {
		if (writer != null)
			writer.close();
		
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return false;
			}
		}

		return true;
	}

	/**
	 * Methode zum Einlesen der Buchdaten aus einer externen Datenquelle.
	 * Das Verfügbarkeitsattribut ist in der Datenquelle (Datei) als "t" oder "f"
	 * codiert abgelegt.
	 * 
	 * @return Buch-Objekt, wenn Einlesen erfolgreich, false null
	 */
	public Buch ladeBuch() throws IOException {
		// Titel einlesen
		String titel = liesZeile();
		if (titel == null) {
			// keine Daten mehr vorhanden
			return null;
		}
		// Nummer einlesen ...
		String nummerString = liesZeile();
		// ... und von String in int konvertieren
		int nummer = Integer.parseInt(nummerString);
		
		// Buch ausgeliehen?
		String verfuegbarCode = liesZeile();
		// Codierung des Ausleihstatus in boolean umwandeln
		boolean verfuegbar = false;
		if (verfuegbarCode.equals("t"))
			verfuegbar = true;
		
		// neues Buch-Objekt anlegen und zur�ckgeben
		return new Buch(titel, nummer, verfuegbar);
	}

	/**
	 * Methode zum Schreiben der Buchdaten in eine externe Datenquelle.
	 * Das Verfügbarkeitsattribut wird in der Datenquelle (Datei) als "t" oder "f"
	 * codiert abgelegt.
	 * 
	 * @param b Buch-Objekt, das gespeichert werden soll
	 * @return true, wenn Schreibvorgang erfolgreich, false sonst
	 */
	public boolean speichereBuch(Buch b) throws IOException {
		// Titel, Nummer und Verf�gbarkeit schreiben
		schreibeZeile(b.getTitel());
		schreibeZeile(Integer.valueOf(b.getNummer()).toString());
		if (b.isVerfuegbar())
			schreibeZeile("t");
		else
			schreibeZeile("f");

		return true;
	}

	/*
	 *  Wenn später mal eine Kundenverwaltung ergänzt wird:

	public Kunde ladeKunde() throws IOException {
		// TODO: Implementieren
		return null;
	}

	public boolean speichereKunde(Kunde k) throws IOException {
		// TODO: Implementieren
		return false;
	}

	*/
	
	/*
	 * Private Hilfsmethoden
	 */
	
	private String liesZeile() throws IOException {
		if (reader != null)
			return reader.readLine();
		else
			return "";
	}

	private void schreibeZeile(String daten) {
		if (writer != null)
			writer.println(daten);
	}
}
