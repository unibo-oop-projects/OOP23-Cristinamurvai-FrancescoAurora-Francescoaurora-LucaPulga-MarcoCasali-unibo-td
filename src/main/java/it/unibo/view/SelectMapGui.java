package it.unibo.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

/**
 * map loading with slider for scrolling.
 */
public class SelectMapGui extends JFrame {
    private JLabel[] imageLabels;
    private int focusIndex;
    private static final int NUM_MAP = 5;
    private JPanel guiMapSelected;

    /**
     * @param oldGui switching the gui panel of the old window
     */
    public SelectMapGui(final JPanel oldGui) {

        // Set the layout of the contentPane to BorderLayout
        oldGui.setLayout(new BorderLayout());

        focusIndex = 0; // Index of the central image

        // Panel to contain the images
        JPanel imagePanel = new JPanel(new GridLayout(1, 3));

        // Initialize the array of JLabels
        imageLabels = new JLabel[3];

        // Initialize the JLabels with the images
        for (int i = 0; i < 3; i++) {
            int index = (focusIndex - 1 + i + NUM_MAP) % NUM_MAP; // Calculate the index of the image
            imageLabels[i] = new JLabel(new ImageIcon("src/main/resources/map_preview/MAP" + (index + 1) + ".png"));
            imageLabels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    //change Gui for start Game
                    changeGui(index + 1);

                } });
            imagePanel.add(imageLabels[i]);
        }

        // Initialize the JLabels with the images
        oldGui.add(imagePanel, BorderLayout.CENTER);

        // Button to scroll left
        JButton leftButton = new JButton("<");
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // Update the focusIndex and redraw the images
                focusIndex = (focusIndex - 1 + NUM_MAP) % NUM_MAP;
                updateImages();
            }
        });

        // Add the button to scroll left to the frame
        oldGui.add(leftButton, BorderLayout.WEST);

        // Button to scroll right
        JButton rightButton = new JButton(">");
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // Update the focusIndex and redraw the images
                focusIndex = (focusIndex + 1) % NUM_MAP;
                updateImages();
            }
        });

        // Add the button to scroll right to the frame
        oldGui.add(rightButton, BorderLayout.EAST);

        // Request the container to update the GUI
        oldGui.revalidate();
        oldGui.repaint();

        guiMapSelected = oldGui;
    }

    //Update the image when an arrow is clicked
    private void updateImages() {
        // Calculate the indices of the images
        int[] indices = {
            (focusIndex - 1 + NUM_MAP) % NUM_MAP,
            focusIndex,
            (focusIndex + 1) % NUM_MAP
        };

        // Update the JLabels with the new images
        for (int i = 0; i < 3; i++) {
            final int tmp = indices[i] + 1;
            //imageLabels[i] = new JLabel();
            imageLabels[i].removeMouseListener(imageLabels[i].getMouseListeners()[0]);
            imageLabels[i].setIcon(new ImageIcon("src/main/resources/map_preview/MAP" + (tmp) + ".png"));
            imageLabels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    //change Gui for start Game
                    changeGui(tmp);
                } });
        }
    }

    /**
     * Change gui with map selected and start game.
     * @param mapSelected Number of Map to Selected
     */
    public void changeGui(final int mapSelected) {
        // Remove the "Map"
        guiMapSelected.removeAll();
        //Change Gui for start game
        new GuiGameStart(guiMapSelected, mapSelected);
    }
}
