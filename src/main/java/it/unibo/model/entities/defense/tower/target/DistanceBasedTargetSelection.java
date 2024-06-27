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
            double distance = this.calculateDistance(tower.getPosition(), enemy.getPosition());
            if (distance <= tower.getRange()) {
                return Optional.of(enemy);
            }
        }
        return Optional.empty();
    }

    private double calculateDistance(Position2D towerPosition2d, Position2D enemyPosition2d) {
        double deltaX = towerPosition2d.x() - enemyPosition2d.x();
        double deltaY = towerPosition2d.y() - enemyPosition2d.y();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
