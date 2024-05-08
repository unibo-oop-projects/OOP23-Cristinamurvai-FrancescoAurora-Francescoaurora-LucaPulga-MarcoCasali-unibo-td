package it.unibo.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;

import java.awt.event.ActionListener;

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
        oldGui.setLayout(new GridLayout(11, 1)); // Main layout with two rows

        // Sub-panel for the labels ‘Screw and screw image’, ‘Time wave’, ‘Available money’.
        JPanel labelPanel = new JPanel(new GridLayout(1, 3)); // Layout with one row and three columns

        // add label screw
        JLabel labelLife = new JLabel("Vite e immagine vite");
        labelPanel.add(labelLife);

        // add label time
        JLabel labelTime = new JLabel("Tempo ondata");
        labelPanel.add(labelTime);

        // Adding label money
        JLabel labelMoney = new JLabel("Soldi disponibili");
        labelPanel.add(labelMoney);

        // Add the label panel to the main layout
        oldGui.add(labelPanel);



        ActionListener al = e -> { };

        for (int i = 0; i < 10; i++) {
            JPanel buttonPanel = new JPanel(new GridLayout(1, 13)); // I print the row containing 13 columns
            for (int j = 0; j < 13; j++) {
                if (j == 10) {
                    final JPanel empityJPanel = new JPanel();
                    buttonPanel.add(empityJPanel);
                } else {
                    final JButton jb;
                    if (j > 10) {
                        jb = new JButton("torre " + i + " " + j);
                    } else {
                        jb = new JButton("" + i + j);
                    }
                    jb.addActionListener(al);
                    buttonPanel.add(jb);
                }
            }
            oldGui.add(buttonPanel);
        }



        // Request the container to update the GUI
        oldGui.revalidate();
        oldGui.repaint();
    }
}
