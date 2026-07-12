package bib.client.ui.gui.swing.panels;

import bib.common.exceptions.BuchExistiertBereitsException;
import bib.common.interfaces.BibliotheksInterface;
import bib.common.entities.Buch;

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
	// die dieses Interface implementiert und auf ein neue hinzugefügtes
	// Buch reagiert, indem sie die Bücherliste aktualisiert.
	public interface AddBookListener {
		public void onBookAdded(Buch buch);
	}


	private BibliotheksInterface bib = null;
	private AddBookListener addListener = null;

	private JButton addButton;
	private JTextField numberTextField = null;
	private JTextField titleTextField = null;

	public AddBookPanel(BibliotheksInterface bibliothek, AddBookListener listener) {
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
		this.add(new Box.Filler(borderMinSize, borderPrefSize, borderMaxSize));

		// Eingabefelder
		this.add(new JLabel("Nummer: "));
		numberTextField = new JTextField();
		this.add(numberTextField);
		this.add(new JLabel("Titel: "));
		titleTextField = new JTextField();
		this.add(titleTextField);

		// Abstandhalter ("Filler") zwischen Eingabefeldern und Button
		Dimension minSize = new Dimension(1, 20);
		Dimension prefSize = new Dimension(1, Short.MAX_VALUE);
		Dimension maxSize = new Dimension(1, Short.MAX_VALUE);
		Box.Filler filler = new Box.Filler(minSize, prefSize, maxSize);
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
//		addButton.addActionListener(e -> buchEinfügen());
	}

	private void onAddButtonClick() {
		String nummer = numberTextField.getText();
		String titel = titleTextField.getText();

		if (!nummer.isEmpty() && !titel.isEmpty()) {
			try {
				int nummerAlsInt = Integer.parseInt(nummer);
				Buch buch = bib.fuegeBuchEin(titel, nummerAlsInt);
				numberTextField.setText("");
				titleTextField.setText("");

				// Am Ende Listener, d.h. unseren Frame benachrichtigen:
				addListener.onBookAdded(buch);
			} catch (NumberFormatException nfe) {
				System.err.println("Bitte eine Zahl eingeben.");
			} catch (BuchExistiertBereitsException bebe) {
				System.err.println(bebe.getMessage());
			}
		}
	}
}