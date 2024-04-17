package it.unibo.model.entities.defense.bullet;

import it.unibo.model.entities.Entity;
import it.unibo.model.entities.enemies.Enemy;

/**
 * Represents the bullet fired from the defensive tower's weapon.
 */
public interface Bullet extends Entity {

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

    /**
     * Bullet movement;
     */
    void move();
    
    /**
     * Hit the targetted enemy.
     * @param enemy
     */
    void hitEnemy(Enemy enemy);
}
