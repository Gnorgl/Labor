package bib.server.net;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;

import bib.common.exceptions.BuchExistiertBereitsException;
import bib.common.interfaces.BibliotheksInterface;
import bib.common.entities.Buch;

/**
 * Klasse zur Verarbeitung der Kommunikation zwischen EINEM Client und dem
 * Server. Die Kommunikation folgt dabei dem "Protokoll" der Anwendung. Das
 * ClientRequestProcessor-Objekt führt folgende Schritte aus: 
 * 0. Begrüßungszeile an den Client senden
 * Danach in einer Schleife:
 * 1. Empfang einer Zeile vom Client (z.B. Aktionsauswahl, hier eingeschränkt); 
 *    wenn der Client die Abbruchaktion sendet ('q'), wird die Schleife verlassen
 * 2. abhängig von ausgewählter Aktion Empfang weiterer Zeilen (Parameter für ausgewählte Aktion)
 * 3. Senden der Antwort an den Client; die Antwort besteht je nach Aktion aus einer oder mehr Zeilen
 * 
 * @author teschke, eirund
 */
class ClientRequestProcessor implements Runnable {

	// Bibliotheksverwaltungsobjekt, das die eigentliche Arbeit machen soll
	private BibliotheksInterface bib; 

	// Datenstrukturen für die Kommunikation
	private Socket clientSocket;
	private BufferedReader in;
	private PrintStream out;

	
	/**
	 * @param socket
	 * @param bib
	 */
	public ClientRequestProcessor(Socket socket, BibliotheksInterface bib) {

		this.bib = bib;
		clientSocket = socket;

		// I/O-Streams initialisieren und ClientRequestProcessor-Objekt als Thread starten:
		try {
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			try {
				clientSocket.close();
			} catch (IOException e2) {
			}
			System.err.println("Ausnahme bei Bereitstellung des Streams: " + e);
			return;
		}

		System.out.println("Verbunden mit " + clientSocket.getInetAddress()
				+ ":" + clientSocket.getPort());
	}

	/**
	 * Methode zur Abwicklung der Kommunikation mit dem Client gemäß dem
	 * vorgebenen Kommunikationsprotokoll.
	 */
	public void run() {

		String input = "";

		// Begrüßungsnachricht an den Client senden
		out.println("Server an Client: Bin bereit für Deine Anfragen!");

		// Hauptschleife zur wiederholten Abwicklung der Kommunikation
		do {
			// Beginn der Benutzerinteraktion:
			// Aktion vom Client einlesen [dann ggf. weitere Daten einlesen ...]
			try {
				input = in.readLine();
			} catch (Exception e) {
				System.out.println("--->Fehler beim Lesen vom Client (Aktion): ");
				System.out.println(e.getMessage());
				continue;
			}

			// Gewünschte Aktion ausführen:
			switch (input) {
				case "a" -> ausgeben();		// Aktion "Bücher _a_usgeben" gewählt
				case "d" -> loeschen();		// Aktion "Buch löschen (_d_elete)" gewählt
				case "e" -> einfuegen();	// Aktion "Buch _e_infügen" gewählt
				case "f" -> suchen();		// Aktion "Bücher _f_inden" (suchen) gewählt
				case "s" -> speichern();	// Aktion "_s_peichern" gewählt
			}

		} while (!(input.equals("q")));

		// Verbindung wurde vom Client abgebrochen:
		disconnect();		
	}

	/*
	 * Implementierung der Aktionen
	 */

	private void ausgeben() {
		// Die Arbeit soll wieder das Bibliotheksverwaltungsobjekt machen:
		List<Buch> buecher = null;
		buecher = bib.gibAlleBuecher();

		sendeBuecherAnClient(buecher);
	}

