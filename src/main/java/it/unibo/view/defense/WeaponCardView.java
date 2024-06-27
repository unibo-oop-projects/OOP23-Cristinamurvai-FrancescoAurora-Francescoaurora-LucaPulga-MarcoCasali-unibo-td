package it.unibo.view.defense;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unibo.model.entities.defense.weapon.Weapon;
import java.util.Set;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;

import javax.swing.ImageIcon;
import it.unibo.view.defense.WeaponCardView;

public class WeaponCardView extends JFrame {

    private String towerName;
    private Set<Weapon> weapons;

    public WeaponCardView(String towerName, Set<Weapon> weapons) {
        this.towerName = towerName;
        this.weapons = weapons;
    }

    public JDialog showWeaponDialog() {
        JDialog weaponDialog = new JDialog(this, "Weapons for " + towerName, true);
        weaponDialog.setSize(400, 300);

        JPanel weaponPanel = new JPanel();
        weaponPanel.setLayout(new BoxLayout(weaponPanel, BoxLayout.Y_AXIS));

        weapons.stream().map(this::createWeaponPanel).forEach(weaponPanel::add);

        weaponDialog.add(new JScrollPane(weaponPanel));
        weaponDialog.setLocationRelativeTo(this);
        weaponDialog.setVisible(true);
        return weaponDialog;
    }

    private JPanel createWeaponPanel(Weapon weapon) {
        JPanel singleWeaponPanel = new JPanel(new BorderLayout());

        String type = weapon.getType();
        int frequency = weapon.getFrequency();
        String imgPath = weapon.getPath();

        JLabel typeLabel = new JLabel("Type: " + type);
        JLabel frequencyLabel = new JLabel("Frequency: " + frequency);
        JLabel imgLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource(imgPath)));
        imgLabel.setPreferredSize(new Dimension(200, 150));

        singleWeaponPanel.add(typeLabel, BorderLayout.WEST);
        singleWeaponPanel.add(frequencyLabel, BorderLayout.CENTER);
        singleWeaponPanel.add(imgLabel, BorderLayout.EAST);

        return singleWeaponPanel;
    }
}
