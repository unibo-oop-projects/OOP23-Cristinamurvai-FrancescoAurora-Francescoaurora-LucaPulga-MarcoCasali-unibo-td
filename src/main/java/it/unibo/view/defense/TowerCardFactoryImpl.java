package it.unibo.view.defense;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.map.GameMap;

/**
 * Factory implementation of {@link TowerCardFactory} that creates tower cards
 * selectable on the panel located on the right side of the screen.
 */
public class TowerCardFactoryImpl implements TowerCardFactory {

    public Tower selectedTower;
    private final int DIALOG_WIDTH = 400;
    private final int DIALOG_HEIGHT = 300;
    private final int WEAPON_IMG_WIDTH = 150;
    private final int WEAPON_IMG_HEIGHT = 100;

    /**
     * Constructor.
     */
    public TowerCardFactoryImpl() {
    }

    /**
     * Creates the defense panel positioned on the right side of the screen.
     *
     * @param tower card representing the tower's info.
     * @return JPanel representing the tower's info.
     */
    private JPanel createTowerCard(final Tower tower) {
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
        JLabel typeLabel = new JLabel("Cost: " + tower.getCost());
        statsPanel.add(typeLabel);

        card.add(statsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton weaponButton = new JButton("Weapons");
        weaponButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.out.println("Weapon button clicked");
                showWeaponDialog(tower);
            }
        });
        buttonPanel.add(weaponButton);
        card.add(buttonPanel, BorderLayout.SOUTH);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                selectedTower = tower;
                System.out.println("Selected tower: " + tower.getName());
            }
        });
        return card;
    }

    /**
     * Creates the defense panel positioned on the right side of the screen.
     *
     * @param towers panel to display.
     * @return JPanel with all the towers.
     */
    @Override
    public JPanel createDefensePanel(final Set<Tower> towers) {
        JPanel towerPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        towers.forEach(tower -> towerPanel.add(createTowerCard(tower)));
        return towerPanel;
    }

    /**
     * Dialog to display all the {@link Weapon}s owned by a tower.
     *
     * @param tower 's weapons do be displayed.
     */
    @Override
    public void showWeaponDialog(final Tower tower) {
        JPanel weaponPanel = new JPanel();
        weaponPanel.setLayout(new BoxLayout(weaponPanel, BoxLayout.Y_AXIS));

        Set<Weapon> weapons = tower.getWeapons();
        for (Weapon weapon : weapons) {
            JPanel weaponInfoPanel = new JPanel(new BorderLayout());

            if (weapon.getPath() != null) {
                JLabel imgLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource(weapon.getPath())));
                imgLabel.setPreferredSize(new Dimension(WEAPON_IMG_WIDTH, WEAPON_IMG_HEIGHT));
                weaponInfoPanel.add(imgLabel, BorderLayout.WEST);
            }

            JPanel specsPanel = new JPanel();
            specsPanel.setLayout(new BoxLayout(specsPanel, BoxLayout.Y_AXIS));

            JLabel nameLabel = new JLabel("Name: " + weapon.getName());
            specsPanel.add(nameLabel);

            JLabel damageLabel = new JLabel("Frequency: " + weapon.getFrequency());
            specsPanel.add(damageLabel);

            weaponInfoPanel.add(specsPanel, BorderLayout.CENTER);
            weaponPanel.add(weaponInfoPanel);
        }

        JDialog dialog = new JDialog((Frame) null, "Weapon Information", true);
        dialog.setContentPane(weaponPanel);
        dialog.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    /**
     * Selected tower's card to be positioned on the {@link GameMap}.
     *
     * @return tower's panel.
     */
    @Override
    public Tower getSelectedTower() {
        return this.selectedTower;
    }
}
