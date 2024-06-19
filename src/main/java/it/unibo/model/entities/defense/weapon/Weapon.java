package it.unibo.model.entities.defense.weapon;

import it.unibo.model.entities.IEntity;

/**
 * Represents the Weapon entity.
 */
public interface Weapon extends IEntity {
    /**
     * Represents the frequency with which the weapon can fire.
     * @return the frequency with which the weapon can fire.
     */
    int getFrequency();

    long getLastShotTime();

    void setLastShotTime(long lastShotTime);
}
