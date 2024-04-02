package it.unibo.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JButton startButton;
    private JPanel contentPane; // Declared as a class field to make it accessible from other methods

    public GUI (){
        // Set the title of the frame
        super("Proggetto oop - Sostituire con nome gioco");

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
            
            new SelectMapGui(contentPane);
        };
        
        // Create the start game button
        startButton = new JButton("Start");
 
        // Add an ActionListener for the button
        startButton.addActionListener(startGameListen);

        // Set custom dimensions for the button
        startButton.setPreferredSize(new Dimension(100, 50));

        // Set the alignment of the button to center
        gbc.anchor = GridBagConstraints.CENTER;

        // Add the button to the panel with GridBagConstraints specifications
        contentPane.add(startButton, gbc);

        // Set the layout of the frame
        this.getContentPane().setLayout(new BorderLayout());

        // Fit the frame to the screen dimensions
        this.setExtendedState(MAXIMIZED_BOTH);

        // Add the empty panel to the frame
        this.getContentPane().add(contentPane, BorderLayout.CENTER);

        // Make the frame visible
        this.setVisible(true);
    }
}
