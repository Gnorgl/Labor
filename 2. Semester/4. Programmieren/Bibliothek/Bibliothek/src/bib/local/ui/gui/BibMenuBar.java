package bib.local.ui.gui;

import bib.local.domain.Bibliothek;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BibMenuBar extends JMenuBar {

    private Bibliothek bib;

    public BibMenuBar(Bibliothek bib) {
        this.bib = bib;

        add(new FileMenu());
        add(new HelpMenu());
    }

    /*
     * (non-Javadoc)
     *
     * Mitgliedsklasse für File-Menü
     *
     */
    class FileMenu extends JMenu implements ActionListener {

        public FileMenu() {
            super("File");

            JMenuItem saveItem = new JMenuItem("Save");
            saveItem.addActionListener(this);
            add(saveItem);
            addSeparator();
            JMenuItem quitItem = new JMenuItem("Quit");
            quitItem.addActionListener(this);
            add(quitItem);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()) {
                case "Save" -> {
                    try {
                        bib.speichereBuecher();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                case "Quit" -> System.exit(0);
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * Mitgliedsklasse für Help-Menü
     *
     */
    class HelpMenu extends JMenu implements ActionListener {

        public HelpMenu() {
            super("Help");

            // Nur zu Testzwecken: Menü mit Untermenü
            JMenu m = new JMenu("About");
            JMenuItem mi = new JMenuItem("Programmers");
            mi.addActionListener(this);
            m.add(mi);
            mi = new JMenuItem("Stuff");
            mi.addActionListener(this);
            m.add(mi);
            this.add(m);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Klick auf Menü '" + e.getActionCommand() + "'.");
        }
    }
}
