package it.unibo.model.entities.defense.tower.view;

import javax.swing.*;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.weapon.Weapon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Set;
import java.awt.event.ActionListener;

public class TowerCardFactoryImpl implements TowerCardFactory {

    public Tower selectedTower;

    public TowerCardFactoryImpl() {
    }

    @Override
    public JPanel createTowerCard(Tower tower) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        String tooltipText = "<html><b>Name:</b> " + tower.getName()
                + "<br><b>Type:</b> " + tower.getType()
                + "<br><b>Range:</b> " + tower.getRange()
                + "<br><b>Cost:</b> " + tower.getCost()
                + "<br><b>Level:</b> " + tower.getLevel()
                + "</html>";

        card.setToolTipText(tooltipText);
        JPanel imgPanel = new JPanel(new BorderLayout());
        imgPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        JLabel imgLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource(tower.getPath())));
        imgLabel.setPreferredSize(new Dimension(250, 200));
        imgPanel.add(imgLabel, BorderLayout.NORTH);
        card.add(imgPanel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JLabel nameLabel = new JLabel("Name: " + tower.getName());
        statsPanel.add(nameLabel);

        JLabel typeLabel = new JLabel("Type: " + tower.getType());
        statsPanel.add(typeLabel);

        card.add(statsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton weaponButton = new JButton("Weapons");
        weaponButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Weapon button clicked");
                showWeaponDialog(tower);
            }
        });
        buttonPanel.add(weaponButton);
        card.add(buttonPanel, BorderLayout.SOUTH);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedTower = tower;
                System.out.println("Selected tower: " + tower.getName());
            }
        });
        return card;
    }

    @Override
    public JPanel createDefensePanel(Set<Tower> towers) {
        JPanel towerPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        towers.forEach(tower -> towerPanel.add(createTowerCard(tower)));
        return towerPanel;
    }

    @Override
    public void showWeaponDialog(Tower tower) {
        // Creazione del pannello delle armi
        JPanel weaponPanel = new JPanel();
        weaponPanel.setLayout(new BoxLayout(weaponPanel, BoxLayout.Y_AXIS));

        Set<Weapon> weapons = tower.getWeapons();
        for (Weapon weapon : weapons) {
            JPanel weaponInfoPanel = new JPanel(new BorderLayout());

            // Aggiunta dell'immagine a sinistra
            if (weapon.getPath() != null) {
                JLabel imgLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource(weapon.getPath())));
                imgLabel.setPreferredSize(new Dimension(150, 100));
                weaponInfoPanel.add(imgLabel, BorderLayout.WEST);
            }

            // Creazione di un pannello per le specifiche
            JPanel specsPanel = new JPanel();
            specsPanel.setLayout(new BoxLayout(specsPanel, BoxLayout.Y_AXIS));

            JLabel nameLabel = new JLabel("Name: " + weapon.getName());
            specsPanel.add(nameLabel);

            JLabel damageLabel = new JLabel("Frequency: " + weapon.getFrequency());
            specsPanel.add(damageLabel);

            // Aggiunta del pannello delle specifiche al centro
            weaponInfoPanel.add(specsPanel, BorderLayout.CENTER);

            weaponPanel.add(weaponInfoPanel);
        }

        // Creazione e visualizzazione della finestra di dialogo
        JDialog dialog = new JDialog((Frame) null, "Weapon Information", true);
        dialog.setContentPane(weaponPanel);
        dialog.setSize(400, 300); // Imposta la dimensione preferita
        dialog.setLocationRelativeTo(null); // Centra la finestra sullo schermo
        dialog.setVisible(true);
    }

    @Override
    public Tower getSelectedTower() {
        return this.selectedTower;
    }
}
