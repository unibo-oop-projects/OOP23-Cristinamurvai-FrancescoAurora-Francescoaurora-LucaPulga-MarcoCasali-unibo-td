package it.unibo.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Loading Game Screen.
 */
public class GuiGameStart extends JFrame {

    /**
     * Classroom method.
     * @param oldGui JPanel To create the game map
     * @param mapToLoad Map number to load
     */
    public GuiGameStart(final JPanel oldGui, final int mapToLoad) {
        System.out.println("Image clicked: " + mapToLoad);
        // Request the container to update the GUI
        oldGui.revalidate();
        oldGui.repaint();
    }
}
