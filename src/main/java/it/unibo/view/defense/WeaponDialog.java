package it.unibo.view.defense;

import java.awt.BorderLayout;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.weapon.Weapon;

/**
 * Weapons dialog related to a specific {@link Tower}.
 */
public class WeaponDialog extends JDialog {

    /**
     * Constructor.
     *
     * @param parent {@link Tower} card.
     * @param tower info.
     */
    public WeaponDialog(final JFrame parent, final Tower tower) {
        super(parent, "Weapons for " + tower.getName(), true);

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
