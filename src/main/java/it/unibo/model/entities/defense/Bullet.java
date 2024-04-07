package it.unibo.model.entities.defense;

/**
 * Represents the bullet fired from the defensive tower's weapon.
 */
public interface Bullet {

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