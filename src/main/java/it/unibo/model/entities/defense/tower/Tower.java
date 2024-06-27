package it.unibo.model.entities.defense.tower;

import java.util.Optional;
import java.util.Set;

import it.unibo.model.entities.IMovableEntity;
import it.unibo.model.entities.defense.bullet.Bullet;
import it.unibo.model.entities.defense.tower.attack.AttackStrategy;
import it.unibo.model.entities.defense.tower.target.TargetSelectionStrategy;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.enemies.Enemy;

/**
 * Represents the {@link Tower} entity.
 */
public interface Tower extends IMovableEntity {

    /**
     * Get {@link Tower}'s level.
     * @return {@link Tower}'s level.
     */
    int getLevel();

    /**
     * Represents the attackable range from the {@link Tower}.
     * @return the attackable range from the {@link Tower}.
     */
    double getRange();

    /**
     * Get {@link Tower}'s associated weapons.
     * @return {@link Tower}'s associated weapons.
     */
    Set<Weapon> getWeapons();

    /**
     * Get {@link Tower}'s current {@link Weapon}.
     * @return {@link Tower}'s current {@link Weapon}.
     */
    Weapon getCurrentWeapon();

    /**
     * {@link Tower}'s cost.
     * @return {@link Tower}'s cost.
     */
    int getCost();


    Optional<Enemy> target(Set<Enemy> enemies);

    /**
     * {@link Tower}'s attack method to attack target {@link Enemy}.
     * Attacking @param tower.
     * Target @param enemy chosen by the {@link Tower} depending on the {@link TargetSelectionStrategy}.
     */
    void attack(Set<Enemy> enemies);

    /**
     * Get {@link Tower}'s Target Selection Strategy.
     * @return {@link Tower}'s Target Selection Strategy.
     */
    TargetSelectionStrategy getTargetSelectionStrategy();

    /**
     * Get {@link Tower}'s AttackStrategy.
     * @return {@link Tower}'s AttackStrategy.
     */
    AttackStrategy getAttackStrategy();

    /**
     * Set {@link Tower}'s setTargetSelectionStrategy.
     * {@link Tower}'s @param targetSelectionStrategy type of target strategy.
     */    
    void setTargetSelectionStrategy(TargetSelectionStrategy targetSelectionStrategy);

    /**
     * Set {@link Tower}'s AttackStrategy.
     * {@link Tower}'s @param targetSelectionStrategy type of attack strategy.
     */ 
    void setAttackStrategy(AttackStrategy attackStrategy);

    /**
     * Clear all the {@link Tower}'s {@link Bullet}s fired.
     */
    void clearBullets();

    /**
     * Update all the {@link Tower}'s {@link Bullet}s position.
     */
    void updateBullets();
    
    /**
     * Get all active {@link Bullet}s fired from a {@link Tower}.
     * @return active {@link Bullet}s fired from a {@link Tower}.
     */
    Set<Bullet> getBullets();

}
