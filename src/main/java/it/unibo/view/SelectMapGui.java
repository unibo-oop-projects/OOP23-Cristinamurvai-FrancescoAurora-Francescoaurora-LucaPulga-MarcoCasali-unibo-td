package it.unibo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.controller.GameControllerImpl;
import it.unibo.model.core.GameEngineImpl;
import it.unibo.model.utilities.ScaledImage;

/**
 * map loading with slider for scrolling.
 */
public class SelectMapGui extends JFrame {

    private final transient Logger logger = LoggerFactory.getLogger(GameEngineImpl.class);
    private static final long serialVersionUID = 1L;
    private final JLabel imageLabels;
    private int focusIndex;
    private final List<String> maps = new GameControllerImpl().getAvailableMaps();
    private GuiGameStart guiGameStart;
    /*private final JLabel leftButton;
    private final JLabel rightButton;
    private transient Image left;
    private transient Image right;*/
    private static final int DIMENSION_BUTTONS = 100;
    private static final int SPACE_TEXT = 25;
    private final JLabel textLabel;

    /**
     * Constructor.
     *
     * @param oldGui switching the gui panel of the old window
     * @param guiStart for exit from game
     */
    public SelectMapGui(final JPanel oldGui, final GuiStart guiStart) {

        oldGui.setLayout(new BorderLayout());

        focusIndex = 0; // Index of the central image

        // Button to scroll left
        final JLabel leftButton = new JLabel();
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                // Update the focusIndex and redraw the images
                focusIndex = (focusIndex - 1 + maps.size()) % maps.size();
                updateImages(oldGui, guiStart);
            }
        });

        Image left = null;
        try {
            left = ImageIO.read(ClassLoader.getSystemResource("buttons/left.png"));
        } catch (IOException e) {
            logger.error("error when retrieving buttons/left.png", e);
        }

        leftButton.setPreferredSize(new Dimension(DIMENSION_BUTTONS, DIMENSION_BUTTONS));
        leftButton.setIcon(ScaledImage.getScaledImage(left, DIMENSION_BUTTONS, DIMENSION_BUTTONS));

        // Add the button to scroll left to the frame
        oldGui.add(leftButton, BorderLayout.WEST);

        // Button to scroll right
        final JLabel rightButton = new JLabel();
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                // Update the focusIndex and redraw the images
                focusIndex = (focusIndex + 1) % maps.size();
                updateImages(oldGui, guiStart);
            }
        });

        Image right = null;
        try {
            right = ImageIO.read(ClassLoader.getSystemResource("buttons/right.png"));
        } catch (IOException e) {
            logger.error("error when retrieving buttons/right.png", e);
        }

        rightButton.setPreferredSize(new Dimension(DIMENSION_BUTTONS, DIMENSION_BUTTONS));
        rightButton.setIcon(ScaledImage.getScaledImage(right, DIMENSION_BUTTONS, DIMENSION_BUTTONS));

        // Add the button to scroll right to the frame
        oldGui.add(rightButton, BorderLayout.EAST);
        textLabel = new JLabel("Select the map for starting the game. This is " + maps.get(this.focusIndex));
        textLabel.setSize(textLabel.getWidth(), SPACE_TEXT);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        oldGui.add(textLabel, BorderLayout.NORTH);

        // Panel to contain the images
        final JPanel imagePanel = new JPanel(new FlowLayout());

        // Initialize the array of JLabels
        this.imageLabels = new JLabel();
        imageLabels.setHorizontalAlignment(SwingConstants.CENTER);
        //stringAndImage.add(imageLabels,BorderLayout.CENTER);
        // Initialize the JLabels with the images
        oldGui.add(imagePanel, BorderLayout.CENTER);

        // Initialize the JLabels with the images
        imageLabels.setSize(oldGui.getWidth(), oldGui.getHeight());
        imageLabels.setIcon(new ImageIcon(ClassLoader.getSystemResource("map_preview/" + maps.get(this.focusIndex) + ".png")));
        imageLabels.setIcon(getScalated(maps.get(focusIndex), oldGui));
        imageLabels.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                // Change GUI for starting game
                changeGui(maps.get(focusIndex), oldGui, guiStart);
            }
        });

        final ComponentAdapter resize = new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                imageLabels.setIcon(getScalated(maps.get(focusIndex), oldGui));
            }
        };
        imagePanel.addComponentListener(resize);

        imagePanel.add(imageLabels);

        // Request the container to update the GUI
        oldGui.revalidate();
        oldGui.repaint();

        // Request the container to update the GUI
        oldGui.revalidate();
        oldGui.repaint();
    }

    /**
     * Update the image when an arrow is clicked.
     *
     * @param oldGui for image calculation
     * @param guiStart for exit from game
     */
    private void updateImages(final JPanel oldGui, final GuiStart guiStart) {
        textLabel.setText("Select the map for starting the game. This is " + maps.get(this.focusIndex));
        // Remove previous mouse listeners
        for (final MouseListener adapter : imageLabels.getMouseListeners()) {
            imageLabels.removeMouseListener(adapter);
        }
        imageLabels.setIcon(getScalated(maps.get(focusIndex), oldGui));
        imageLabels.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                // Change GUI for starting game
                changeGui(maps.get(focusIndex), oldGui, guiStart);
            }
        });

    }

    /**
     * Change gui with map selected and start game.
     *
     * @param mapSelected Selected map name
     * @param oldGui panel to pass
     * @param guiStart for exit from game
     */
    public void changeGui(final String mapSelected, final JPanel oldGui, final GuiStart guiStart) {
        oldGui.removeAll();
        if (guiGameStart == null) {
            guiGameStart = new GuiGameStart(mapSelected, oldGui, guiStart);
        } else {
            // Ensure SelectMapGui is visible if it's already instantiated
            guiGameStart.setVisible(true);
        }
    }

    /**
     * Scale the image by fitting it to the smaller side.
     *
     * @param image image to adapt
     * @param oldGui window for size calculation
     * @return the adapted image
     */
    private ImageIcon getScalated(final String image, final JPanel oldGui) {
        BufferedImage icon;
        try {
            icon = ImageIO.read(ClassLoader.getSystemResource("map_preview/" + image + ".png"));
        } catch (IOException e) {
            logger.error("error when retrieving map_preview/" + image + ".png", e);
            return null;
        }

        final int widthWitchButton = oldGui.getWidth() - (DIMENSION_BUTTONS * 2);
        final int heightWitchText = oldGui.getHeight() - SPACE_TEXT;

        if (heightWitchText <= 0 || widthWitchButton <= 0) {
            return ScaledImage.getScaledImage(icon, DIMENSION_BUTTONS, DIMENSION_BUTTONS);
        }

        // Calculates the proportions of the image
        final double iconAspectRatio = (double) icon.getWidth() / icon.getHeight();
        final double panelAspectRatio = (double) widthWitchButton / heightWitchText;

        // Check if the image is wider than the available space
        if (icon.getWidth() > widthWitchButton || icon.getHeight() > heightWitchText) {
            if (panelAspectRatio > iconAspectRatio) {
                // L'altezza è il fattore limitante
                final int newHeight = heightWitchText;
                final int newWidth = (int) (newHeight * iconAspectRatio);
                return ScaledImage.getScaledImage(icon, newWidth, newHeight);
            } else {
                // Width is the limiting factor
                final int newWidth = widthWitchButton;
                final int newHeight = (int) (newWidth / iconAspectRatio);
                return ScaledImage.getScaledImage(icon, newWidth, newHeight);
            }
        }
        return new ImageIcon(icon);
    }
}
