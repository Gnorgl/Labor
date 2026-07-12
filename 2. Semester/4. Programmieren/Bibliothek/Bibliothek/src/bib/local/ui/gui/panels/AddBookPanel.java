package bib.local.ui.gui.panels;

import bib.local.domain.Bibliothek;
import bib.local.domain.exceptions.BuchExistiertBereitsException;
import bib.local.entities.Buch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Wichtig: Das AddBookPanel _ist ein_ Panel und damit auch eine Component; 
// es kann daher in das Layout eines anderen Containers 
// (in unserer Anwendung des Frames) eingefügt werden.
public class AddBookPanel extends JPanel {

	// Über dieses Interface übermittelt das AddBookPanel
	// ein neu hinzugefügtes Buch an einen Empfänger.
	// In unserem Fall ist der Empfänger die BibGuiMitKomponenten,
	// die dieses Interface implementiert und auf ein neu hinzugefügtes
	// Buch reagiert, indem sie die Bücherliste aktualisiert.	
	public interface AddBookListener {
		void onBookAdded(Buch buch);
	}

	
	private Bibliothek bib = null;
	private AddBookListener addListener = null; 

	private JButton addButton;
	private JTextField numberTextField = null;
	private JTextField titleTextField = null;

	public AddBookPanel(Bibliothek bibliothek, AddBookListener listener) {
		bib = bibliothek;
		addListener = listener;

		setupUI();

		setupEvents();
	}

	private void setupUI() {
		// BoxLayout (vertikal)
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// Abstandhalter ("Filler") zwischen Rand und erstem Element
		Dimension borderMinSize = new Dimension(5, 10);
		Dimension borderPrefSize = new Dimension(5, 10);
		Dimension borderMaxSize = new Dimension(5, 10);
		this.add(
				new Box.Filler(
						borderMinSize,
						borderPrefSize,
						borderMaxSize));

		// Eingabefelder
		this.add(new JLabel("Nummer: "));
		numberTextField = new JTextField();
		this.add(numberTextField);
		this.add(new JLabel("Titel: "));
		titleTextField = new JTextField();
		this.add(titleTextField);

		// Abstandhalter ("Filler") zwischen Eingabefeldern und Button
		// mit Angabe von Minimalgröße, bevorzugter Größe und Maximalgröße
		// (bevorzugte und maximale Größe mit größtmöglicher Höhe, damit
		// sich der Filler bei vertikalem Platz streckt).
		Box.Filler filler = new Box.Filler(
				new Dimension(1, 20),
				new Dimension(1, Short.MAX_VALUE),
				new Dimension(1, Short.MAX_VALUE));
		this.add(filler);

		// Button
		addButton = new JButton("Hinzufügen");
		this.add(addButton);

		// Abstandhalter ("Filler") zwischen letztem Element und Rand
		this.add(new Box.Filler(borderMinSize, borderPrefSize, borderMaxSize));

		// Umrandung
		this.setBorder(BorderFactory.createTitledBorder("Einfügen"));
	}

	private void setupEvents() {
		addButton.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ae) {
						System.out.println("Event: " + ae.getActionCommand());
						onAddButtonClick();
					}
				});
//		// Alternative: Lambda Expression
//		addButton.addActionListener(event -> onAddButtonClick());
	}

	private void onAddButtonClick() {
		String nummer = numberTextField.getText();
		String titel = titleTextField.getText();

		if (!nummer.isBlank() && !titel.isBlank()) {
			try {
				int nummerAlsInt = Integer.parseInt(nummer);
				// Buch in Bibliothek einfügen
				Buch buch = bib.fuegeBuchEin(titel, nummerAlsInt);
				// Felder zurücksetzen
				numberTextField.setText("");
				titleTextField.setText("");

				// Am Ende Listener, d.h. unseren Frame benachrichtigen:
				addListener.onBookAdded(buch);
			} catch (NumberFormatException nfe) {
				// Dialog mit Fehlermeldung öffnen
				JOptionPane.showMessageDialog(this, "Bitte eine Zahl eingeben.");
			} catch (BuchExistiertBereitsException bebe) {
				// Dialog mit Fehlermeldung öffnen
				JOptionPane.showMessageDialog(this, bebe.getMessage());
			}
		}
	}
}
