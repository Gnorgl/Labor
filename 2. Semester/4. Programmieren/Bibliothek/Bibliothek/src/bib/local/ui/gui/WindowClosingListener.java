package bib.local.ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowClosingListener extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        Window window = e.getWindow();
        int result = JOptionPane.showConfirmDialog(window, "Wollen Sie die Anwendung wirklich schließen?");
        if (result == 0) {
            window.setVisible(false);
            System.exit(0);
        }
    }
}
