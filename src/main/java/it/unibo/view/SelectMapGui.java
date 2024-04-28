package it.unibo.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * map loading with slider for scrolling.
 */
public class SelectMapGui extends JFrame {
    private JLabel[] imageLabels;
    private int focusIndex;
    private final int numMap = 5;

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
            int index = (focusIndex - 1 + i + numMap) % numMap; // Calculate the index of the image
            imageLabels[i] = new JLabel(new ImageIcon("src/main/resources/map_preview/MAP" + (index + 1) + ".png"));
            imagePanel.add(imageLabels[i]);
        }

        // Initialize the JLabels with the images
        oldGui.add(imagePanel, BorderLayout.CENTER);

        // Button to scroll left
        JButton leftButton = new JButton("<");
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // Update the focusIndex and redraw the images
                focusIndex = (focusIndex - 1 + numMap) % numMap;
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
                focusIndex = (focusIndex + 1) % numMap;
                updateImages();
            }
        });

        // Add the button to scroll right to the frame
        oldGui.add(rightButton, BorderLayout.EAST);

        // Request the container to update the GUI
        oldGui.revalidate();
        oldGui.repaint();
    }

    //Update the image when an arrow is clicked
    private void updateImages() {
        // Calculate the indices of the images
        int[] indices = {
            (focusIndex - 1 + numMap) % numMap,
            focusIndex,
            (focusIndex + 1) % numMap
        };

        // Update the JLabels with the new images
        for (int i = 0; i < 3; i++) {
            imageLabels[i].setIcon(new ImageIcon("src/main/resources/map_preview/MAP" + (indices[i] + 1) + ".png"));
        }
    }
}
