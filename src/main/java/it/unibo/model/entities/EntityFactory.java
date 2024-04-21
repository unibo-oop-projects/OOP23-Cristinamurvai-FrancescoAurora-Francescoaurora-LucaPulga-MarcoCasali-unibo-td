package it.unibo.model.entities;

import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;
import it.unibo.model.utilities.Vector2D;

/**
 * Entity factory method.
 */
public interface EntityFactory {
    /**
     * Create Tower's instance.
     * @param name
     * @param position2d
     * @return Tower's instance.
     */
    Tower createTower(String name, Position2D position2d);

    /**
     * Create Enemy's instance.
     * @param name
     * @return Enemy's instance.
     */
    Enemy createEnemy(String name);

    /**
     * Create Weapon's instance.
     * @param name
     * @return Weapon's instance.
     */
    Weapon createWeapon(String name);

    /**
     * Create Bullet's instance.
     * @param name
     * @param position2d
     * @param direction
     * @return Bullet's instance.
     */
    Bullet createBullet(String name, Position2D position2d, Vector2D direction);
}
