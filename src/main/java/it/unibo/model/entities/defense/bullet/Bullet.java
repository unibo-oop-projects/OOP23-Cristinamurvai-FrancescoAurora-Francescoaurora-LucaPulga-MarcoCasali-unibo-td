package it.unibo.model.entities.defense.bullet;

import it.unibo.model.entities.MovableEntity;

/**
 * Represents the bullet fired from the defensive tower's weapon.
 */
public interface Bullet extends MovableEntity {

    /**
     * Represents the damage dealt to the target enemy.
     * 
     * @return the damage dealt to the target enemy.
     */
    int getDamage();

    /**
     * Represents the speed bullet.
     * 
     * @return the speed bullet.
     */
    int getSpeed();
}
