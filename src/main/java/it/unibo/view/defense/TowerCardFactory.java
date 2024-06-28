package it.unibo.view.defense;

import java.util.Set;

import javax.swing.JPanel;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.map.GameMap;

/**
 * Factory that creates tower cards selectable on the panel located on the right
 * side of the screen.
 */
public interface TowerCardFactory {

    /**
     * Creates the defense panel positioned on the right side of the screen.
     *
     * @param towers panel to display.
     * @return JPanel with all the towers.
     */
    JPanel createDefensePanel(Set<Tower> towers);

    /**
     * Dialog to display all the {@link Weapon}s owned by a tower.
     *
     * @param tower 's weapons do be displayed.
     */
    void showWeaponDialog(Tower tower);

    /**
     * Selected tower's card to be positioned on the {@link GameMap}.
     *
     * @return tower's panel.
     */
    Tower getSelectedTower();
}
