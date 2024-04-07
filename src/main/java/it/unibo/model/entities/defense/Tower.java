package it.unibo.model.entities.defense;

import java.util.Set;

import it.unibo.model.entities.Entity;

/**
 * Represents the tower entity.
 */
public interface Tower extends Entity{
    
    /**
     * Represents the level of the tower.
     * 
     * @return the level of the tower.
     */
    int getLevel();

    /**
     * Represents the attackable range from the tower.
     * 
     * @return the attackable range from the tower.
     */
    int getRange();

    /**
     * Represents the associated weapons.
     * 
     * @return the the associated weapons.
     */
    Set<Weapon> getWeapons();

    /**
     * 
     * @return
     */
    int getCost();

}