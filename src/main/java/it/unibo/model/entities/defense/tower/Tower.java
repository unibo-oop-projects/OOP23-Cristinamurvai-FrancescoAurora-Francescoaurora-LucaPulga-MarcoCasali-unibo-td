package it.unibo.model.entities.defense.tower;

import java.util.Optional;
import java.util.Set;

import it.unibo.model.entities.MovableEntity;
import it.unibo.model.entities.defense.tower.attack.AttackStrategy;
import it.unibo.model.entities.defense.tower.target.TargetSelectionStrategy;
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
    double getRange();

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

    public Optional<Set<Enemy>> target(Set<Enemy> enemies);

    public void attack(Set<Enemy> enemies);

    /**
     * The tower target selection strategy.
     * @return the tower target selection strategy.
     */
    TargetSelectionStrategy getTargetSelectionStrategy();
    
    /**
     * The tower attack strategy.
     * @return the tower attack strategy.
     */
    AttackStrategy getAttackStrategy();
}
