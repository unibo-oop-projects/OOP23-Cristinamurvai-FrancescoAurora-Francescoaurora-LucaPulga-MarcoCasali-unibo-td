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
    // /**
    //  * load Tower's instance.
    //  * @param name
    //  * @param position2d
    //  * @return Tower's instance.
    //  */
    // Tower loadTower(String name, Position2D position2d);

    // /**
    //  * load Enemy's instance.
    //  * @param name
    //  * @return Enemy's instance.
    //  */
    // Enemy loadEnemy(String name);

    // /**
    //  * load Weapon's instance.
    //  * @param name
    //  * @return Weapon's instance.
    //  */
    // Weapon loadWeapon(String name);

    // /**
    //  * load Bullet's instance.
    //  * @param name
    //  * @param position2d
    //  * @param direction
    //  * @return Bullet's instance.
    //  */
    // Bullet loadBullet(String name, Position2D position2d, Vector2D direction);

    <T> T loadEntity(final String filePath, final Position2D position2d, Vector2D direction, Class<T> entityType);
}
