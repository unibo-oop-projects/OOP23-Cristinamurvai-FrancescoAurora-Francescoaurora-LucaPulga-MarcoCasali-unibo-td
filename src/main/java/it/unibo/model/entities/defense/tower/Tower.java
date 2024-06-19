package it.unibo.model.entities.defense.tower;

import java.util.Optional;
import java.util.Set;

import it.unibo.model.entities.IMovableEntity;
import it.unibo.model.entities.defense.tower.attack.AttackStrategy;
import it.unibo.model.entities.defense.tower.target.TargetSelectionStrategy;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.enemies.Enemy;

/**
 * Represents the tower entity.
*/
public interface Tower extends IMovableEntity {
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

    Optional<Set<Enemy>> target(Set<Enemy> enemies);

    void attack(Set<Enemy> enemies);

    TargetSelectionStrategy getTargetSelectionStrategy();

    AttackStrategy getAttackStrategy();

    void setTargetSelectionStrategy(TargetSelectionStrategy targetSelectionStrategy);

    void setAttackStrategy(AttackStrategy attackStrategy);
}
