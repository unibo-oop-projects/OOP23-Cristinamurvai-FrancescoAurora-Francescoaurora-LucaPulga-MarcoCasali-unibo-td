package it.unibo.view;

import javax.swing.*;

import it.unibo.model.map.GameMap;
import it.unibo.model.map.GameMapFactory;
import it.unibo.model.map.GameMapFactoryImpl;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Loading Game Screen.
 */
public class GuiGameStart extends JFrame {

    private String selectedTowerName = null;
    private String selectedTowerImagePath = null;

    public GuiGameStart(final JPanel oldGui, final int mapToLoad) {
        oldGui.setLayout(new BorderLayout()); // Main layout with BorderLayout

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
        oldGui.add(labelPanel, BorderLayout.NORTH);

        // Create and add the game map panel
        final GameMapFactory mapFactory = new GameMapFactoryImpl();
        final GameMap map = mapFactory.fromName("test");
        JPanel mapPanel = new JPanel(new GridLayout(map.getRows(), map.getColumns())); // Example grid layout for the map
        map.getTiles().forEach(t -> {
            final JButton cell = new JButton();
            cell.setIcon(new ImageIcon(ClassLoader.getSystemResource(t.getSprite())));
            cell.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (selectedTowerName != null) {
                        ImageIcon icon = new ImageIcon(getClass().getResource(selectedTowerImagePath));
                        Image img = icon.getImage();
                        Image resizedImg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                        cell.setIcon(new ImageIcon(resizedImg));
                        cell.setText("");
                        System.out.println("Placed " + selectedTowerName + " at cell " + cell.getText());
                        selectedTowerName = null; // Clear selection after placing the tower
                        selectedTowerImagePath = null;
                    }
                }
            });
            mapPanel.add(cell);
        });
                
        oldGui.add(mapPanel, BorderLayout.CENTER);

        // Create and add the tower panel with two columns
        JPanel towerPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // Layout with two columns and dynamic rows

        // Add towers
        towerPanel.add(createTowerCard("Tower1", "/towers/img/tower1.jpg", 100, 540, 1));
        towerPanel.add(createTowerCard("Tower2", "/towers/img/tower1.jpg", 20, 100, 1));
        towerPanel.add(createTowerCard("Tower2", "/towers/img/tower1.jpg", 20, 100, 1));
        towerPanel.add(createTowerCard("Tower2", "/towers/img/tower1.jpg", 20, 100, 1));
        towerPanel.add(createTowerCard("Tower1", "/towers/img/tower1.jpg", 100, 540, 1));
        towerPanel.add(createTowerCard("Tower2", "/towers/img/tower1.jpg", 20, 100, 1));
        towerPanel.add(createTowerCard("Tower2", "/towers/img/tower1.jpg", 20, 100, 1));
        towerPanel.add(createTowerCard("Tower2", "/towers/img/tower1.jpg", 20, 100, 1));

        // Add more towers as needed
        JScrollPane scrollPane = new JScrollPane(towerPanel);
        scrollPane.setPreferredSize(new Dimension(300, 0)); // Set preferred size for the scroll pane
        oldGui.add(scrollPane, BorderLayout.EAST);

        // Request the container to update the GUI
        oldGui.revalidate();
        oldGui.repaint();
    }

    private JPanel createTowerCard(String name, String imgPath, int stat0, int stat1, int stat2) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        String tooltipText = "<html><b>Name:</b> " + name + "<br><b>Stat0:</b> " + stat0 +
        "<br><b>Stat1:</b> " + stat1 + "<br><b>Stat2:</b> " + stat2 + "</html>";

        // Aggiungi tooltip con le statistiche formattate
        card.setToolTipText(tooltipText);
        // Panel for image with top margin
        JPanel imgPanel = new JPanel(new BorderLayout());
        imgPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Add top margin
        JLabel imgLabel = new JLabel(new ImageIcon(getClass().getResource(imgPath)));
        imgLabel.setPreferredSize(new Dimension(200, 150)); // Set preferred size for image
        imgPanel.add(imgLabel, BorderLayout.NORTH);
        card.add(imgPanel, BorderLayout.NORTH);

        // Panel for stats with left margin
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Add left margin

        JLabel nameLabel = new JLabel(name);
        statsPanel.add(nameLabel);

        JLabel stat1Label = new JLabel("Stat1: " + stat1);
        statsPanel.add(stat1Label);

        card.add(statsPanel, BorderLayout.CENTER);

        // Button panel with centered button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton weaponButton = new JButton("Weapons");
        weaponButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showWeaponDialog(name);
            }
        });
        buttonPanel.add(weaponButton);
        card.add(buttonPanel, BorderLayout.SOUTH);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedTowerName = name;
                selectedTowerImagePath = imgPath;
                System.out.println("Selected tower: " + name);
            }
        });
        return card;
    }

    private void showWeaponDialog(String towerName) {
        JDialog weaponDialog = new JDialog(this, "Weapons for " + towerName, true);
        weaponDialog.setSize(400, 300);

        JPanel weaponPanel = new JPanel();
        weaponPanel.setLayout(new BoxLayout(weaponPanel, BoxLayout.Y_AXIS));

        // Example weapons, you can replace these with real data
        weaponPanel.add(new JLabel("Weapon 1: Damage 10"));
        weaponPanel.add(new JLabel("Weapon 2: Damage 20"));
        weaponPanel.add(new JLabel("Weapon 3: Damage 30"));

        weaponDialog.add(weaponPanel);
        weaponDialog.setLocationRelativeTo(this);
        weaponDialog.setVisible(true);
    }
}