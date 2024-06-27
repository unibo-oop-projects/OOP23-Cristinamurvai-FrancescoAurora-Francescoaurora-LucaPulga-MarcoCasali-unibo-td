package it.unibo.model.entities.defense.weapon;

import it.unibo.model.entities.IEntity;
import it.unibo.model.entities.defense.bullet.Bullet;

/**
 * Represents the Weapon {@link IEntity}.
 */
public interface Weapon extends IEntity {

    /**
     * Represents the frequency with which the {@link Weapon} can fire a {@link Bullet}.
     * @return the frequency with which the weapon can fire a {@link Bullet}.
     */
    int getFrequency();

    /**
     * Represents the last {@link Bullet}'s time fired by the {@link Weapon}.
     * @return the last {@link Bullet}'s time fired by the {@link Weapon} in millis.
     */
    long getLastShotTime();

    /**
     * Sets the last {@link Bullet}'s time fired by the {@link Weapon}.
     * @param lastShotTime sets the last {@link Bullet}'s time fired by the {@link Weapon} in millis.
     */
    void setLastShotTime(final long lastShotTime);
}
