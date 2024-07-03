package it.unibo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.model.core.GameEngineImpl;
import it.unibo.model.utilities.ScaledImage;

/**
 * Load the game gui.
 */
public class GuiStart extends JFrame {

    private static final long serialVersionUID = 1L;
    private final transient Logger logger = LoggerFactory.getLogger(GameEngineImpl.class);
    private final JLabel startButton;
    private final JPanel contentPanel; // Declared as a class field to make it accessible from other methods
    private SelectMapGui selectMapGui; // Store reference to SelectMapGui instance
    private static final int PROP_BUTTON = 3; // Set custom dimensions for the button
    private transient Image icon;

    /**
     * Load the game gui.
     */
    public GuiStart() {

        // Set the title of the frame
        super("Unibo TD");

        // Set the close operation of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create an empty panel
        contentPanel = new JPanel();

        // Set the layout of the empty panel to GridBagLayout
        contentPanel.setLayout(new GridBagLayout());

        // Create a GridBagConstraints to configure the positioning of the button
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Column position
        gbc.gridy = 0; // Row position
        gbc.insets = new Insets(10, 10, 10, 10); // External margin

        // Set the layout of the frame
        this.setLayout(new BorderLayout());

        // Fit the frame to the screen dimensions
        this.setExtendedState(MAXIMIZED_BOTH);

        // Add the empty panel to the frame
        this.add(contentPanel, BorderLayout.CENTER);

        // Create the start game button
        startButton = new JLabel();
        // Add an ActionListener for the button
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                // Remove the "Start" button
                contentPanel.removeAll();

                // Initialize SelectMapGui if not already instantiated
                if (selectMapGui == null) {
                    selectMapGui = new SelectMapGui(contentPanel, GuiStart.this);
                } else {
                    // Ensure SelectMapGui is visible if it's already instantiated
                    selectMapGui.setVisible(true);
                }
            }
        });

        try {
            icon = ImageIO.read(ClassLoader.getSystemResource("buttons/Play.png"));
        } catch (IOException e) {
            logger.error("Error when retrieving buttons/Play.png\"", e);
        }

        // Make the frame visible
        this.setVisible(true);

        final int initialDimension = calculateDimensions();

        startButton.setPreferredSize(new Dimension(initialDimension, initialDimension));
        // Set the alignment of the button to center
        gbc.anchor = GridBagConstraints.CENTER;

        // Add the button to the panel with GridBagConstraints specifications
        contentPanel.add(startButton, gbc);
        startButton.setIcon(ScaledImage.getScaledImage(icon, calculateDimensions(), calculateDimensions()));
        final ComponentAdapter resize = new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                resizeImages(startButton);
            }
        };
        this.contentPanel.addComponentListener(resize);

        // Request the container to update the GUI
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * resize image present in label.
     *
     * @param panel panel containing the image
     */
    private void resizeImages(final JLabel panel) {
        final int newDimension = calculateDimensions();
        panel.setPreferredSize(new Dimension(newDimension, newDimension));
        panel.setIcon(ScaledImage.getScaledImage(icon, newDimension, newDimension));
    }

    /**
     * Calculates the size of the image.
     *
     * @return image size
     */
    private int calculateDimensions() {
        final Dimension size = super.getSize(); // Use super.getSize() to avoid overwritinga
        if (size.width < size.height) {
            return size.width / PROP_BUTTON;
        }
        return size.height / PROP_BUTTON;
    }

    /**
     * Closes the game.
     */
    public void closeGame() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
