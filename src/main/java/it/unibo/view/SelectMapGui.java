package it.unibo.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unibo.controller.GameControllerImpl;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * map loading with slider for scrolling.
 */
public class SelectMapGui extends JFrame {
    private JLabel[] imageLabels;
    private int focusIndex;
    private final List<String> maps = new GameControllerImpl().getAvailableMaps();

    /**
     * @param oldGui switching the gui panel of the old window
     */
    public SelectMapGui(final JPanel oldGui) {

        // Set the layout of the contentPane to BorderLayout
        oldGui.setLayout(new BorderLayout());

        focusIndex = 0; // Index of the central image

        // Panel to contain the images
        JPanel imagePanel = new JPanel(new FlowLayout());

        // Initialize the array of JLabels
        imageLabels = new JLabel[3];

        // Initialize the JLabels with the images
        for (int i = 0; i < 3; i++) {
            int index = i % maps.size(); // Calculate the index of the image
            imageLabels[i] = new JLabel(new ImageIcon(ClassLoader.getSystemResource("map_preview/" + maps.get(index) + ".png")));
            imageLabels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    //change Gui for start Game
                    changeGui(maps.get(index));

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
                focusIndex = (focusIndex - 1 + maps.size()) % maps.size();
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
                focusIndex = (focusIndex + 1) % maps.size();
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
            (focusIndex - 1 + maps.size()) % maps.size(),
            focusIndex,
            (focusIndex + 1) % maps.size()
        };

        // Update the JLabels with the new images
        for (int i = 0; i < 3; i++) {
            final int tmp = indices[i];
            //imageLabels[i] = new JLabel();
            imageLabels[i].removeMouseListener(imageLabels[i].getMouseListeners()[0]);
            imageLabels[i].setIcon(new ImageIcon(ClassLoader.getSystemResource("map_preview/" + maps.get(tmp) + ".png")));
            imageLabels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    //change Gui for start Game
                    changeGui(maps.get(tmp));
                } });
        }
    }

    /**
     * Change gui with map selected and start game.
     * @param mapSelected Selected map name
     */
    public void changeGui(final String mapSelected) {
        new GuiGameStart(mapSelected);
    }
}
