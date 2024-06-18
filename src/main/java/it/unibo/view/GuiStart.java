package it.unibo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Load the game gui.
 */
public class GuiStart extends JFrame {
    private final JButton startButton;
    private JPanel contentPane; // Declared as a class field to make it accessible from other methods
    private SelectMapGui selectMapGui; // Store reference to SelectMapGui instance

    /**
     * Load the game gui.
     */
    public GuiStart() {
        // Set the title of the frame
        super("Unibo TD");

        // Set the close operation of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an empty panel
        contentPane = new JPanel();

        // Set the layout of the empty panel to GridBagLayout
        contentPane.setLayout(new GridBagLayout());

        // Create a GridBagConstraints to configure the positioning of the button
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Column position
        gbc.gridy = 0; // Row position
        gbc.insets = new Insets(10, 10, 10, 10); // External margin

        ActionListener startGameListen = e -> {
            // Remove the "Start" button
            contentPane.removeAll();

            // Initialize SelectMapGui if not already instantiated
            if (selectMapGui == null) {
                selectMapGui = new SelectMapGui(contentPane);
            } else {
                // Ensure SelectMapGui is visible if it's already instantiated
                selectMapGui.setVisible(true);
            }
        };

        // Create the start game button
        startButton = new JButton("Start");

        // Add an ActionListener for the button
        startButton.addActionListener(startGameListen);

        // Set custom dimensions for the button
        final int heightButton = 50, widthButton = 100;
        startButton.setPreferredSize(new Dimension(widthButton, heightButton));

        // Set the alignment of the button to center
        gbc.anchor = GridBagConstraints.CENTER;

        // Add the button to the panel with GridBagConstraints specifications
        contentPane.add(startButton, gbc);

        // Set the layout of the frame
        this.setLayout(new BorderLayout());

        // Fit the frame to the screen dimensions
        this.setExtendedState(MAXIMIZED_BOTH);

        // Add the empty panel to the frame
        this.add(contentPane, BorderLayout.CENTER);

        // Make the frame visible
        this.setVisible(true);
    }
}
