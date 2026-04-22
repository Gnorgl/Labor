package bib.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import bib.local.domain.exceptions.BuchExistiertBereitsException;
import bib.local.domain.Bibliothek;
import bib.local.entities.Buch;


/**
 * Klasse für sehr einfache Benutzungsschnittstelle für die Bibliothek.
 * Die Benutzungsschnittstelle basiert auf Ein- und Ausgaben auf der Kommandozeile,
 * daher der Name CUI (Command line User Interface).
 * 
 * @author teschke
 * @version 1.2 (Verwaltung der Bücher in Liste mt Generics)
 */
public class BibClientCUI {

	private Bibliothek bib;
	private BufferedReader in;
	
	public BibClientCUI(String datei) throws IOException {
		// die Bib-Verwaltung erledigt die Aufgaben, 
		// die nichts mit Ein-/Ausgabe zu tun haben
		bib = new Bibliothek(datei);

		// Stream-Objekt fuer Texteingabe ueber Konsolenfenster erzeugen
		in = new BufferedReader(new InputStreamReader(System.in));
	}

	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zur Ausgabe des Menüs.
	 */
	private void gibMenueAus() {
		System.out.print("Befehle: \n  Bücher ausgeben:  'a'");
		System.out.print("         \n  Buch löschen: 'd'");
		System.out.print("         \n  Buch einfügen: 'e'");
		System.out.print("         \n  Bücher suchen:  'f'");
		System.out.print("         \n  Daten sichern:  's'");
		System.out.print("         \n  ---------------------");
		System.out.println("         \n  Beenden:        'q'");
		System.out.print("> "); // Prompt
		System.out.flush(); // ohne NL ausgeben
	}

	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zum Einlesen von Benutzereingaben.
	 */
	private String liesEingabe() throws IOException {
		// einlesen von Konsole
		return in.readLine();
	}

	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zur Verarbeitung von Eingaben
	 * und Ausgabe von Ergebnissen.
	 */
	private void verarbeiteEingabe(String line) throws IOException {
		String nummer;
		int nr;
		String titel;
		List<Buch> buecherListe;
		
		// Eingabe bearbeiten:
		switch (line) {
			case "a" -> {
				buecherListe = bib.gibAlleBuecher();
				gibBuecherlisteAus(buecherListe);
			}
			case "d" -> {
				// lies die notwendigen Parameter, einzeln pro Zeile
				System.out.print("Buchnummer > ");
				nummer = liesEingabe();
				nr = Integer.parseInt(nummer);
				System.out.print("Buchtitel  > ");
				titel = liesEingabe();
				bib.loescheBuch(titel, nr);
			}
			case "e" -> {
				// lies die notwendigen Parameter, einzeln pro Zeile
				System.out.print("Buchnummer > ");
				nummer = liesEingabe();
				nr = Integer.parseInt(nummer);
				System.out.print("Buchtitel  > ");
				titel = liesEingabe();

				try {
					bib.fuegeBuchEin(titel, nr);
					System.out.println("Einfügen ok");
				} catch (BuchExistiertBereitsException e) {
					// Hier Fehlerbehandlung...
					System.out.println("Fehler beim Einfügen");
					e.printStackTrace();
				}
			}
			case "f" -> {
				System.out.print("Buchtitel  > ");
				titel = liesEingabe();
				buecherListe = bib.sucheNachTitel(titel);
				gibBuecherlisteAus(buecherListe);
			}
			case "s" -> bib.speichereBuecher();
		}
	}

	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zum Ausgeben von Bücherlisten.
	 *
	 */
	private void gibBuecherlisteAus(List<Buch> liste) {
		if (liste.isEmpty()) {
			System.out.println("Liste ist leer.");
		} else {
			// Durchlaufen einer Liste mit der for-each-Schleife:
			for (Buch buch: liste) {
				System.out.println(buch.toString());
				// Der toString()-Aufruf ist überflüssig, wird bei Ausgaben automatisch aufgerufen
			}
		}
	}

	/**
	 * Methode zur Ausführung der Hauptschleife:
	 * - Menü ausgeben
	 * - Eingabe des Benutzers einlesen
	 * - Eingabe verarbeiten und Ergebnis ausgeben
	 * (EVA-Prinzip: Eingabe-Verarbeitung-Ausgabe)
	 */
	public void run() {
		// Variable für Eingaben von der Konsole
		String input = ""; 
	
		// Hauptschleife der Benutzungsschnittstelle
		do {
			gibMenueAus();
			try {
				input = liesEingabe();
				verarbeiteEingabe(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!input.equals("q"));
	}

	
	/**
	 * Die main-Methode...
	 */
	public static void main(String[] args) {
		BibClientCUI cui;
		try {
			cui = new BibClientCUI("BIB");
			cui.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
