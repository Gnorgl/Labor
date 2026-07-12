package bib.local.ui.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Listener zu Demo-Zwecken
// Ermöglicht die Beobachtung von MouseEvents mit Konsolenausgaben:
// - Mausklicks
// - Betreten und Verlassen einer Komponente
public class MouseEventListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked an Position [ " + e.getX() + ", " + e.getY() + " ]");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mousePressed an Position [ " + e.getX() + ", " + e.getY() + " ]");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased an Position [ " + e.getX() + ", " + e.getY() + " ]");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered an Position [ " + e.getX() + ", " + e.getY() + " ]");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited an Position [ " + e.getX() + ", " + e.getY() + " ]");
    }
}
