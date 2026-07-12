
package bib.client.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.List;

import bib.client.net.BibliotheksFassade;
import bib.common.exceptions.BuchExistiertBereitsException;
import bib.common.interfaces.BibliotheksInterface;
import bib.common.entities.Buch;

/**
 * Klasse für sehr einfache Benutzungsschnittstelle für die
 * Bibliothek. Die Benutzungsschnittstelle basiert auf Ein-
 * und Ausgaben auf der Kommandozeile, daher der Name CUI
 * (Command line User Interface).
 * 
 * @author teschke
 * @version 4 (Netzwerk-Client: 
 *             Änderungen nur am Konstruktor und an main()-Methode)
 */
public class BibClientCUI {

	public static final int DEFAULT_PORT = 6789;

	private BibliotheksInterface bib;
	private BufferedReader in;
	
	
	public BibClientCUI(String host, int port) throws IOException {
		// die Bib-Verwaltung auf Server erledigt die Aufgaben, 
		// die nichts mit Ein-/Ausgabe zu tun haben.
		// Die hier eingesetzte Fassade entkoppelt den Client
		// von der erforderlichen Netzwerkkommunikation
		bib = new BibliotheksFassade(host, port);
		
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
			case "e"  -> {
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
			case "s" -> bib.schreibeBuecher();
		}
	}

	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zum Ausgeben von Bücherlisten.
	 *
	 */
	private void gibBuecherlisteAus(List<Buch> liste) {
		if (liste != null) {
			if (liste.isEmpty()) {
				System.out.println("Liste ist leer.");
			} else {
				for (Buch buch: liste) {
					System.out.println(buch);
				}
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
		int port = 0;
		String host = null;
		InetAddress ia = null;

		// Host- und Port-Argumente einlesen, wenn angegeben
		if (args.length > 2) {
			System.out.println("Aufruf: java BibClientGUI [<hostname> [<port>]]");
			System.exit(0);
		}
		switch (args.length) {
			case 0 -> {
				try {
					ia = InetAddress.getLocalHost();
				} catch (Exception e) {
					System.out.println("XXX InetAdress-Fehler: " + e);
					System.exit(0);
				}
				host = ia.getHostName(); // host ist lokale Maschine
				port = DEFAULT_PORT;
			}
			case 1 -> {
				port = DEFAULT_PORT;
				host = args[0];
			}
			case 2 -> {
				host = args[0];
				try {
					port = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					System.out
							.println("Aufruf: java BibClientGUI [<hostname> [<port>]]");
					System.exit(0);
				}
			}
		}
		
		// CUI auf Starten und mit Server auf Host und Port verbinden
		BibClientCUI cui;
		try {
			cui = new BibClientCUI(host, port);
			cui.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
