package it.unibo.model.entities.defense.tower.view;

import javax.swing.JPanel;

import it.unibo.model.entities.defense.tower.Tower;

public interface TowerCardFactory {
    JPanel createTowerCard(Tower tower);
}
