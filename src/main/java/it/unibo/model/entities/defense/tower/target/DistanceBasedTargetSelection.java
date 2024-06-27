package it.unibo.model.entities.defense.tower.target;

import java.util.Optional;
import java.util.Set;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;

/**
 * Strategy pattern for choosing the type of the tower's target method. This one is based on the distance between the towers and the enemies.
 */
public class DistanceBasedTargetSelection implements TargetSelectionStrategy {

    /**
     * {@link Tower}'s target method to target {@link Enemy}.
     * Attacking @param tower.
     * All the @param enemies alive.
     * @return {@code Optional} {@link Enemy} chosen based on the {@link Tower}'s' target strategy. 
     */
    @Override
    public Optional<Enemy> selectTarget(final Tower tower, final Set<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            double distance = Position2D.calculateDistance(tower.getPosition(), enemy.getPosition());
            if (distance <= tower.getRange()) {
                return Optional.of(enemy);
            }
        }
        return Optional.empty();
    }
}
