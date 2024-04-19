package it.unibo.model.entities.defense.tower;

import java.util.Set;
import java.util.Optional;

import it.unibo.model.entities.MovableEntity;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.enemies.Enemy;

/**
 * Represents the tower entity.
*/
public interface Tower extends MovableEntity {    
    /**
     * Represents the level of the tower.
     * @return the level of the tower.
     */
    int getLevel();

    /**
     * Represents the attackable range from the tower.
     * @return the attackable range from the tower.
     */
    int getRange();

    /**
     * Represents the associated weapons.
     * @return the the associated weapons.
     */
    Set<Weapon> getWeapons();

    /**
     * Current weapon.
     * @return Current weapon.
     */
    Weapon getCurrentWeapon();

    /**
     * Tower's cost.
     * @return tower's cost.
     */
    int getCost();

    /**
     * Shoot a shot.
     */
    void attack(Set<Enemy> enemies);

    /**
     * Enenmy to be targetted.
     * @param enemies
     * @return Targetted enemy.
     */
    Optional<Enemy> targetEnemy(Set<Enemy> enemies);

}
