package it.unibo.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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

import it.unibo.controller.GameControllerImpl;

/**
 * map loading with slider for scrolling.
 */
public class SelectMapGui extends JFrame {
    private final JLabel[] imageLabels;
    private int focusIndex;
    private final List<String> maps = new GameControllerImpl().getAvailableMaps();
    private final JPanel oldGui;
    private GuiGameStart guiGameStart;
    private JLabel leftButton;
    private JLabel rightButton;
    private Image left = null;
    private Image right = null;
    private static final int DIMENSION_BUTTONS = 100;

    /**
     * @param oldGui switching the gui panel of the old window
     */
    public SelectMapGui(final JPanel oldGui) {
        this.oldGui = oldGui;

        // Set the layout of the contentPane to BorderLayout
        oldGui.setLayout(new BorderLayout());

        focusIndex = 0; // Index of the central image

        // Button to scroll left
        this.leftButton = new JLabel();
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
            // Update the focusIndex and redraw the images
            focusIndex = (focusIndex - 1 + maps.size()) % maps.size();
            updateImages();
            }
        });

        try {
            left = ImageIO.read(ClassLoader.getSystemResource("buttons/left.png"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("error when retrieving " + "buttons/left.png");
        }

        leftButton.setPreferredSize(new Dimension(DIMENSION_BUTTONS, DIMENSION_BUTTONS));
        leftButton.setIcon(getScaledImage(left, DIMENSION_BUTTONS, DIMENSION_BUTTONS));

        // Add the button to scroll left to the frame
        oldGui.add(leftButton, BorderLayout.WEST);

        // Button to scroll right
        this.rightButton = new JLabel();
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
            // Update the focusIndex and redraw the images
            focusIndex = (focusIndex + 1) % maps.size();
            updateImages();
            }
        });

        try {
            right = ImageIO.read(ClassLoader.getSystemResource("buttons/right.png"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("error when retrieving " + "buttons/right.png");
        }

        rightButton.setPreferredSize(new Dimension(DIMENSION_BUTTONS, DIMENSION_BUTTONS));
        rightButton.setIcon(getScaledImage(right, DIMENSION_BUTTONS, DIMENSION_BUTTONS));
        
        // Add the button to scroll right to the frame
        oldGui.add(rightButton, BorderLayout.EAST);

        /**TODO settare il pannello imagePannel alla dimensione della larghezza - bottoni, dedicare 1/4 
        //all'immagine di destra e sinistra e 2/4 a quella centrale, impostare resize, e gestione dimensione 0
        aggiungere i nomi delle mappe
        */
        // Panel to contain the images
        JPanel imagePanel = new JPanel(new FlowLayout());

        // Initialize the array of JLabels
        imageLabels = new JLabel[3];

        // Initialize the JLabels with the images
        for (int i = 0; i < 3; i++) {
            int index = i % maps.size(); // Calculate the index of the image
            imageLabels[i] = new JLabel(new ImageIcon(ClassLoader.getSystemResource("map_preview/" + maps.get(index) + ".png")));
            final int tmpIndex = index; // Save the index for use in the mouse listener
            imageLabels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    // Change GUI for starting game
                    changeGui(maps.get(tmpIndex));
                }
            });
            imagePanel.add(imageLabels[i]);
        }

        // Initialize the JLabels with the images
        oldGui.add(imagePanel, BorderLayout.CENTER);

        // Request the container to update the GUI
        oldGui.revalidate();
        oldGui.repaint();
    }

    // Update the image when an arrow is clicked
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
            // Remove previous mouse listeners
            for (MouseListener adapter : imageLabels[i].getMouseListeners()) {
                imageLabels[i].removeMouseListener(adapter);
            }
            imageLabels[i].setIcon(new ImageIcon(ClassLoader.getSystemResource("map_preview/" + maps.get(tmp) + ".png")));
            imageLabels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    // Change GUI for starting game
                    changeGui(maps.get(tmp));
                }
            });
        }
    }

    /**
     * Change gui with map selected and start game.
     * @param mapSelected Selected map name
     */
    public void changeGui(final String mapSelected) {
        oldGui.removeAll();
        if (guiGameStart == null) {
            guiGameStart = new GuiGameStart((mapSelected), this.oldGui);
        } else {
            // Ensure SelectMapGui is visible if it's already instantiated
            guiGameStart.setVisible(true);
        }
    }

    /**
     * TODO reference
     * https://stackoverflow.com/a/6714381 .
     * @param srcImg source Image
     * @param width
     * @param height
     */
    private ImageIcon getScaledImage(final Image srcImg, final int width, final int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, width, height, null);
        g2.dispose();

        return new ImageIcon(resizedImg);
    }
}