	private void loeschen() {
		String input = null;
		// lese die notwendigen Parameter, einzeln pro Zeile
		// zuerst die Nummer des einzufügenden Buchs:
		try {
			input = in.readLine();
		} catch (Exception e) {
			System.out.println("--->Fehler beim Lesen vom Client (BuchNr): ");
			System.out.println(e.getMessage());
		}
		int buchNr = Integer.parseInt(input);

		// dann den Titel:
		try {
			input = in.readLine();
		} catch (Exception e) {
			System.out
					.println("--->Fehler beim Lesen vom Client (Titel): ");
			System.out.println(e.getMessage());
		}
		String titel = input;

		// Die eigentliche Arbeit soll das Bibliotheksverwaltungsobjekt machen:
		bib.loescheBuch(titel, buchNr);
		// Rückmeldung an den Client: Aktion erfolgreich!
		out.println("Erfolg");
	}

	private void einfuegen() {
		String input = null;
		// lese die notwendigen Parameter, einzeln pro Zeile
		// zuerst die Nummer des einzufügenden Buchs:
		try {
			input = in.readLine();
		} catch (Exception e) {
			System.out.println("--->Fehler beim Lesen vom Client (BuchNr): ");
			System.out.println(e.getMessage());
		}
		int buchNr = Integer.parseInt(input);

		// dann den Titel:
		try {
			input = in.readLine();
		} catch (Exception e) {
			System.out.println("--->Fehler beim Lesen vom Client (Titel): ");
			System.out.println(e.getMessage());
		}
		String titel = input;

		// Die eigentliche Arbeit soll das Bibliotheksverwaltungsobjekt machen:
		try {
			Buch buch = bib.fuegeBuchEin(titel, buchNr);
			// Rückmeldung an den Client: Aktion erfolgreich!
			out.println("Erfolg");
			sendeBuchAnClient(buch);
		} catch (BuchExistiertBereitsException e) {
			// Rückmeldung an den Client: Fehler!
			out.println("Fehler");
			out.println(e.getMessage());
		}
	}

	private void suchen() {
		String input = null;
		// lese die notwendigen Parameter, einzeln pro Zeile
		// hier ist nur der Titel der gesuchten Bücher erforderlich:
		try {
			input = in.readLine();
		} catch (Exception e) {
			System.out.println("--->Fehler beim Lesen vom Client (Titel): ");
			System.out.println(e.getMessage());
		}

		// Die eigentliche Arbeit soll das Bibliotheksverwaltungsobjekt machen:
		String titel = input;
		List<Buch> buecher = null;
		if (titel.isBlank())
			buecher = bib.gibAlleBuecher();
		else
			buecher = bib.sucheNachTitel(titel);

		sendeBuecherAnClient(buecher);
	}

	private void speichern() {
		// Parameter sind in diesem Fall nicht einzulesen

		// die Arbeit macht wie immer Bibliotheksverwaltungsobjekt:
		try {
			bib.schreibeBuecher();
			out.println("Erfolg");
		} catch (IOException e) {
			System.out.println("--->Fehler beim Sichern: ");
			System.out.println(e.getMessage());
			out.println("Fehler");
		}
	}

	/*
	 * Hilfsmethoden zum Senden der Antworten an den Client
	 */

	private void sendeBuecherAnClient(List<Buch> buecher) {
		// Anzahl der gefundenen Bücher senden
		out.println(buecher.size());
		for (Buch buch: buecher) {
			sendeBuchAnClient(buch);
		}
	}

	private void sendeBuchAnClient(Buch buch) {
		// Nummer des Buchs senden
		out.println(buch.getNummer());
		// Titel des Buchs senden
		out.println(buch.getTitel());
		// Verfügbarkeit des Buchs senden
		out.println(buch.isVerfuegbar());
	}

	private void disconnect() {
		try {
			out.println("Tschuess!");
			clientSocket.close();

			System.out.println("Verbindung zu " + clientSocket.getInetAddress()
					+ ":" + clientSocket.getPort() + " durch Client abgebrochen");
		} catch (Exception e) {
			System.out.println("--->Fehler beim Beenden der Verbindung: ");
			System.out.println(e.getMessage());
			out.println("Fehler");
		}
	}
}
