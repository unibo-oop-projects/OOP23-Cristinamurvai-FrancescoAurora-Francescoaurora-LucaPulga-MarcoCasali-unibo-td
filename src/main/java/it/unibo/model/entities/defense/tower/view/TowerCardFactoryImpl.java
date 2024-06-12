package it.unibo.model.entities.defense.tower.view;

import javax.swing.*;
import it.unibo.model.entities.defense.tower.Tower;
import java.awt.*;
import java.awt.event.ActionEvent;
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
                showWeaponDialog(tower.getName());
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
    private void showWeaponDialog(final String towerName) {
        JDialog weaponDialog = new JDialog();
        weaponDialog.setSize(400, 300);

        JPanel weaponPanel = new JPanel();
        weaponPanel.setLayout(new BoxLayout(weaponPanel, BoxLayout.Y_AXIS));

        weaponPanel.add(new JLabel("Weapon 1: Damage 10"));
        weaponPanel.add(new JLabel("Weapon 2: Damage 20"));
        weaponPanel.add(new JLabel("Weapon 3: Damage 30"));

        weaponDialog.add(weaponPanel);
        weaponDialog.setVisible(true);
    }
}
