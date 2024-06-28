package it.unibo.model.entities.defense.tower.target;

import java.util.Optional;
import java.util.Set;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.enemies.Enemy;

/**
 * Strategy pattern for choosing the type of the tower's target method.
 */
public interface TargetSelectionStrategy {

    /**
     * {@link Tower}'s target method to target {@link Enemy}. Attacking @param
     * tower. All the @param enemies alive.
     *
     * @return {@code Optional} {@link Enemy} chosen based on the
     * {@link Tower}'s' target strategy.
     */
    Optional<Enemy> selectTarget(final Tower tower, final Set<Enemy> enemies);
}
