package it.unibo.model.entities.defense.tower.view;

import javax.swing.*;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.weapon.Weapon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Set;
import java.awt.event.ActionListener;

public class TowerCardFactoryImpl implements TowerCardFactory {

    public TowerCardFactoryImpl () { }

    @Override
    public JPanel createTowerCard(Tower tower) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        String tooltipText = "<html><b>Name:</b> " + tower.getName() + 
                             "<br><b>Type:</b> " + tower.getType() + 
                             "<br><b>Range:</b> " + tower.getRange() + 
                             "<br><b>Cost:</b> " + tower.getCost() + 
                             "</html>";

        card.setToolTipText(tooltipText);
        JPanel imgPanel = new JPanel(new BorderLayout());
        imgPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        JLabel imgLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource(tower.getPath())));
        imgLabel.setPreferredSize(new Dimension(200, 150));
        imgPanel.add(imgLabel, BorderLayout.NORTH);
        card.add(imgPanel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JLabel nameLabel = new JLabel(tower.getName());
        statsPanel.add(nameLabel);

        card.add(statsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton weaponButton = new JButton("Weapons");
        weaponButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showWeaponDialog(tower);
            }
        });
        buttonPanel.add(weaponButton);
        card.add(buttonPanel, BorderLayout.SOUTH);

        // card.addMouseListener(new java.awt.event.MouseAdapter() {
        //     public void mouseClicked(java.awt.event.MouseEvent evt) {
        //         selectedTowerName = name;
        //         selectedTowerImagePath = imgPath;
        //         System.out.println("Selected tower: " + name);
        //     }
        // });
        return card;
    }

    @Override
    public JPanel createDefensePanel(Set<Tower> towers){
        JPanel towerPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        towers.forEach(tower -> towerPanel.add(createTowerCard(tower)));
        return towerPanel;
    }

    @Override
    public void showWeaponDialog(Tower tower) {
        JPanel weaponPanel = new JPanel();
        weaponPanel.setLayout(new BoxLayout(weaponPanel, BoxLayout.Y_AXIS));
        
        Set<Weapon> weapons = tower.getWeapons();
        for (Weapon weapon : weapons) {
            JPanel weaponInfoPanel = new JPanel(new BorderLayout());

            JLabel nameLabel = new JLabel("Name: " + weapon.getName());
            weaponInfoPanel.add(nameLabel, BorderLayout.NORTH);

            JLabel damageLabel = new JLabel("Frequency: " + weapon.getFrequency());
            weaponInfoPanel.add(damageLabel, BorderLayout.CENTER);

            if (weapon.getPath() != null) {
                JLabel imageLabel = new JLabel(new ImageIcon(weapon.getPath()));
                weaponInfoPanel.add(imageLabel, BorderLayout.WEST);
            }
            weaponPanel.add(weaponInfoPanel);
        }
    }
}
