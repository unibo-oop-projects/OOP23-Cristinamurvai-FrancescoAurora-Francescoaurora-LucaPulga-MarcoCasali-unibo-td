package it.unibo.model.entities.defense.bullet;

import it.unibo.model.core.GameState;
import it.unibo.model.entities.IMovableEntity;

/**
 * Represents the bullet fired from the defensive tower's weapon.
 */
public interface Bullet extends IMovableEntity {

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
    double getSpeed();

    boolean hasReachedTarget();

    void update(GameState gameState);

    boolean isOutOfBounds();
}
