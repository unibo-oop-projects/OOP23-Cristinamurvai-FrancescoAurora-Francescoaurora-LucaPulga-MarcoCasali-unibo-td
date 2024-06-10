package it.unibo.model.entities.defense.tower.view;

import it.unibo.model.entities.defense.tower.Tower;

import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.GridLayout;

/**
 * TowerPanel.
 */
public class TowerPanel extends JPanel {

    /**
     * Constractor.
     * @param towers
     */
    public TowerPanel(final Set<Tower> towers) {
        setLayout(new GridLayout(towers.size(), 2));

        for (Tower tower : towers) {
            add(createTowerPanel(tower));
        }
    }

    private JPanel createTowerPanel(final Tower tower) {
        JPanel towerPanel = new JPanel();
        towerPanel.setBorder(BorderFactory.createEtchedBorder());
        towerPanel.setLayout(new BorderLayout());

        JLabel towerImageLabel = new JLabel(new ImageIcon(getClass().getResource(tower.getPath())));
        towerPanel.add(towerImageLabel, BorderLayout.CENTER);

        JTextArea towerStatsTextArea = new JTextArea();
        towerStatsTextArea.setEditable(false);
        towerStatsTextArea.append("Name: " + tower.getName() + "\n");
        towerStatsTextArea.append("Type: " + tower.getType() + "\n");
        towerStatsTextArea.append("Target Strategy: " + tower.getTargetSelectionStrategy() + "\n");
        towerStatsTextArea.append("Attack Strategy: " + tower.getAttackStrategy() + "\n");
        towerStatsTextArea.append("Cost: " + tower.getCost() + "\n");
        // Aggiungi altre statistiche
        towerPanel.add(towerStatsTextArea, BorderLayout.SOUTH);

        return towerPanel;
    }
}
