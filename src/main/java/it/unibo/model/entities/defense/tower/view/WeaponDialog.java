package it.unibo.model.entities.defense.tower.view;

import javax.swing.*;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.weapon.Weapon;

import java.awt.*;
import java.util.Set;

public class WeaponDialog extends JDialog {
    public WeaponDialog(JFrame parent, Tower tower) {
        super(parent, "Weapons for " + tower.getName(), true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        JPanel weaponPanel = new JPanel();
        weaponPanel.setLayout(new BoxLayout(weaponPanel, BoxLayout.Y_AXIS));

        Set<Weapon> weapons = tower.getWeapons();
        for (Weapon weapon : weapons) {
            JPanel weaponInfoPanel = new JPanel(new BorderLayout());

            JLabel nameLabel = new JLabel("Name: " + weapon.getName());
            weaponInfoPanel.add(nameLabel, BorderLayout.NORTH);

            JLabel damageLabel = new JLabel("Frequency: " + weapon.getFrequency());
            weaponInfoPanel.add(damageLabel, BorderLayout.CENTER);

            // Aggiungi l'immagine dell'arma se disponibile
            if (weapon.getPath() != null) {
                JLabel imageLabel = new JLabel(new ImageIcon(weapon.getPath()));
                weaponInfoPanel.add(imageLabel, BorderLayout.WEST);
            }
            weaponPanel.add(weaponInfoPanel);
        }

        JScrollPane scrollPane = new JScrollPane(weaponPanel);
        getContentPane().add(scrollPane);
    }
}
