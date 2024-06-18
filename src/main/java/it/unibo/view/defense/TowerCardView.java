package it.unibo.view.defense;
import javax.swing.*;

import it.unibo.model.entities.defense.tower.Tower;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class TowerCardView extends JFrame {

    private Tower tower;
    
    public TowerCardView (Tower tower){
        this.tower = tower;
    }

    public JPanel createTowerCard() {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        String tooltipText = createTooltipText(tower);
        card.setToolTipText(tooltipText);

        JPanel imgPanel = new JPanel(new BorderLayout());
        imgPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        JLabel imgLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource(tower.getPath())));
        imgLabel.setPreferredSize(new Dimension(200, 150));
        imgPanel.add(imgLabel, BorderLayout.NORTH);
        card.add(imgPanel, BorderLayout.NORTH);

        JPanel statsPanel = createStatsPanel(tower);
        card.add(statsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel(tower);
        card.add(buttonPanel, BorderLayout.SOUTH);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Selected tower: " + tower.getName());
            }
        });

        return card;
    }

    private String createTooltipText(Tower tower) {
        StringBuilder tooltipText = new StringBuilder("<html><b>Name:</b> " + tower.getName());
        for (Field field : tower.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                tooltipText.append("<br><b>").append(field.getName()).append(":</b> ").append(field.get(tower));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        tooltipText.append("</html>");
        return tooltipText.toString();
    }

    private JPanel createStatsPanel(Tower tower) {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        for (Field field : tower.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                JLabel label = new JLabel(field.getName() + ": " + field.get(tower));
                statsPanel.add(label);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return statsPanel;
    }

    private JPanel createButtonPanel(Tower tower) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton weaponButton = new JButton("Weapons");
        WeaponCardView weaponCardView = new WeaponCardView(tower.getName(), tower.getWeapons());
        weaponButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                weaponCardView.showWeaponDialog();
            }
        });
        buttonPanel.add(weaponButton);
        return buttonPanel;
    }
}