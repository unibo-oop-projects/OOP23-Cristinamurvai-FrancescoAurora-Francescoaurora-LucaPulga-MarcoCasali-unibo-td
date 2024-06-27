package it.unibo.model.entities.defense.tower.target;

import java.util.Optional;
import java.util.Set;

import it.unibo.model.entities.defense.tower.Tower;
import it.unibo.model.entities.enemies.Enemy;
import it.unibo.model.utilities.Position2D;

// Implementazione di selezione del bersaglio basata sulla distanza
public class DistanceBasedTargetSelection implements TargetSelectionStrategy {

    @Override
    public Optional<Enemy> selectTarget(Tower tower, Set<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            double distance = Position2D.calculateDistance(tower.getPosition(), enemy.getPosition());
            if (distance <= tower.getRange()) {
                return Optional.of(enemy);
            }
        }
        return Optional.empty();
    }
}
