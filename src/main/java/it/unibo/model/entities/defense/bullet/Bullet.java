package it.unibo.model.entities.defense.bullet;

import it.unibo.model.core.GameState;
import it.unibo.model.entities.IMovableEntity;
import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.weapon.Weapon;
import it.unibo.model.entities.enemies.Enemy;

/**
 * Represents the {@link Bullet} as a {@link IMovableEntity} fired from the defensive {@link Tower}'s {@link Weapon}.
 */
public interface Bullet extends IMovableEntity {

    /**
     * Represents the damage dealt to the target {@link Enemy}.
     * @return the damage dealt to the target {@link Enemy}.
     */
    int getDamage();

    /**
     * Represents the speed {@link Bullet}.
     * @return the speed {@link Bullet}.
     */
    double getSpeed();

    /**
     * Determines whether the {@link Bullet} has reached the position of the target {@link Enemy}.
     * @return {@code True} if target enemy's position reached, otherwise {@code False}.
     */
    boolean hasReachedTarget();

    /**
     * Implement {@link Bullet}'s observer.
     */
    void update(GameState gameState);

    /**
     * Determines if {@link Bullet} is out of bound or not.
     * @return {@code True} if {@link Bullet} is out of bound, otherwise {@code False}.
     */
    boolean isOutOfBounds();
}
