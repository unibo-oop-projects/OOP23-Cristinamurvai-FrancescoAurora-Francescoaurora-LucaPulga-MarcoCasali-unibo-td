package it.unibo.model.entities.defense.tower.view;

import javax.swing.JPanel;
import java.util.Set;
import it.unibo.model.entities.defense.tower.Tower;

public interface TowerCardFactory {
    JPanel createTowerCard(Tower tower);

    JPanel createDefensePanel(Set<Tower> towers);

    void showWeaponDialog(Tower tower);
}
