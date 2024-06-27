package it.unibo.model.entities.defense.tower.attack;

import java.util.Optional;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.defense.tower.target.TargetSelectionStrategy;
import it.unibo.model.entities.enemies.Enemy;

/**
 * Strategy pattern for choosing the type of the tower's attack method.
 */
public interface AttackStrategy {

    /**
     * {@link Tower}'s attack method to attack target {@link Enemy}.
     * Attacking @param tower.
     * Target @param enemy chosen by the {@link Tower} depending on the {@link TargetSelectionStrategy}.
     */
    void attack(final Tower tower, final Optional<Enemy> enemy);
}
